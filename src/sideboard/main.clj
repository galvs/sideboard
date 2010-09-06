(ns sideboard.main
  [:require
   ring.adapter.jetty
   [plugboard.plugboard :as plugboard]
   [plugboard.configurations :as pc]
   [webfunction.webfunction :as web]
   (webfunction selectors plugboards response)
   sideboard.webfunctions]
  )

(defn application-handler [req]
  (webfunction.response/get-response
   req
   (merge pc/default-decision-map
          (webfunction.plugboards/basic-config
           (map find-ns ['sideboard.webfunctions]))
          )
   ))

