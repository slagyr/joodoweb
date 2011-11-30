(let [joodo-ns (:joodo-ns *view-context*)
      ns-info (meta joodo-ns)
	  ns-name (ns-name joodo-ns)]
  (list
    [:h2 (format-namespace ns-name)]
    [:p (:doc ns-info)]
    [:ul
      (map
	    #(if (not= nil (:doc (meta (second %))))
	      (merge [:li [:a {:href (str "/docs/" (ns->url ns-name) "/" (fn->url (first %)))} (first %)]]))
        (ns-publics joodo-ns))]
  )
)