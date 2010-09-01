(defproject sideboard "1.0.0"
  :description "A sample web application built with compojure, plugboard and webfunction."
  :url "http://github.com/malcolmsparks/sideboard"
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [clout "0.2.0"]
                 [hiccup "0.2.6"]
                 [compojure "0.4.1"]
                 [ring/ring-devel "0.2.5"]
                 [ring/ring-httpcore-adapter "0.2.5"]
                 [ring/ring-jetty-adapter "0.2.5"]
                 [ring/ring-servlet "0.2.5"]
                 [plugboard "1.1.0"]
                 [webfunction "1.2.0"]
                 [org.clojars.the-kenny/clojure-couchdb "0.2.3"]
                 ]

  :dev-dependencies [
                     [swank-clojure "1.2.1"]
                     ]
  )
