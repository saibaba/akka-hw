(ns akka-hw.hello
  (:import
    (akka.actor ActorRef ActorSystem Props Inbox)
    (scala.concurrent.duration Duration FiniteDuration)
    (java.util.concurrent TimeUnit)))

(require '[akka-hw.messages :as messages])
(require '[akka-hw.actors :as actors])

(defn create-greetee
  [name]
  (doto (akka-hw.messages.WhoToGreet.) (.setWho name)))

(defn tell-to-greet
  [greeter who inbox]
  (.tell greeter (create-greetee who) (ActorRef/noSender))
  (.send inbox greeter (akka-hw.messages.Greet.))
  (let [fiveseconds (Duration/create 5, (TimeUnit/SECONDS))
        greeting1 (.receive inbox fiveseconds)]
     (println (.getMessage greeting1))))

(defn send-repeated-messages
  [system greeter]
  (let [actor (actors/create-greet-printer-actor)
        props (akka.actor.Props/create actor)
        greetPrinter (.actorOf system props)
        zero (Duration/Zero)
        onesecond (Duration/create 1, (TimeUnit/SECONDS))
        scheduler (.scheduler system)
        dispatcher (.dispatcher system)
        greet (akka-hw.messages.Greet.)]
    (.schedule scheduler zero onesecond greeter greet dispatcher greetPrinter)))

(defn sample
  []
  (let [system  (ActorSystem/create "helloakka")
        actor   (actors/create-greeter-actor)
        props   (akka.actor.Props/create actor)
        greeter (.actorOf system props "greeter")
        inbox   (Inbox/create system)]
        (tell-to-greet greeter "sai" inbox)
        (tell-to-greet greeter "typesafe" inbox)
        (let [cancellable (send-repeated-messages system greeter)]
          (Thread/sleep 5000)
          (.cancel cancellable)))
  "Goodbye")
