(ns sideboard.main
  [:require
   ring.adapter.jetty
   [plugboard.plugboard :as plugboard]
   [plugboard.configurations :as pc]
   [webfunction.webfunction :as web]
   (webfunction selectors plugboards headers)
   sideboard.webfunctions]
  )

(defn get-body [req]
  (let [[status state] (plugboard/get-status-with-state
                         (merge pc/default-decision-map
                                (webfunction.plugboards/basic-config
                                 (map find-ns ['sideboard.webfunctions]))
                          )
                         {:request req})
        webfn (first (get state webfunction.plugboards/compatible-webfunctions))
        headers (webfunction.headers/get-headers webfn)
        body (if (not (nil? webfn)) (webfn {:status status :request req}))
        ]
    {:status status
     :headers headers
     :body body}
    ))

(defn application-handler [req]
  (get-body req)
  )



