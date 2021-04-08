(ns azure-servicebus.core
  (:require [azure-servicebus.config :as config])
  (:gen-class)
  (:import (com.azure.messaging.servicebus ServiceBusClientBuilder ServiceBusMessage)))

(def conn-str (config/get-sb-conn-str))

(def builder (-> (ServiceBusClientBuilder.)
                 (.connectionString conn-str)))

(def queue-name "salesmessages")

(def sender (.buildClient (.queueName (.sender builder) queue-name)))
(def receiver (.buildClient (.queueName (.receiver builder) queue-name)))

(defn send-message [msg]
  (.sendMessage sender
               (ServiceBusMessage. msg)))

(defn read-message []
  (let [msg (first (.receiveMessages receiver 1))
        contents (.toString (.getBody msg))]
    (.complete receiver msg)
    contents
    ))

(comment

  (send-message
  "this is going into the Azure cloud")
  )