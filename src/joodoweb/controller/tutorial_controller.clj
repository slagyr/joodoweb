(ns joodoweb.controller.tutorial-controller
  (:use
    [compojure.core]
    [joodo.views :only (render-template)]
    [ring.util.response :only (redirect)]))

(defroutes tutorial-controller
  (GET "/tutorial" [] (redirect "/tutorial/basics"))
  (context "/tutorial" []
    (GET "/basics" [] (render-template "tutorial/basics"))
    (GET "/controllers" [] (render-template "tutorial/controllers"))
    (GET "/views" [] (render-template "tutorial/views"))
    (GET "/reference" [] (render-template "tutorial/reference"))))