(ns joodoweb.controller.docs-controller-spec
  (:use
    [speclj.core]
    [joodo.spec-helpers.controller]
    [joodoweb.controller.docs-controller]))

(describe "Documentation Controller"

  (with-mock-rendering)
  (with-routes docs-controller)

  (context "dynamically creates a route based on Joodo's docstrings"
    (it "joodo.env namespace route exists"
      (let [result (do-get "/docs/joodo_env")]
        (should= 200 (:status result))
        (should= "docs/ns_doc" @rendered-template)))

    (it "invalid ns route doesn't exist"
      (let [result (do-get "/docs/invalid-ns")]
        (should= 200 (:status result))
        (should= "docs/not_found" @rendered-template)))

    (it "find-tag route exists"
      (let [result (do-get "/docs/joodo_spec-helpers_view/find-tag")]
        (should= 200 (:status result))
        (should= "docs/fn_doc" @rendered-template)))

    (it "wrap-bind-request route exists"
      (let [result (do-get "/docs/joodo_middleware_request/wrap-bind-request")]
        (should= 200 (:status result))
        (should= "docs/fn_doc" @rendered-template)))

    (it "invalid function route doesn't exist"
      (let [result (do-get "/docs/joodo_string/invalid-fn")]
        (should= 200 (:status result))
        (should= "docs/not_found" @rendered-template)))
  )
)

(run-specs)