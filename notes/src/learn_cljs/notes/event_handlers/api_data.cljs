(ns learn-cljs.notes.event-handlers.api-data
  (:require
    [learn-cljs.notes.command :refer [dispatch!]]
    [learn-cljs.notes.state :refer [register-handler!]]))

(register-handler!
  :note/created
  (fn [db payload]
    (let [{:keys [id title]} payload]
      (dispatch! :notification/add
                 {:type :info
                  :text (str "Note created: " title)})
      (dispatch! :route/navigate
                 [:edit-note {:note-id id}])
      (assoc-in db [:data :notes id]
                (dissoc payload :tads)))))

