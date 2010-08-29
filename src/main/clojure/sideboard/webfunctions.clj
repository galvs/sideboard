(ns sideboard.webfunctions
  (:require [webfunction.webfunction :as web])
  )

(defn ^{web/uri "/index.html"} index-html []
  "<h1>Hello  - this is the index page to sideboard - <a href='pat.html'>click here</a></h1>")

(defn ^{web/uri "/pat.html"} pat-html []
  "<h1>Hello  - this is the patrick page</h1>")

