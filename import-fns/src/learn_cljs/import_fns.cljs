(ns ^:figwheel-hooks learn-cljs.import-fns
  #_(:require-macros
    [macro-ns :as macros])
  (:require
    [goog.dom :refer [getElement] :rename {getElement get-element}]
    learn-cljs.ui
    [learn-cljs.format :refer [pluralize]]
    [learn-cljs.inventory :as inventory])
  (:import
    [goog.math Coordinate Rect]))

(defn item-description [i item]
  (let [qty (inventory/item-qty i item)
        label (if (> qty 1) (pluralize item) item)]
      (str qty " " label)))

(let [i (-> (inventory/make-inventory)
            (inventory/add-items "Laser Catapult" 1)
            (inventory/add-items "Antimatter Scrubber" 5))]
  (learn-cljs.ui/render-list (get-element "app")
    (map (partial item-description i)
         (inventory/list-items i))))

(.contains (Rect. 10 50 5 5)
           (Coordinate. 12 50))

