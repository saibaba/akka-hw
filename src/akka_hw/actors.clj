(ns akka-hw.actors
  (:import
    (akka.actor UntypedActor UntypedActorFactory ActorRef)
    (java.io Serializable)))

(require '[akka-hw.messages :as messages])

(defmulti greeter-on-receive-handler (fn [_ _ _ m] (type m)))
(defmethod greeter-on-receive-handler akka-hw.messages.Greet
  [this sender state message]
  (println "Got greet message for" @state)
  (let [hello (str "Hello " @state ", welcome to distributed world!")
        greeting (doto (messages/create-greeting-message) (.setMessage hello))]
    (.tell sender greeting (.getSelf this))))
(defmethod greeter-on-receive-handler akka-hw.messages.WhoToGreet
  [this sender state message]
  (println "Got who to greet message for" (.getWho message) )
  (reset! state (.getWho message)))

(defn create-greeter-actor
  []
  (proxy [UntypedActorFactory] []
    (create []
      (let [state (atom "unknown")]
      (proxy [UntypedActor] []
        (onReceive [message]
          (greeter-on-receive-handler this (proxy-super getSender) state message)))))))

(defmulti greet-printer-on-receive-handler (fn [m] (type m)))
(defmethod greet-printer-on-receive-handler akka-hw.messages.Greeting
  [message]
  (println "Greet printer Got greeting" (.getMessage message)))
(defn create-greet-printer-actor
  []
  (proxy [UntypedActorFactory] []
    (create []
      (proxy [UntypedActor] []
        (onReceive [message]
          (greet-printer-on-receive-handler message))))))

