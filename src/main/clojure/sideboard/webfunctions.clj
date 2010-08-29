(ns sideboard.webfunctions
  (:require
   [webfunction.webfunction :as web]
   couchdb.client
   clojure.contrib.json
   [clout.core :as clout]
   )
  )

(def doc-route (clout/route-compile "/docs/:docid"))

(defn match-document-route [uri]
  (clout/route-matches doc-route uri)
  )

(match-document-route "/docs/123")

(defn ^{web/uri (fn [uri] (match-document-route uri))
        } document-html [context]
          (let [docid (get (match-document-route (get-in context [:request :uri])) "docid")]
            (str
             (:value ( first (:rows (couchdb.client/view-get "http://localhost:5984/" "sideboard" "pages" "pages"))))
             "<p>The document id was " docid "</p>"
             )
            )
  
)

(defn ^{web/uri "/pat.html"} pat-html [context]
  "<h1>Hello  - this is the patrick page</h1>")

