(ns joodoweb.view.view-helpers
  "Put helper functions for views in this namespace."
  (:require [joodo.views :refer (render-partial *view-context*)]
            [hiccup.page :refer :all ]
            [hiccup.form :refer :all ]
            [joodo.middleware.request :refer (*request*)]
            [clojure.string :as string]
            [clojure.repl :refer (source-fn)]
            [joodoweb.docs.usage_docs :refer (usage-docs)]))

(def documented-namespaces
  ['joodo.controllers
   'joodo.env
   'joodo.middleware.asset-fingerprint
   'joodo.middleware.favicon
   'joodo.middleware.keyword-cookies
   'joodo.middleware.locale
   'joodo.middleware.refresh
   'joodo.middleware.request
   'joodo.middleware.servlet-session
   'joodo.middleware.util
   'joodo.middleware.verbose
   'joodo.middleware.view-context
   'joodo.spec-helpers.controller
   'joodo.spec-helpers.view
   'joodo.views])

(defn format-namespace [ns-string]
  (string/replace ns-string #"joodo\." ""))

(defn ns->url-safe [ns-string]
  (string/replace ns-string #"\." "_"))

(defn ns->github-url [ns-string]
  (str "https://github.com/slagyr/joodo/blob/master/joodo/src/"
    (string/replace
      (string/replace ns-string #"-" "_")
      #"\." "/") ".clj"))

(defn url-safe->ns [ns-string]
  (symbol (string/replace ns-string #"_" ".")))

(defn get-source-code [ns-string fn-string]
  (try
    (require (symbol ns-string))
    (source-fn (symbol (str ns-string "/" fn-string)))
    (catch Exception e
      nil)))

(defn get-random-icon []
  [:img.icon {:src (str "/images/throw_" (+ 1 (rand-int 15)) ".png") :alt "A Joodo Throw"}])
