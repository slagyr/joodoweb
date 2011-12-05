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
          [:h4.toggle (first current-fn)]
          [:p.hideable fn-doc]])))]))