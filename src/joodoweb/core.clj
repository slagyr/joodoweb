(ns joodoweb.core
  (:use
    [compojure.core :only (defroutes GET)]
    [compojure.route :only (not-found)]
    [joodo.middleware.view-context :only (wrap-view-context)]
    [joodo.middleware.request :only (wrap-bind-request)]
    [ring.middleware.params :only (wrap-params)]
    [ring.middleware.keyword-params :only (wrap-keyword-params)]
    [ring.middleware.multipart-params :only [wrap-multipart-params]]
    [ring.middleware.multipart-params.byte-array :refer [byte-array-store]]
    [joodo.views :only (render-template render-html)]
    [joodo.controllers :only (controller-router)]))

(defroutes joodoweb-routes
  (GET "/" [] (render-template "index"))
  (GET "/about" [] (render-template "about"))
  (GET "/community" [] (render-template "community"))
  (GET "/license" [] (render-template "license"))
  (controller-router 'joodoweb.controller)
  (not-found (render-template "not_found" :template-root "joodoweb/view" :ns `joodoweb.view.view-helpers)))

(def app-handler
  (->
    joodoweb-routes
    wrap-keyword-params
    wrap-params
    (wrap-multipart-params {:store (byte-array-store)})
    wrap-bind-request
    (wrap-view-context :template-root "joodoweb/view" :ns `joodoweb.view.view-helpers)))