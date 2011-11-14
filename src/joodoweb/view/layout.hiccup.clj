(doctype :html5)
[:html
 [:head
  [:meta {:http-equiv "Content-Type" :content "text/html" :charset "iso-8859-1"}]
  [:title "Clojure Web Framework | Joodo"]
  (include-css "/stylesheets/joodoweb.css")
  (include-js "/javascript/joodoweb.js")]
 [:body
  [:a {:href "/"}
    [:h1 "Joodo"]
    [:h2 "Clojure Web Framework"]]
  [:ul
   [:li [:a {:href "/about"} "Why Use Joodo"]]
   [:li [:a {:href "/tutorial"} "Getting Started"]
    (if (re-matches #"^/tutorial.*" (or (:uri *request*) ""))
      (list
       	[:ul
         [:li [:a {:href "/tutorial/install"} "Installation"]]
         [:li [:a {:href "/tutorial/make-app"} "Making your first app"]]
         [:li [:a {:href "/tutorial/tour"} "The Grand Tour"]]]))]
   [:li [:a {:href "/docs"} "Documentation"]]
   [:li [:a {:href "/community"} "Community"]]
   [:li [:a {:href "https://github.com/slagyr/joodo/issues" :target "_blank"} "Feature Request"]]]
  (eval (:template-body joodo.views/*view-context*))
]]