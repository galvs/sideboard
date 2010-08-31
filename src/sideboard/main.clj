(ns sideboard.main
  [:require
   ring.adapter.jetty
   [plugboard.plugboard :as plugboard]
   [plugboard.configurations :as pc]
   [webfunction.webfunction :as web]
   webfunction.selectors
   webfunction.plugboards
   sideboard.webfunctions]
  )

(defn get-body [req]
  (let [[status state] (plugboard/get-status-with-state
                         (merge pc/default-decision-map
                                webfunction.plugboards/basic-config
                          )
                         {:request req})
        webfn (first (get state webfunction.plugboards/compatible-webfunctions))
        body (if (not (nil? webfn)) (webfn {:status status :request req}))
        ]
    {:status status
     :headers {}
     :body body}
    ))

(defn application-handler [req]
  (get-body req)
  )


