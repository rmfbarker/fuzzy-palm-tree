(ns azure-servicebus.core
  (:require [dotty.core :refer [env]])
  (:gen-class)
  (:import (com.azure.messaging.servicebus ServiceBusClientBuilder ServiceBusMessage)))

(def conn-str (env "SB_CONN_STR"))

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