(doctype :html5)
[:html
 [:head
  [:meta {:http-equiv "Content-Type" :content "text/html" :charset "iso-8859-1"}]
  [:title "Clojure Web Framework | Joodo"]
  (include-css "/stylesheets/joodoweb.css")
  (include-css "/stylesheets/reset.css")
  (include-js "/javascript/joodoweb.js")]
 [:body
  [:div.sidebar
   [:a.title {:href "/"}
    [:h1 "Joodo"]]
   [:ul.navigation
    [:a {:href "/about"} [:li [:span "Why Use Joodo"]]]
    [:a {:href "/tutorial"} [:li [:span "Getting Started"]]]
    [:a {:href "/docs"} [:li [:span "Documentation"]]]
    [:a {:href "/community"} [:li [:span "Community"]]]
    [:a {:href "https://github.com/slagyr/joodo/issues" :target "_blank"} [:li [:span "Feature Request"]]]]
   [:span.footer
    [:a {:href "/license"} "License Info"]
    " | "
    [:a {:href "https://github.com/slagyr/joodo" :target "_blank"} "Git Repository"]
    [:p "Copyright &copy; 2011 Micah Martin"]
    [:p "All Rights Reserved"]]]
   [:div.content
    (if (re-matches #"^/tutorial.*" (or (:uri *request*) ""))
      (list
        [:div.drawer
         [:ul
          [:li [:a {:href "/tutorial/install"} "Installation"]]
          [:li [:a {:href "/tutorial/build-skeleton"} "Build Application Skeleton"]]
          [:li [:a {:href "/tutorial/tour"} "The Grand Tour"]]
          ; [:li [:a {:href "/tutorial/sample-app"} "Sample Application"]]
          ]]))
    (eval (:template-body joodo.views/*view-context*))]
 ]
]