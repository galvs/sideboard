(ns sideboard.webfunctions
  (:use
   plugboard.webfunction.context
   )
  (:require
   [plugboard.webfunction.webfunction :as web]
   couchdb.client
   clojure.contrib.json
   [clout.core :as clout]
   [hiccup.core :as hiccup]
   )
  )

(def doc-route (clout/route-compile "/docs/:docid"))

(defn match-document-route [uri]
  (clout/route-matches doc-route uri)
  )

(defn ^{web/uri (fn [uri] (match-document-route uri))
        } document-html []
          (let [docid (get (match-document-route (get-in *web-context* [:request :uri])) "docid")]
            (hiccup/html
             [:h1 "Document " docid]
             [:h2 "Content"]
             [:p
              (:value (first (:rows (couchdb.client/view-get "http://localhost:5984/" "sideboard" "pages" "pages"))))])
            )
          )

(defn ^{web/uri "/pat.html"
        web/content-type "text/html"
        :title "Index page"}
  index-html []
  (hiccup/html
   [:h1 (get-meta :title)]
   [:p "Welcome to the site"]))

