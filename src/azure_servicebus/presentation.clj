(ns azure-servicebus.presentation
  (:require [compojure.core :refer :all]
            [compojure.route :as route]))

;; Literal data structures

[1 2 3]

{:a :foo
 :b "bar"
 :c 123}

;; Expressions c.f. statements

(if (< 0.5 (rand))
  :heads
  :tails)

;; Higher order functions

(defn twice [f]
  (fn [x] (f (f x))))

(defn plus-three [i]
  (+ i 3))

(def g (twice plus-three))

(g 7)                                                       ;; => 13


;; Generic functions

(let [x [1 2 3]
      y #{1 2 3}
      z {:a 1 :b 2 :c 3}]

  (println (first x))
  (println (first y))
  (println (first z)))

;; Laziness

(import java.util.UUID)

(defn uuid-seq
  []
  (lazy-seq
    (cons (str (UUID/randomUUID))
          (uuid-seq))))

(take 100 (map (fn [x y]
                 [x y])
               (range)
               (uuid-seq)))

;; Nil punning

(let [x nil]
  (if x "it was true" "it was false"))

(let [x nil]
  (str "it was " (boolean x)))

(let [x nil]
  (seq x))

;; Destructuring

(let [[small big] (split-with #(< % 5) (range 10))]
  (println small big))

;; Web programming

(use 'org.httpkit.server)

(defn app [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "hello HTTP!"})

(defonce server (atom nil))

(defn stop-server []
  (when-not (nil? @server)
    ;; graceful shutdown: wait 100ms for existing requests to be finished
    ;; :timeout is optional, when no timeout, stop immediately
    (@server :timeout 100)
    (reset! server nil)))

(defn start-server []
  (reset! server (run-server #'app {:port 8080})))


;; with routing

(defroutes app
           (GET "/" [] "<h1>Hello World</h1>")
           (route/not-found "<h1>Page not found</h1>"))

(run-server app {:port 8080})
