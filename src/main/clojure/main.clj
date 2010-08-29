(ns main
  [:require ring.adapter.jetty [plugboard.status :as plugboard]]
  )

(defn get-body [req]
  {:status (plugboard/get-status {} {:request req})
   :headers {}
   :body "Hello World!"}
  )

(defn application-handler [req]
  (get-body req)
  )


