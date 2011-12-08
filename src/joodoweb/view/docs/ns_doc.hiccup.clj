(let [joodo-ns (:joodo-ns *view-context*)
      ns-info (meta joodo-ns)
	  listed-fns (:joodo-fns *view-context*)]
  (list
    [:h1 (ns-name joodo-ns)]
    [:p (:doc ns-info)]
    [:br][:br]
    [:ul
      (for [current-fn (ns-publics joodo-ns)]
        (if-let [fn-doc (:doc (meta (second current-fn)))]
        (list [:li
					[:div.toggle_documentation
						[:h4 (first current-fn)]]
					[:div.documentation
						[:div.description
							[:p fn-doc]]
						[:div.usage
							[:pre {:class "brush: clojure"}
							((keyword (first current-fn)) ((keyword (ns-name joodo-ns)) usage-docs))]]
						[:div.toggle_source
							[:h5 "Source"]]
						[:div.source
							[:pre {:class "brush: clojure"} 
							(get-source-code (ns-name joodo-ns) (first current-fn))]]]])))]))