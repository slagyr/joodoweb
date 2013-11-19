(ns joodoweb.controller.upgrade-controller-spec
  (:use
    [speclj.core]
    [joodo.spec-helpers.controller :only (should-redirect-to do-get with-mock-rendering with-routes)]
    [joodoweb.controller.upgrade-controller :only (upgrade-controller)]))

(describe "Upgrade Controller"

  (with-mock-rendering)
  (with-routes upgrade-controller)

  (it "has 1 valid route"
    (should= 200 (:status (do-get "/upgrade/index"))))

  (it "redirects the user to /upgade/upgrade if a request is sent to /upgrade"
    (should-redirect-to (do-get "/upgrade") "/upgrade/index")))