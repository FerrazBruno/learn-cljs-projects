(ns ^:figwheel-hooks learn-cljs.chat
  (:require
   [goog.dom :as gdom]
   [learn-cljs.components.app :refer [init-app]]
   ;; TODO: implementar!
   [learn-cljs.handlers]
   [learn-cljs.message-bus :as bus]))

(defonce initialized?
  (do
    (init-app (gdom/getElement "app") bus/msg-ch)
    true))

