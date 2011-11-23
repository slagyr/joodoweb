(let [ns-info (ns-name (:joodo-ns *view-context*))
      fn-info (meta (:joodo-fn *view-context*))]
  (list
    [:h2 (:name fn-info)]
    [:p "Namespace: " [:a {:href (str "/docs/" (ns->url (ns-name ns-info)))} (ns-name ns-info)]]
    [:p "File: " [:a {:href (str "https://github.com/slagyr/joodo/blob/master/joodo/src/" (:file fn-info))} (:file fn-info)]]
    (if (= nil (:doc fn-info))
      [:p "There is no documentation for the function you specified. To create documentation fork Joodo on GitHub, make the documentation, then send us a pull request."]
      [:p "Documentation: " (:doc fn-info)])
  )
)