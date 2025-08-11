(ns learn-cljs.components.app
  (:require
    [learn-cljs.components.dom :as dom]
    [learn-cljs.components.auth :refer [init-auth]]
    [learn-cljs.components.compose :refer [init-composer]]
    [learn-cljs.components.header :refer [init-header]]
    [learn-cljs.components.messages :refer [init-messages]]
    [learn-cljs.components.sidebar :refer [init-sidebar]]))

(defn init-main
  [msg-ch]
  (dom/section "content-main"
               (init-header)
               (init-messages)
               (init-composer msg-ch)))

(defn init-app
  [el msg-ch]
  (let [wrapper (dom/div "app-wrapper"
                         (init-sidebar msg-ch)
                         (init-main msg-ch)
                         (init-auth msg-ch))]
    (set! (.-innerText el) "")
    (.appendChild el wrapper)))

