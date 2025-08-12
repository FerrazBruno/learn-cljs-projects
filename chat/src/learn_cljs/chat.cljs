(ns ^:figwheel-hooks learn-cljs.chat
  (:require
   [learn-cljs.api :as api]
   [goog.dom :as gdom]
   [learn-cljs.handlers]
   [learn-cljs.components.app :refer [init-app]]
   [learn-cljs.message-bus :as bus]
   [learn-cljs.state :as state]))

(defonce initialized?
  (do
    (api/init! bus/msg-ch js/WS_API_URL) ;; URL da api websocket
    (init-app (gdom/getElement "app") bus/msg-ch)
    (set! (.-getAppState js/window) #(clj->js @state/app-state))
    true))

