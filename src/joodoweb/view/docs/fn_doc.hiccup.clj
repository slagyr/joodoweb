(let [ns-info (ns-name (:joodo-ns *view-context*))
      fn-info (meta (:joodo-fn *view-context*))]
  (list
    [:h2 (:name fn-info)]
    [:a {:href (str "/docs/" (ns->url (ns-name ns-info)))} (str "Back to " (ns-name ns-info))]
    (if (= nil (:doc fn-info))
      [:p "Documentation not found"]
      [:p (:doc fn-info)])
  )
)