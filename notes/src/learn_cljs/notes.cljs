(ns ^:figwheel-hooks learn-cljs.notes
  (:require
    [learn-cljs.notes.event-handlers.core]
    [learn-cljs.notes.routes     :as    routes]
    [learn-cljs.notes.ui.footer  :refer [footer]]
    [learn-cljs.notes.ui.header  :refer [header]]
    [learn-cljs.notes.ui.main    :refer [main]]
    [learn-cljs.notes.ui.sidebar :refer [sidebar]]
    [goog.dom                    :as    gdom]
    [reagent.dom                 :as    rdom]))

(defonce initialize?
  (do
    (routes/initialize)
    true))

(defn app
  []
  [:div.app
   [header]
   [main]
   [sidebar]
   [footer]])

(rdom/render
  [app]
  (gdom/getElement "app"))

(defn map-values
  [f m]
  (into {} (for [[k v] m] [k (f v)])))

(defn make-index
  [coll & {:keys [index-fn value-fn group-fn]
           :or {value-fn identity
                group-fn identity}}]
  (->> coll
       (group-by index-fn)
       (map-values #(group-fn (mapv value-fn %)))))

(defn get-links
  [notes]
  (mapcat
    (fn [note]
      (for [tag (:tags note)]
        {:note-id (:id note)
         :tag-id (:id tag)}))
    notes))

(defn normalize-notes
  [notes]
  (let [links (get-links notes)
        notes-without-tags (mapv #(dissoc % :tags) notes)
        all-note-tags (mapcat :tags notes)]
    {:notes (make-index notes-without-tags
                        :index-fn :id
                        :group-fn first)
     :tags (make-index all-note-tags
                       :index-fn :id
                       :group-fn first)
     :notes-tags {:by-note-id (make-index links
                                          :index-fn :note-id
                                          :value-fn :tag-id)
                  :by-tag-id (make-index links
                                         :index-fn :tag-id
                                         :value-fn :note-id)}}))

