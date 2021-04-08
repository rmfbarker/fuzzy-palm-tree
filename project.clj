(defproject azure-servicebus "0.1.0-SNAPSHOT"
  :description "Clojure examples"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.10.0"]
                 [com.azure/azure-messaging-servicebus "7.1.0"]
                 [aleph "0.4.6"]
                 [compojure "1.6.2"]
                 [org.clojure/core.async "1.3.610"]
                 [http-kit "2.3.0"] ; Add to your project.clj.
                 [compojure "1.6.2"]
                 [cwhitey/dotty "0.2.3"]
                 ]
  :main ^:skip-aot azure-servicebus.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
