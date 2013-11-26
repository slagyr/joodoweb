(ns joodoweb.core
  (:require [compojure.core :refer :all ]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [joodo.env :as env]
            [joodo.middleware.asset-fingerprint :refer [wrap-asset-fingerprint]]
            [joodo.middleware.favicon :refer [wrap-favicon-bouncer]]
            [joodo.middleware.keyword-cookies :refer [wrap-keyword-cookies]]
            [joodo.middleware.refresh :as refresh]
            [joodo.middleware.request :refer [wrap-bind-request]]
            [joodo.middleware.util :refer [wrap-development-maybe]]
            [joodo.middleware.view-context :refer [wrap-view-context]]
            [joodo.middleware.view-context :refer [wrap-view-context]]
            [joodo.views :refer [render-template render-html]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [ring.middleware.flash :refer [wrap-flash]]
            [ring.middleware.head :refer [wrap-head]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.multipart-params :refer [wrap-multipart-params]]
            [ring.middleware.multipart-params.byte-array :refer [byte-array-store]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.session :refer [wrap-session]]
            ))

(env/load-configurations)

(defroutes joodoweb-routes
  (GET "/" [] (render-template "index"))
  (GET "/about" [] (render-template "about"))
  (GET "/community" [] (render-template "community"))
  (GET "/license" [] (render-template "license"))
  (refresh/handler 'joodoweb.controller.docs-controller/docs-controller)
  (refresh/handler 'joodoweb.controller.tutorial-controller/tutorial-controller)
  (refresh/handler 'joodoweb.controller.upgrade-controller/upgrade-controller)
  (route/not-found (render-template "not_found" :template-root "joodoweb/view" :ns `joodoweb.view.view-helpers)))

(def app-handler
  (-> joodoweb-routes
    (wrap-view-context :template-root "joodoweb/view" :ns `joodoweb.view.view-helpers)))

(def app
  (-> app-handler
    wrap-development-maybe
    wrap-bind-request
    wrap-keyword-params
    wrap-params
    wrap-multipart-params
    wrap-flash
    wrap-keyword-cookies
    wrap-session
    wrap-favicon-bouncer
    (wrap-resource "public")
    wrap-asset-fingerprint
    wrap-file-info
    wrap-head
    ))

