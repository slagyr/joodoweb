(ns joodoweb.controller.docs-controller
  (:use
    [compojure.core]
    [ring.util.response :only (redirect)]
    [joodo.views :only (render-template)]
    [joodoweb.view.view-helpers :only (url-safe->ns documented-namespaces)]))

(defn- get-ns [joodo-ns-name]
  (try
    (require joodo-ns-name)
    (find-ns joodo-ns-name)
    (catch Exception e
      nil)))

(defn- get-fns [joodo-ns]
	(ns-publics joodo-ns))

(defn- direct-to-doc-page [joodo-ns-name]
  (if-let [joodo-ns (get-ns joodo-ns-name)]
    (render-template "docs/ns_doc" :joodo-ns joodo-ns :joodo-fns (get-fns joodo-ns))
    (render-template "docs/not_found" :joodo-ns joodo-ns-name)))

(defroutes docs-controller
  (GET "/docs" [] (redirect "docs/index"))
  (context "/docs" []
    (GET "/index" [] (render-template "docs/index"))
    (GET "/:joodo-ns-name" [joodo-ns-name] (direct-to-doc-page (url-safe->ns joodo-ns-name)))))
