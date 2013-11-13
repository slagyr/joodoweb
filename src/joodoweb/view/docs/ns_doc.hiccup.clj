(let [joodo-ns (:joodo-ns *view-context*)
      ns-info (meta joodo-ns)
      listed-fns (:joodo-fns *view-context*)]
  (list
    [:h1 (ns-name joodo-ns)]
    [:p (:doc ns-info)]
    [:br ] [:br ]
    [:ul {}
     (for [current-fn listed-fns]
       (if-let [fn-doc (or (:doc (meta (second current-fn))) "undocumented")]
         (list [:li [:div.toggle_documentation [:h4 (first current-fn)]]
                ;[:div.documentation
                [:div {}
                 [:div.description [:p fn-doc]]
                 [:div.usage {}
                  [:pre {:class "brush: clojure"}
                   ((keyword (first current-fn)) ((keyword (ns-name joodo-ns)) usage-docs))]]
                 [:div.toggle_source [:h5 "source"]]
                 [:div ;[:div.source
                  [:pre {:class "brush: clojure"}
                   (get-source-code (ns-name joodo-ns) (first current-fn))]]]])))]))
