(ns ^:figwheel-hooks learn-cljs.chat
  (:require
   [api :as api]
   [goog.dom :as gdom]
   [handlers]
   [learn-cljs.components.app :refer [init-app]]
   [message-bus :as bus]
   [state :as state]))

(defonce initialized?
  (do
    (api/init! bus/msg-ch js/WS_API_URL) ;; URL da api websocket
    (init-app (gdom/getElement "app") bus/msg-ch)
    (set! (.-getAppState js/window) #(clj->js @state/app-state))
    true))

