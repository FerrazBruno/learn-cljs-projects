(ns learn-cljs.notes.ui.common
  (:require
    [learn-cljs.notes.command :refer [dispatch!]]))

(defn handle-navigate
  [route-params]
  (fn [_]
    (dispatch! :route/navigate route-params)))

(defn button
  [text {:keys [route-params class]
         :or   {class ""}}]
  [:button {:class (str "button " class)
            :on-click (handle-navigate route-params)}
   text])

