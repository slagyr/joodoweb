[:h3 "Building Our Controller"]
[:p "By default our controller directory is empty. However as projects grow, this directory becomes more and more important. The files that go here contain additional routes that are related to each other. For our purposes, we will want to create a posts controller that has all of the routing information for our blog posts."]
[:p "To make our posts controller and posts controller tester, create these two files: " [:b "src/sample_app/controller/post_controller.clj"] " and " [:b "spec/sample_app/controller/post_controller_spec.clj"]]
[:p "In the controller tester paste the following code:"]
[:pre {:class "brush: clojure"}
"(ns sample_app.controller.post-controller-spec
  (:require [speclj.core :refer [describe around it should= run-specs]]
            [joodo.spec-helpers.controller :refer [do-get rendered-template rendered-context
                                                   with-mock-rendering with-routes]]
            [sample_app.controller.post-controller :refer [post-controller]]))

(describe \"post-controller\"
  (with-mock-rendering)
  (with-routes post-controller)

  (it \"directs to the not_found page if the blog post isn't specified\"
    (let [result (do-get \"/post\")]
      (should= 404 (:status result))
      (should= \"not_found\" @rendered-template)
      (should= \"You must specify a blog post.\" (:error @rendered-context)))))"]

[:p "This file uses Speclj along with Joodo spec helpers to test out the post controller page. The call to the 'with-mock-rendering' function tells Joodo to keep track of all files rendered without actually rendering them. The with-routes function tells Joodo to use the routes listed in the specified var (in our case it is post-controller). We also need to ':refer' to the controller functions we'll be using to test our controller."]
[:p "Within our test we are asserting that if a client sends a get request to the /post uri, they will get a response with a status of 404, the not_found page will display, and an error message will get passed."]
[:p "Now run your tests from the terminal by using the " [:b "lein spec -a"] " command"]
[:p "You should get an error message stating that no namespace called sample_app.controller.post-controller exists. Let's remedy that by pasting the following code into the " [:b "src/sample_app/controller/post_controller.clj"] " file we created:"]
[:pre {:class "brush: clojure"}
"(ns sample_app.controller.post-controller
  (:require [compojure.core :refer (GET defroutes)]
            [joodo.views :refer (render-template)]))

(defn- render-not-found [error-msg]
  {:status 404
   :headers {}
   :body (render-template \"not_found\" :error error-msg)})

(defroutes post-controller
  (GET \"/post\" [] (render-not-found \"You must specify a blog post.\")))"]
[:p "This defines our post-controller var and populates it with one route. When the " [:b "/post"] " route hits, the response is sent back with a status of 404, a body filled with the data stored in the not_found template, and an error message gets passed through as a flash message."]
[:p "A common mistake for controllers is not starting your route with the controller name. For example, we can't list routes in our post controller that start with anything other than post. Another important thing to note is that if we don't include a var named post-controller, none of our routes will get loaded. So if you had a controller named happy_controller.clj, Joodo will expect a var called happy-controller to exist and contain all of the routes from that controller."]
[:p "Now that we have our first passing test, it is time to create a route that will display the specified blog post. But first, let's add the tests for that code to our existing post-controller-spec:"]
[:pre {:class "brush: clojure"}
";* Add 'blog-post-directory' to the refer array for sample_app.controller.post-controller
;* at the top of your post_controller_spec.clj file.

;* Also, make sure to create your test_post directory as described in the instructions above
;* to make these tests to pass.

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
    (should= \"Blog post does not exist.\" (:error @rendered-context))))"]
[:p "Our new tests assert that if a blog post exists, a route will be created for it. This test fails because there is no such var called blog-post-directory. The around function binds any calls to blog-post-directory to a test_posts directory that we use to create mock blog posts."]
[:p "Let's make a file called " [:b "20111215_test-post.hiccup"] " in the " [:b "/spec/sample_app/view/test_posts"] " directory. After we do that, we are ready to write the controller logic to make those tests pass. Update the code in your post-controller so that it now looks like this:"]
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
[:p "Now if you run the tests they pass. So what have we done so far?"]
[:p "We are testing that our controller handles routes for all existing blog post files. Our tests pass because we created a test blog post. Note that we had to explicitly declare the blog-post-directory var " [:b "^:dynamic"] " so that we could re-bind it in our test. This is a change in Clojure 1.2. In earlier versions vars were dynamic by default."]
[:p "To get a real blog post to serve up, lets make a real blog post called " [:b "20111215_first-post.hiccup"] " in the " [:b "/src/sample_app/view/posts"] " directory. Now if you open up your browser with your local server running (type " [:b "lein joodo server"] " in the command line) and go to " [:a {:href "http://localhost:8080/post/20111215_first-post" :target "_blank"} "http://localhost:8080/post/20111215_first-post"] ", you will see your blog post."]
[:br][:br]
[:a {:href "/tutorial/basics"} "<-- Previous Step"]
[:span " | "]
[:a {:href "/tutorial/views"} "Next Step -->"]
