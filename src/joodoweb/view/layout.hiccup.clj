(doctype :html5)
[:html
 [:head
  [:meta {:http-equiv "Content-Type" :content "text/html" :charset "iso-8859-1"}]
  [:link {:rel "shortcut icon" :href "images/favicon.ico"}]
  [:title "Joodo | Clojure Web Framework"]
  (include-css "/stylesheets/joodoweb.css")
  (include-css "/stylesheets/reset.css")
  (include-js "/javascript/joodoweb.js")]
 [:body
  [:table.body [:tr
   [:td.sidebar
    [:a.title {:href "/"}
     [:img {:src "/images/logo.png" :alt "Joodo"}]]
    [:ul.navigation
     [:a {:href "/docs"} [:li [:span "api documentation"]]]
     [:a {:href "/tutorial"} [:li [:span "sample application"]]]
     [:a {:href "/about"} [:li [:span "about"]]]
     [:a {:href "/community"} [:li [:span "community"]]]]
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
         (map
           #(merge [:li
	         (if (= (str "/docs/" (ns->url %)) (or (:uri *request*) ""))
               [:a.selected {:href (str "/docs/" (ns->url %))} (format-namespace %)]
               [:a {:href (str "/docs/" (ns->url %))} (format-namespace %)])])
           documented-namespaces)]]))
   [:td.content
    (eval (:template-body joodo.views/*view-context*))]
  ]]
 ]
]