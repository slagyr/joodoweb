(let [joodo-ns (:joodo-ns *view-context*)
	  ns-name (ns-name joodo-ns)]
  (list
    [:h2 ns-name]
    [:ul
      (map #(merge [:li [:a {:href (str "/docs/" (ns->url ns-name) "/" (fn->url (first %)))} (first %)]]) (ns-publics joodo-ns))]
  )
)