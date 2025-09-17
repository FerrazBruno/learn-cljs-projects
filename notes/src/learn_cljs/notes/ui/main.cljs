(ns learn-cljs.notes.ui.main
  (:require
    [learn-cljs.notes.state :as state]
    [learn-cljs.notes.ui.views.home :refer [home]]))

(defn not-found
  []
  [:section.hero
   [:h1.title "Page Not Found!"]])

(defn main
  []
  (let [[route params query] (:current-route @state/app)]
    [:div.main
     (case route
       :home [home]
       [not-found])]))

