(ns joodoweb.core
  (:use
    [compojure.core :only (defroutes GET)]
    [compojure.route :only (not-found)]
    [joodo.middleware.view-context :only (wrap-view-context)]
    [joodo.views :only (render-template render-html)]
    [joodo.controllers :only (controller-router)]))

(defroutes joodoweb-routes
  (GET "/" [] (render-template "index"))
  (GET "/about" [] (render-template "about"))
  (GET "/community" [] (render-template "community"))
  (controller-router 'joodoweb.controller)
  (not-found (render-template "not_found" :template-root "joodoweb/view" :ns `joodoweb.view.view-helpers)))

(def app-handler
  (->
    joodoweb-routes
    (wrap-view-context :template-root "joodoweb/view" :ns `joodoweb.view.view-helpers)))