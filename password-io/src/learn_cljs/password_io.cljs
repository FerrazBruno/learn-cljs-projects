(ns ^:figwheel-hooks learn-cljs.password-io
  (:require
   [goog.dom :as gdom]
   [goog.events :as gevents]))

(defn values-same?
  [field-1 field-2]
  (= (aget field-1 "value")
     (aget field-2 "value")))

(defn handle-change
  [password confirmation status]
  (gdom/setTextContent status
                       (if (values-same? password confirmation)
                         "Matches"
                         "Do not match")))

(let [password     (gdom/getElement "password")
      confirmation (gdom/getElement "confirmation")
      status       (gdom/getElement "out")]

  (gevents/listen password "keyup"
                  #(handle-change password confirmation status))

  (gevents/listen confirmation "keyup"
                  #(handle-change password confirmation status)))

