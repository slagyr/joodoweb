(ns joodoweb.controller.docs-controller
  (:use
    [compojure.core]
    [joodo.views :only (render-template)]
    [ring.util.response :only (redirect)]))

(defroutes docs-controller
  (GET "/docs" [] (redirect "docs/index"))
  (context "/docs" []
    (GET "/index" [] (render-template "docs/index"))))