(ns azure-servicebus.web
  (:import
    [io.netty.handler.ssl SslContextBuilder])
  (:require
    [compojure.core :as compojure :refer [GET]]
    [ring.middleware.params :as params]
    [compojure.route :as route]
    [compojure.response :refer [Renderable]]
    [aleph.http :as http]
    [byte-streams :as bs]
    [manifold.stream :as s]
    [manifold.deferred :as d]
    [clojure.core.async :as a]
    [clojure.java.io :refer [file]]))

(defn hello-world-handler
  [req]
  {:status 200
   :headers {"content-type" "text/plain"}
   :body "hello world!"})

(defn delayed-hello-world-handler
  [req]
  (d/timeout!
    (d/deferred)
    1000
    (hello-world-handler req)))

