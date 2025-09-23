(ns ^:figwheel-hooks learn-cljs.notes
  (:require
    [goog.dom :as gdom]
    [learn-cljs.notes.event-handlers.core]
    [learn-cljs.notes.routes :as routes]
    [learn-cljs.notes.ui.footer :refer [footer]]
    [learn-cljs.notes.ui.header :refer [header]]
    [learn-cljs.notes.ui.notifications :refer [notifications]]
    [learn-cljs.notes.ui.main :refer [main]]
    [learn-cljs.notes.ui.sidebar :refer [sidebar]]
    [reagent.dom :as rdom]))

(defn app
  []
  [:div.app
   [header]
   [main]
   [sidebar]
   [footer]
   [notifications]])

(rdom/render
  [app]
  (gdom/getElement "app"))

(defonce initialize?
  (do
    (routes/initialize!)
    true))

