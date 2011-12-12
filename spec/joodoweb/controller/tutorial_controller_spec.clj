(ns joodoweb.controller.tutorial-controller-spec
  (:use
    [speclj.core]
    [joodo.spec-helpers.controller :only (should-redirect-to do-get with-mock-rendering with-routes)]
    [joodoweb.controller.tutorial-controller :only (tutorial-controller)]))

(describe "Tutorial Controller"

  (with-mock-rendering)
  (with-routes tutorial-controller)

  (it "has 4 valid routes; one for each section of the tutorial"
    (should= 200 (:status (do-get "/tutorial/basics")))
    (should= 200 (:status (do-get "/tutorial/controllers")))
    (should= 200 (:status (do-get "/tutorial/views")))
    (should= 200 (:status (do-get "/tutorial/reference"))))

  (it "redirects the user to /tutorial/basics if a request is sent to /tutorial"
    (should-redirect-to (do-get "/tutorial") "/tutorial/basics"))
)