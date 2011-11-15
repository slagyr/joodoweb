(doctype :html5)
[:html
 [:head
  [:meta {:http-equiv "Content-Type" :content "text/html" :charset "iso-8859-1"}]
  [:title "Clojure Web Framework | Joodo"]
  (include-css "/stylesheets/joodoweb.css")
  (include-js "/javascript/joodoweb.js")]
 [:body
  [:div.sidebar
   [:a.title {:href "/"}
    [:h1 "Joodo"]]
   [:ul.navigation
    [:li [:a {:href "/about"} "Why Use Joodo"]]
    [:li [:a {:href "/tutorial"} "Getting Started"]]
    [:li [:a {:href "/docs"} "Documentation"]]
    [:li [:a {:href "/community"} "Community"]]
    [:li [:a {:href "https://github.com/slagyr/joodo/issues" :target "_blank"} "Feature Request"]]]
   [:span.footer
    [:a {:href "/license"} "License Info"]
    " | "
    [:a {:href "https://github.com/slagyr/joodo" :target "_blank"} "Git Repository"]
    [:p "Copyright &copy; 2011 Micah Martin"]
    [:p "All Rights Reserved"]]]
  (if (re-matches #"^/tutorial.*" (or (:uri *request*) ""))
    (list
      [:div.drawer
       [:ul
        [:li [:a {:href "/tutorial/install"} "Installation"]]
        [:li [:a {:href "/tutorial/build-skeleton"} "Build Application Skeleton"]]
        [:li [:a {:href "/tutorial/tour"} "The Grand Tour"]]
        [:li [:a {:href "/tutorial/sample-app"} "Sample Application"]]]]))
  [:div.drawer]
  [:div.content (eval (:template-body joodo.views/*view-context*))]
 ]
]