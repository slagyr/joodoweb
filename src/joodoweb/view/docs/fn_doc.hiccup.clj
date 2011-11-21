(let [ns-info (meta (:joodo-ns *view-context*))
	  fn-info (meta (:joodo-fn *view-context*))]
  (list
	[:a {:href (str "/docs/" (:name ns-info))} (:name ns-info)]
    [:h2 (:name fn-info)]
    [:p (:doc fn-info)]
  )
)