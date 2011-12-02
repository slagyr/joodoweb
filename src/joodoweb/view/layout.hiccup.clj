(doctype :html5)
[:html
 [:head
  [:meta {:http-equiv "Content-Type" :content "text/html" :charset "iso-8859-1"}]
  [:link {:rel "shortcut icon" :href "images/favicon.ico"}]
  [:title "Clojure Web Framework | Joodo"]
  (include-css "/stylesheets/joodoweb.css")
  (include-css "/stylesheets/reset.css")
  (include-js "/javascript/joodoweb.js")]
 [:body
  [:table.body [:tr
   [:td.sidebar
    [:a.title {:href "/"}
     [:img {:src "/images/logo.png" :alt "Joodo"}]]
    [:ul.navigation
     [:a {:href "/docs"} [:li [:span "documentation"]]]
     [:a {:href "/about"} [:li [:span "why joodo?"]]]
     [:a {:href "/tutorial"} [:li [:span "getting started"]]]
     [:a {:href "/community"} [:li [:span "community"]]]
     [:a {:href "https://github.com/slagyr/joodo/issues" :target "_blank"} [:li [:span "feature request"]]]]
    [:span.footer
     [:a {:href "/license"} "License Info"]
     " | "
     [:a {:href "https://github.com/slagyr/joodo" :target "_blank"} "Git Repository"]
     [:p "Copyright &copy; 2011 Micah Martin"]
     [:p "All Rights Reserved"]]]
   (if (re-matches #"^/tutorial.*" (or (:uri *request*) ""))
     (list
       [:td.drawer
        [:ul
         [:li [:a {:href "/tutorial/install"} "installation"]]
         [:li [:a {:href "/tutorial/build-skeleton"} "build application skeleton"]]
         [:li [:a {:href "/tutorial/tour"} "the grand tour"]]
         [:li [:a {:href "/tutorial/sample-app"} "sample application"]]]]))
   (if (re-matches #"^/docs.*" (or (:uri *request*) ""))
     (list
       [:td.drawer
        [:ul
         (map #(merge [:li [:a {:href (str "/docs/" (ns->url %))} (format-namespace %)]]) documented-namespaces)]]))
   [:td.content
    (eval (:template-body joodo.views/*view-context*))]
  ]]
 ]
]