(let [joodo-ns (:joodo-ns *view-context*)
      ns-info (meta joodo-ns)
	  listed-fns (:joodo-fns *view-context*)]
  (list
    [:h1 (ns-name joodo-ns)]
    [:p (:doc ns-info)]
    [:a {:href (ns->github-url joodo-ns)} "Source Code"]
    [:br][:br]
    [:ul
      (for [current-fn (ns-publics joodo-ns)]
        (if-let [fn-doc (:doc (meta (second current-fn)))]
        (list [:li
					[:div.toggle_description
						[:h4 (first current-fn)]
						[:p.description fn-doc]]
					[:div.toggle_source
						[:h5 "Source"]]
					[:div.source
						[:pre [:code(get-source-code (ns-name joodo-ns) (first current-fn))]]]])))]))