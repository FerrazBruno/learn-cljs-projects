(ns learn-cljs.notes.ui.common
  (:require
    [learn-cljs.notes.command :refer [dispatch!]]))

(defn handle-navigate
  [route-params]
  (fn [_]
    (dispatch! :route/navigate route-params)))

(defn handle-dispatch
  [command-data]
  (fn [e]
    (.preventDefault e)
    (apply dispatch! command-data)))

(defn button
  [text {:keys [class dispatch on-click route-params]
         :or   {class ""}}]
  [:button
   {:class (str "button " class)
    :on-click (cond
                route-params (handle-navigate route-params)
                dispatch     (handle-dispatch dispatch)
                on-click     on-click
                :else        #(js/console.error
                                "No action provided for button"))}
   text])

