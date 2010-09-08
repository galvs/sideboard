(ns sideboard.main
  [:require
   ring.adapter.jetty
   [plugboard.core.plugboard :as plugboard]
   [plugboard.core.configurations :as pc]
   [plugboard.webfunction.webfunction :as web]
   (plugboard.webfunction selectors plugboards response)
   sideboard.webfunctions]
  )

(defn application-handler [req]
  (plugboard.webfunction.response/get-response
   req
   (merge pc/default-decision-map
          (plugboard.webfunction.plugboards/web-function-resources
           (map find-ns ['sideboard.webfunctions]))
          )
   ))

