(ns learn-cljs.notes.ui.views.note-form
  (:require
    [reagent.core :as r]
    [learn-cljs.notes.state :refer [app]]
    [learn-cljs.notes.ui.common :refer [button]]))

(defn update-data
  [data key]
  (fn [e]
    (swap! data assoc key (.. e -target -value))))

(defn input-data
  [data key label]
  (let [id (str "field-" (name key))]
    [:div.field
     [:div.label
      [:label {:for id} label]]
     [:div.control
      [:input {:id id
               :type "text"
               :on-change (update-data data key)
               :value (get @data key "")}]]]))

(defn text-area
  [data key label]
  (let [id (str "field-" (name key))]
    [:div.field
     [:div.label
      [:label {:for id} label]]
     [:div.control
      [:textarea {:id id
                  :on-change (update-data data key)
                  :value (get @data key "")}]]]))

(defn submit-button
  [data text]
  [button text {:dispatch [:notes/create @data]}])

(defn note-form
  []
  (let [form-data (r/cursor app [:note-form])]
    (fn []
      [:section.note-form
       [:h2.page-title "Edit Note"]
       [:form
        [input form-data :title "Title"]
        [textarea form-data :content "Content"]
        [submit-button form-data "Save"]]])))

