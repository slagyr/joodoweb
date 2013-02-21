[:h3 "All Done"]
[:p "That's all there is to creating our sample application. We hope this was enough to get you familiar with the Joodo framework. There are a lot of features that we didn't cover. To get more familair with other parts of Joodo, review our " [:a {:href "/docs/index"} "API Documentation"] " section. If you have any ideas for how this site or Joodo can be better, join our " [:a {:href "/community"} "community"] " and let us know."]
[:p "Just for your reference, here are all of the files that we outlined throughout this tutorial:"]
[:h4 "src/sample_app/view/layout.hiccup.clj"]
[:pre {:class "brush: clojure"}
"(doctype :html5)
[:html
 [:head
  [:meta {:http-equiv \"Content-Type\" :content \"text/html\" :charset \"iso-8859-1\"}]
  [:title \"sample-app\"]
  (include-css \"/stylesheets/sample_app.css\")
  (include-js \"/javascript/sample_app.js\")]
 [:body
  (eval (:template-body joodo.views/*view-context*))
]]"]
[:h4 "public/images/logo.png"]
[:img {:src "/images/logo.png"}]
[:h4 "spec/sample_app/view/test_posts/20111215_test-post.hiccup"]
[:pre {:class "brush: clojure"}
"[:h1 \"Test Post\"]
[:p \"This is my test post.\"]"]
[:h4 "spec/sample_app/controller/post_controller_spec.clj"]
[:pre {:class "brush: clojure"}
"(ns sample_app.controller.post-controller-spec
  (:require [speclj.core :refer [describe around it should= run-specs]]
            [joodo.spec-helpers.controller :refer [do-get rendered-template rendered-context 
                                                   with-mock-rendering with-routes]]
            [sample_app.controller.post-controller :refer [post-controller blog-post-directory]]))

(describe \"post-controller\"
  (with-mock-rendering)
  (with-routes post-controller)
  
  (it \"directs to the not_found page if the blog post isn't specified\"
    (let [result (do-get \"/post\")]
      (should= 404 (:status result))
      (should= \"not_found\" @rendered-template)
      (should= \"You must specify a blog post.\" (:error @rendered-context))))
  
  (around [it]
    (binding [blog-post-directory (clojure.java.io/file (str
              (. (java.io.File. \".\") getCanonicalPath)
              \"/spec/sample_app/view/test_posts\"))]
      (it)))
  
  (it \"directs to the blog post if the post exists\"
    (let [result (do-get \"/post/20111215_test-post\")]
      (should= 200 (:status result))
      (should= \"posts/20111215_test-post\" @rendered-template)))

  (it \"directs to the not_found page if the post doesn't exist\"
    (let [result (do-get \"/post/20111215_invalid\")]
      (should= 404 (:status result))
      (should= \"not_found\" @rendered-template)
      (should= \"Blog post does not exist.\" (:error @rendered-context)))))"]
[:h4 "src/sample_app/controller/post_controller.clj"]
[:pre {:class "brush: clojure"}
"(ns sample_app.controller.post-controller
  (:require [compojure.core :refer [GET context defroutes]]
            [joodo.views :refer [render-template]]))

(def ^{:private true} current-path
  (. (java.io.File. \".\") getCanonicalPath))
 
(def ^:dynamic blog-post-directory
  (clojure.java.io/file (str current-path \"/src/sample_app/view/posts\")))
 
(defn blog-post-filenames []
  (map
    #(.getName %)
    (remove
      #(.isDirectory %)
      (file-seq blog-post-directory))))
 
(defn- blog-post-exists? [post-route]
  (some #(= % (str post-route \".hiccup\")) (blog-post-filenames)))

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
  (:require [speclj.core :refer [describe it should= run-specs around with]]
            [sample_app.view.view-helpers :refer [get-post-name get-post-date order-posts
                                                get-post-route]]
            [chee.datetime :refer [parse-datetime]]))

(describe \"view_helpers\"
  (with test-post-1 \"20111215_test-post-1.hiccup\")
  (with test-post-2 \"20111216_test-post-2.hiccup\")
  (with test-post-3 \"20111217_test-post-3.hiccup\")

  (it \"gets the title of a post\"
    (should= \"test post 1\"
             (get-post-name \"20111215_test-post-1.hiccup\")))

  (it \"gets the date of a post\"
    (should= (parse-datetime :dense \"20111215000000\")
             (get-post-date \"20111215_test-post-1.hiccup\")))

  (it \"orders posts from most recent to oldest\"
    (should= [@test-post-3 @test-post-2 @test-post-1]
             (order-posts [@test-post-2 @test-post-1 @test-post-3])))
  
  (it \"gets the route for a specified post\"
    (should= \"/post/20111215_test-post-1\"
             (get-post-route @test-post-1))))"]
[:h4 "src/sample_app/view/view_helpers.clj"]
[:pre {:class "brush: clojure"}
"(ns sample_app.view.view-helpers
  (:require
    [joodo.views :refer [render-partial *view-context*]]
    [chee.string :refer [gsub]]
    [chee.datetime :refer [parse-datetime]]
    [hiccup.page :refer :all]
    [hiccup.form :refer :all]
    [sample_app.controller.post-controller :refer [blog-post-filenames]]
    [clojure.string :as string :refer [split]]))

(defn- post-parts [post-file-name]
  (string/split post-file-name #\"(\\.)|(_)\"))

(defn get-post-name [post-file-name]
  (gsub
    (second (string/split post-file-name #\"(\\.)|(_)\"))
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
"[:div
 [:h1 \"Posts\"]
  (for [current-post-filename (order-posts (blog-post-filenames))]
    (list
      [:a {:href (get-post-route current-post-filename)}
          (get-post-name current-post-filename)]
      [:span \" - \" (.toString (get-post-date current-post-filename))]
      [:br]))]"]
[:h4 "src/sample_app/view/not_found.hiccup.clj"]
[:pre {:class "brush: clojure"}
"[:h1 \"Not Found\"]
(if-let [error (:error *view-context*)]
  [:p error])"]
[:br][:br]
[:a {:href "/tutorial/views"} "<-- Previous Step"]