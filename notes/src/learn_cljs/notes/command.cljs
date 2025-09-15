(ns learn-cljs.notes.command
  (:require
    [learn-cljs.notes.events :refer [emit!]]))

(defn handle-test-hello!
  [name]
  (println "Hello" name)
  (emit! :test/greeting-dispatched {:name name}))

(defn dispatch!
  ([command]
   (dispatch! command nil))
  ([command payload]
   (js/setTimeout
     #(case command
        :test/hello (handle-test-hello! payload)

        (js/console.error (str "Error: unhandled command: " command)))
     0)))

