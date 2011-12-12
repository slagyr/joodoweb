[:h3 "All Done"]
[:p "That's all there is to creating our sample application. We hope this was enough to get you familiar with the Joodo framework. There are a lot of features that we didn't cover. To get more familair with other parts of Joodo, review our " [:a {:href "/docs/index"} "API Documentation"] " section. If you have any ideas for how this site or Joodo can be better, join our " [:a {:href "/community"} "community"] " and let us know."]
[:p "Just for your reference, here are all of the files that we outlined throughout this tutorial:"]
[:h4 "src/sample_app/view/layout.hiccup.clj"]
[:pre {:class "brush: clojure"}
"(doctype :html5)
[:html
 [:head
  [:meta {:http-equiv \"Content-Type\" :content \"text/html\" :charset \"iso-8859-1\"}]
  [:title \"sample_app\"]
  (include-css \"/stylesheets/sample_app.css\")
  (include-js \"/javascript/sample_app.js\")]
 [:body
  [:img {:src \"/images/logo.png\"}]
  (eval (:template-body joodo.views/*view-context*))
]]"]
[:h4 "public/images/logo.png"]
[:img {:src "/images/logo.png"}]
[:h4 "spec/sample_app/view/test_posts/20111215_test-post.hiccup.clj"]
[:pre {:class "brush: clojure"}
"[:h1 \"Test Post\"]
[:p \"This is my test post.\"]"]
[:h4 "spec/sample_app/controller/post_controller_spec.clj"]
[:pre {:class "brush: clojure"}
"(ns sample_app.controller.post-controller-spec
  (:use
    [speclj.core
      :only (describe around it should= run-specs)]
    [joodo.spec-helpers.controller
      :only (do-get rendered-template rendered-context with-mock-rendering with-routes)]
    [sample_app.controller.post-controller]))

(describe \"post-controller\"
  (with-mock-rendering)
  (with-routes post-controller)

  (around [it]
    (binding [blog-post-directory (clojure.java.io/file (str
               (. (java.io.File. \".\") getCanonicalPath)
               \"/spec/sample_app/view/test_posts\"))]
      (it)))

  (it \"directs to the not_found page if the blog post isn't specified\"
    (let [result (do-get \"/post\")]
      (should= 404 (:status result))
      (should= \"not_found\" @rendered-template)
      (should= \"You must specify a blog post.\" (:error @rendered-context))))

  (it \"directs to the blog post if the post exists\"
    (let [result (do-get \"/post/20111215_test-post\")]
      (should= 200 (:status result))
      (should= \"posts/20111215_test-post\" @rendered-template)))

  (it \"directs to the not_found page if the post doesn't exist\"
    (let [result (do-get \"/post/20111215_invalid\")]
      (should= 404 (:status result))
      (should= \"not_found\" @rendered-template)
      (should= \"Blog post does not exist.\" (:error @rendered-context))))
)
 
(run-specs)"]
[:h4 "src/sample_app/controller/post_controller.clj"]
[:pre {:class "brush: clojure"}
"(ns sample_app.controller.post-controller
  (:use
    [compojure.core :only (GET context defroutes)]
    [joodo.views :only (render-template)]))
 
(def ^{:private true} current-path
  (. (java.io.File. \".\") getCanonicalPath))
 
(def blog-post-directory
  (clojure.java.io/file (str current-path \"/src/sample_app/view/posts\")))
 
(defn blog-post-filenames []
  (map
    #(.getName %)
    (remove
      #(.isDirectory %)
      (file-seq blog-post-directory))))
 
(defn- blog-post-exists? [post-route]
  (some #(= % (str post-route \".hiccup.clj\")) (blog-post-filenames)))
 
(defn- render-not-found [error-msg]
  {:status 404
   :headers {}
   :body (render-template \"not_found\" :error error-msg)})
 
(defn- render-post [post-route]
  (if (blog-post-exists? post-route)
    (render-template (str \"posts/\" post-route))
    (render-not-found \"Blog post does not exist.\")))
 
(defroutes post-controller
  (GET \"/post\" [] (render-not-found \"You must specify a blog post.\"))
  (context \"/post\" []
    (GET \"/:post-route\" [post-route] (render-post post-route))))"]
[:h4 "spec/sample_app/view/view_helpers_spec.clj"]
[:pre {:class "brush: clojure"}
"(ns sample_app.view.view-helpers-spec
  (:use
    [speclj.core :only (describe it should= run-specs around with)]
    [joodo.datetime :only (parse-datetime)]
    [sample_app.view.view-helpers]))
 
(describe \"view_helpers\"
  (with test-post-1 \"20111215_test-post-1.hiccup.clj\")
  (with test-post-2 \"20111216_test-post-2.hiccup.clj\")
  (with test-post-3 \"20111217_test-post-3.hiccup.clj\")
 
  (it \"gets the title of a post\"
    (should= \"test post 1\" (get-post-name @test-post-1)))
 
  (it \"gets the date of a post\"
    (should=
      (parse-datetime :dense \"20111215000000\")
      (get-post-date @test-post-1)))
 
  (it \"orders posts from most recent to oldest\"
    (should= [@test-post-3 @test-post-2 @test-post-1]
             (order-posts [@test-post-2 @test-post-1 @test-post-3])))

  (it \"gets the route for a specified post\"
    (should= \"/post/20111215_test-post-1\"
             (get-post-route @test-post-1)))
)
 
(run-specs)"]
[:h4 "src/sample_app/view/view_helpers.clj"]
[:pre {:class "brush: clojure"}
"(ns sample_app.view.view-helpers
  \"Put helper functions for views in this namespace.\"
  (:use
    [joodo.views :only (render-partial *view-context*)]
    [joodo.string :only (gsub)]
    [joodo.datetime :only (parse-datetime)]
    [hiccup.page-helpers]
    [hiccup.form-helpers]
    [sample_app.controller.post-controller :only (blog-post-filenames)]
    [clojure.string :as string :only (split)]))
 
(defn- post-parts [post-file-name]
  (string/split post-file-name #\"(\\.)|(_)\"))
 
(defn get-post-name [post-file-name]
  (gsub
    (second (post-parts post-file-name))
    #\"-\"
    (fn [_] \" \")))
 
(defn get-post-date [post-file-name]
  (parse-datetime :dense
    (str (first (post-parts post-file-name)) \"000000\")))

(defn order-posts [post-file-names]
  (sort-by
    #(Integer/parseInt (first (post-parts %)))
    >
    post-file-names))

(defn get-post-route [post-file-name]
  (let [this-posts-parts (post-parts post-file-name)
        date (first this-posts-parts)
        title (second this-posts-parts)]
    (str \"/post/\" date \"_\" title)))"]
[:h4 "src/sample_app/view/index.hiccup.clj"]
[:pre {:class "brush: clojure"}
"[:h1 \"Posts\"]
(for [current-post-filename (order-posts (blog-post-filenames))]
  (list
    [:a {:href (get-post-route current-post-filename)}
        (get-post-name current-post-filename)]
    [:span \" - \" (.toString (get-post-date current-post-filename))]
    [:br]))"]
[:h4 "src/sample_app/view/not_found.hiccup.clj"]
[:pre {:class "brush: clojure"}
"[:h1 \"Not Found\"]
(if-let [error (:error *view-context*)]
  [:p error])"]
[:br][:br]
[:a {:href "/tutorial/views"} "<-- Previous Step"]