(ns joodoweb.controller.docs-controller-spec
  (:use
    [speclj.core]
    [joodo.spec-helpers.controller :only (do-get rendered-template rendered-context with-mock-rendering with-routes)]
    [joodoweb.controller.docs-controller :only (docs-controller)]))

(describe "Documentation Controller"

  (with-mock-rendering)
  (with-routes docs-controller)

  (context "dynamically creates a route based on Joodo's docstrings"
    (it "routes exists for joodo namespaces"
      (let [doc_env (do-get "/docs/joodo_env")
            doc_spec-cont (do-get "/docs/joodo_spec-helpers_controller")]
        (should= 200 (:status doc_env))
        (should= 200 (:status doc_spec-cont))
        (should= "docs/ns_doc" @rendered-template)))

    (it "invalid ns route doesn't exist"
      (let [invalid_doc (do-get "/docs/invalid-ns")]
        (should= 200 (:status invalid_doc))
        (should= "docs/not_found" @rendered-template)))

    (it "passes all information the view needs to populate itself with documentation."
      (let [result (do-get "/docs/joodo_middleware_view-context")]
        (should= "{wrap-view-context #'joodo.middleware.view-context/wrap-view-context}" (str (:joodo-fns @rendered-context)))))
  )
)

(run-specs)
