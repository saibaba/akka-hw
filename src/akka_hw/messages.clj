(ns akka-hw.messages)

(require '[akka-hw.bean :as bean])

(gen-class
  :name akka-hw.messages.Greet
  :implements [java.io.Serializable])

(gen-class
  :name akka-hw.messages.WhoToGreet
  :implements [java.io.Serializable]
  :state state
  :init init
  :prefix "who-to-greet-"
  :methods [[setWho [String] void] [getWho [] String]])

(defn who-to-greet-init
  []
  [ [] (atom {:who "nobody" })])

(defn who-to-greet-setWho
  [instance who]
  (bean/set-field instance :who who))

(defn who-to-greet-getWho
  [instance]
  (bean/get-field instance :who))

(defn create-who-to-greet-message
  []
  (akka-hw.messages.WhoToGreet.))

(gen-class
  :name akka-hw.messages.Greeting
  :implements [java.io.Serializable]
  :state state
  :init init
  :prefix "greeting-"
  :methods [[setMessage [String] void] [getMessage [] String]])

(defn greeting-init
  []
  [ [] (atom {:message "no message" })])

(defn greeting-setMessage
  [instance msg]
  (bean/set-field instance :message msg))

(defn greeting-getMessage
  [instance]
  (bean/get-field instance :message))

(defn create-greeting-message
  []
  (akka-hw.messages.Greeting.))

