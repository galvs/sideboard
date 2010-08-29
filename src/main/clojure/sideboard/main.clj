(ns sideboard.main
  [:require
   ring.adapter.jetty
   plugboard.status
   plugboard.core
   (webfunction helpers vars)
   sideboard.webfunctions]
  )

(defn webfn-matches-path? [path webfn]
  (= path (get (meta webfn) webfunction.vars/uri))
  )

(defn get-first-matching-webfunction-for-path [path]
  (first (filter #(webfn-matches-path? path %) (webfunction.helpers/get-web-functions (find-ns 'sideboard.webfunctions))))
  )

(defn grab-path-from-compojure [state]
  [false (merge {plugboard.core/path (get-in state [:request :uri])} state)]
  )

(defn resource-exists? [state]
  (let [path (get state plugboard.core/path)
        webfn (get-first-matching-webfunction-for-path path)
        result (not (nil? webfn))
        ]
    [result
     (if result
       (merge state {:webfunction webfn}) ; TODO: Don't use a pure atom, use a namespaced atom
       state)
     ]
    )
  )

(defn get-body [req]
  (let [[status state] (plugboard.status/get-status-with-state {:B3 grab-path-from-compojure
                                             :C7 resource-exists?}
                         {:request req})
        webfn (get state :webfunction)
        body (if (not (nil? webfn)) (webfn))
        ]
    {:status status
     :headers {}
     :body body}
    ))

(defn application-handler [req]
  (get-body req)
  )


