(ns joodoweb.controller.docs-controller
  (:use
    [compojure.core]
    [ring.util.response :only (redirect)]
    [joodo.views :only (render-template)])
)


; (defmacro get-routes-for [start-path url-bindings routes] 
;   (let [compiled-routes
; 		    (for [route routes]
; 		      `(GET ~route [] (render-template (str "docs/spec_helpers" ~route))))]
;   `(context ~start-path ~url-bindings
; 	 ~@compiled-routes)))

; (defmacro build-get-routes-for []
;   (let [func-seq
;     (for [a-func (keys (ns-publics 'joodo.spec-helpers.view))]
;         `(GET ~(str "/" a-func) [] (render-template "doc" :joodo-fn a-func)))]
;     `(context "/docs" []
;       ~@func-seq)))
; 
; (defroutes docs-controller
;   (GET "/docs/test" [] (render-template "index"))
;     (build-get-routes-for))


; (defn find-joodo-fn [uri]
; 	(println uri)
; 	(get (ns-publics 'joodo.spec-helpers.view) (symbol uri)))
; 
; (defn docs-controller [request]
; 	(println "{{{{{{ -+- }}}}}}" request)
; 	(if (and
; 			(= :get (:request-method request))
; 			(re-matches #"^/docs/.*" (or (:uri request) "")))
; 		(if-let [joodo-fn (find-joodo-fn (subs (:uri request) 6))]
; 			(do (println "got it!") (render-template "doc" :joodo-fn joodo-fn)))))

(defn- get-ns [joodo-ns-name]
  (try
    (require joodo-ns-name)
    (find-ns joodo-ns-name)
    (catch Exception e
      nil)))

(defn- get-fn [joodo-ns joodo-fn-name]
  (let [pub-fn (ns-interns joodo-ns)]
    (joodo-fn-name pub-fn)))

(defn- direct-to-ns-doc-page [joodo-ns-name]
  (if-let [joodo-ns (get-ns joodo-ns-name)]
    (render-template "docs/ns_doc" :joodo-ns joodo-ns)
    (render-template "docs/not_found")))

(defn- direct-to-fn-doc-page [joodo-ns-name joodo-fn-name]
  (if-let [joodo-ns (get-ns joodo-ns-name)]
    (if-let [joodo-fn (get-fn joodo-ns joodo-fn-name)]
      (render-template "docs/fn_doc" :joodo-ns joodo-ns :joodo-fn joodo-fn)
      (render-template "docs/not_found"))
    (render-template "docs/not_found")))

(defroutes docs-controller
  (GET "/docs" [] (redirect "docs/index"))
  (context "/docs" []
    (GET "/index" [] (render-template "docs/index"))
    (GET "/:joodo-ns-name" [joodo-ns-name] (direct-to-ns-doc-page (symbol (clojure.string/replace joodo-ns-name #"_" "."))))
    (GET "/:joodo-ns-name/:joodo-fn-name" [joodo-ns-name joodo-fn-name] (direct-to-fn-doc-page (symbol (clojure.string/replace joodo-ns-name #"_" ".")) (symbol joodo-fn-name)))))