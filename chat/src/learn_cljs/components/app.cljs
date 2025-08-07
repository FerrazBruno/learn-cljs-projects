(ns learn-cljs.components.app
  (:require
    [learn-cljs.components.dom :as dom]
    [learn-cljs.components.header :refer [init-header]]
    [learn-cljs.components.sidebar :refer [init-sidebar]]
    [goog.dom :as gdom])
  (:import
    [goog.dom TagName]))

(defn init-main
  []
  (dom/section "content-main"
               (init-header)))

(defn init-app
  [el msg-ch]
  (let [wrapper (dom/div "app-wrapper"
                         (init-sidebar msg-ch)
                         (init-main))]
    (set! (.-innerText el) "")
    (.appendChild el wrapper)))

