(doctype :html5)
[:html
 [:head
  [:meta {:http-equiv "Content-Type" :content "text/html" :charset "iso-8859-1"}]
  [:title "joodoweb"]
  (include-css "/stylesheets/joodoweb.css")
  (include-js "/javascript/joodoweb.js")]
 [:body
  (eval (:template-body joodo.views/*view-context*))
]]