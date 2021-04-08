(ns azure-servicebus.config
  (:require [dotty.core :refer [env]])
  )

(defn get-sb-conn-str []
  (or (env "SB_CONN_STR")
      "empty-conn-str"))
