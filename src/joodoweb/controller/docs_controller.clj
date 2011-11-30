(ns joodoweb.controller.docs-controller
  (:use
    [compojure.core]
    [ring.util.response :only (redirect)]
    [joodo.views :only (render-template)]
    [joodoweb.view.view-helpers :only (url->ns url->fn documented-namespaces)]))

(defn- get-ns [joodo-ns-name]
  (try
    (require joodo-ns-name)
    (find-ns joodo-ns-name)
    (catch Exception e
      nil)))

(defn- get-fn [joodo-ns joodo-fn-name]
  (let [pub-fn (ns-publics joodo-ns)]
    (joodo-fn-name pub-fn)))

(defn- direct-to-ns-doc-page [joodo-ns-name]
  (if-let [joodo-ns (get-ns joodo-ns-name)]
    (render-template "docs/ns_doc" :joodo-ns joodo-ns)
    (render-template "docs/not_found" :joodo-ns joodo-ns-name)))

(defn- direct-to-fn-doc-page [joodo-ns-name joodo-fn-name]
  (if-let [joodo-ns (get-ns joodo-ns-name)]
    (if-let [joodo-fn (get-fn joodo-ns joodo-fn-name)]
      (render-template "docs/fn_doc" :joodo-ns joodo-ns :joodo-fn joodo-fn)
      (render-template "docs/not_found" :joodo-ns joodo-ns-name :joodo-fn joodo-fn-name))
    (render-template "docs/not_found" :joodo-ns joodo-ns-name :joodo-fn joodo-fn-name)))

(defroutes docs-controller
  (GET "/docs" [] (redirect "docs/index"))
  (context "/docs" []
    (GET "/index" [] (render-template "docs/index"))
    (GET "/:joodo-ns-name" [joodo-ns-name] (direct-to-ns-doc-page (url->ns joodo-ns-name)))
    (GET "/:joodo-ns-name/:joodo-fn-name" [joodo-ns-name joodo-fn-name] (direct-to-fn-doc-page (url->ns joodo-ns-name) (url->fn joodo-fn-name)))))