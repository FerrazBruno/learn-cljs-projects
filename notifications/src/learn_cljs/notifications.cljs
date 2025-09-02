(ns learn-cljs.notifications
  (:require
    [goog.dom :as gdom]
    [reagent.dom :as rdom]
    [learn-cljs.notifications.pubsub :as pubsub]
    [learn-cljs.notifications.command-event :as command-event]
    [learn-cljs.notifications.actor :as actor]))

(rdom/render
  ;; [pubsub/app]
  [command-event/app]
  ;; [actor/app]
  (gdom/getElement "app"))
