(ns joodoweb.controller.upgrade-controller
  (:use
    [compojure.core]
    [joodo.views :only (render-template)]
    [ring.util.response :only (redirect)]))

(defroutes upgrade-controller
  (GET "/upgrade" [] (redirect "/upgrade/index"))
  (context "/upgrade" []
    (GET "/index" [] (render-template "upgrade/index"))))
