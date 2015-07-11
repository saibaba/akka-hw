(ns akka-hw.bean)

; reusable across generated classes as long as their state is called "state"

(defn set-field
  [instance key value]
  (swap! (.state instance) into {key value}))

(defn get-field
  [instance key]
  (@(.state instance) key))

