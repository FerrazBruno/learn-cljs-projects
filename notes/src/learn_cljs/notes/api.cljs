(ns learn-cljs.notes.api
  (:require
    [camel-snake-kebab.core :as csk]
    [camel-snake-kebab.extras :as cske]
    [learn-cljs.notes.errors :as err]
    [learn-cljs.notes.events :refer [emit!]]))

(defn do-request!
  ([method path cb]
   (do-request! method path nil cb))
  ([method path body cb]
   (let [serialized-body (when body
                           (->> body
                                (cske/trandform-keys csk/->camelCaseString)
                                (clj->js)
                                (js/JSON.stringfy)))]
     (-> (js/fetch
           (str js/API_URL path)
           (cond-> {:method (name method)
                    :headers {"Authorization" (str "Bearer " js/API_TOKEN)}
                    :credentials "include"}
             (some? body)
             (->
               (assoc :body serialized-body)
               (update :headers merge {"content-type" "application/json"}))

             :always
             clj->js))
         (.then (fn [res]
                  (if (.-ok res)
                    (when (= 200 (.-status res))
                      (.json res))
                    (throw (ex-info "API Request Failed"
                                    {:status-code (.-status res)
                                     :status (.-statusText res)}
                                    :api-failure)))))
         (.then #(->> %
                      (js->clj)
                      (cske/transform-keys csk/->kebab-case-keyword)
                      (err/ok)
                      (cb)))
         (.catch #(cb (err/error %)))))))

(defn- display-error
  [err]
  (emit! :notification/added
         {:type :error
          :text (str "API: Error: " (ex-message err))}))

(defn create-note!
  [note]
  (do-request! :post "/notes" note
               (fn [res]
                 (->> res
                      (err/map
                        #(emit! :note/create %))
                      (err/unwrap-or display-error)))))

