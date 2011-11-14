(ns joodoweb.controller.tutorial-controller
  (:use
    [compojure.core]
    [joodo.views :only (render-template)]
    [ring.util.response :only (redirect)]))

(defroutes tutorial-controller
  (GET "/tutorial" [] (redirect "tutorial/index"))
  (context "/tutorial" []
    (GET "/index" [] (render-template "tutorial/index"))
    (GET "/install" [] (render-template "tutorial/install"))
    (GET "/make-app" [] (render-template "tutorial/make_app"))
    (GET "/tour" [] (render-template "tutorial/tour"))))