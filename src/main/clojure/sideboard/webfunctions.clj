(ns sideboard.webfunctions
  (:require webfunction.vars)
  )

(defn ^{webfunction.vars/uri "/index.html"} index-html []
  "<h1>Hello  - this is the index page to sideboard - <a href='pat.html'>click here</a></h1>")

(defn ^{webfunction.vars/uri "/pat.html"} pat-html []
  "<h1>Hello  - this is the patrick page</h1>")

