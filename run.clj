(ns run
  (:require [swank.swank :as swank] ring.adapter.jetty sideboard.main))

(ring.adapter.jetty/run-jetty
 sideboard.main/application-handler
 {:join? false
  :port 8082}
 )

(swank.swank/start-repl)
