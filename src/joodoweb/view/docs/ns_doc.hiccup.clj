(let [joodo-ns (:joodo-ns *view-context*)
      ns-info (meta joodo-ns)
	  listed-fns (:joodo-fns *view-context*)]
  (list
    [:h2 (ns-name joodo-ns)]
    [:p "Source: " [:a {:href (str "https://github.com/slagyr/joodo/blob/master/joodo/src/" ns-info)} (meta (first listed-fns))]]
    [:p (:doc ns-info)]
    [:ul
      (map
	    #(if (not= nil (:doc (meta (second %))))
	      (merge [:li [:a {:href (str "/docs/" (ns->url ns-name) "/" (fn->url (first %)))} (first %)]]))
        (ns-publics joodo-ns))]
  )
)