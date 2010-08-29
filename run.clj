(ns run
  (:require [swank.swank :as swank] ring.adapter.jetty main))

(ring.adapter.jetty/run-jetty
 main/application-handler
 {:join? false
  :port 8082}
 )

(swank.swank/start-repl)
