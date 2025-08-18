(ns ^:figwheel-hooks learn-cljs.exercise-tracker
  (:require
    [goog.dom :as gdom]
    [reagent.dom :as rdom]))

(defn hello
  []
  [:p "Hello World"])

(rdom/render
  hello
  (gdom/getElement "app"))

