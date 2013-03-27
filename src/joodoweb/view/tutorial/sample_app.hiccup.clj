[:h2 "sample application"]
[:p "In this section, we will be building a very simple blogging platform to show you how easy it is to build a site with Joodo. This application will not have any bells or whistles, as it is meant for the sole purpose of teaching you how to use Joodo."]


[:h3 "Creating the App Skeleton"]
[:p "If you have followed our " [:a {:href "/"} "installation instructions"] " creating our starting point is as simple as running the following command:"]
[:pre {:class "brush: clojure"} "joodo new sample_app"]
[:p "You can replace sample_app with whatever project name you desire."]
[:p "To see what that made for us, change into the sample_app's directory, download the project's dependencies, start the local server, then go to " [:a {:href "http://localhost:8080" :target "_blank"} "http://localhost:8080"] " in your favorite browser."]
[:pre {:class "brush: clojure"}
"lein deps
joodo server"]
[:p "Now we should add a logo. All images used by a Joodo application are stored in the public/images/ directory. Once you add your image there, we can display it by editing our src/sample_app/view/layout.hiccup.clj file. Add the following inside the body of your layout file:"]
[:pre {:class "brush: clojure"} "[:img {:src \"/images/logo.png\"}]"]
[:p "As you'll notice, your page is displaying your logo and Joodo's starting page. Delete all of the markup in the src/sample_app/view/index.hiccup.clj file to clean up the page."]
[:p "The heart of any Leiningen project is the project.clj file. This file lists information about your project. You can add descriptions, change version numbers, add dependencies, and much more in this file. More info about the project.clj file can be found on the " [:a {:href "https://github.com/technomancy/leiningen/blob/master/README.md" :target "_blank"} "latest tutorial"] " on Leiningen's github account."]


[:h3 "The Basics"]
[:p "The most important file in your project is the src/core.clj. By default there are three sections of core.clj. It is important to keep in mind that you can modify/add sections to fit your project's needs. These are just there to get you started."]
[:p "The first section declares the file's namespace and lists all of the file's dependencies. If you want a deeper look into what core.clj does you can find the method/macro definitions that are being used in this section."]
[:p "The second section calls a macro called defroutes. This macro is responsible for defining the routes of the website. By default it sets a GET request on the '/' route to render a pre-made index page. It also tells the application to render a pre-made 404 page if a non-existent route is accessed."]
[:p "The most interesting part of the second section tells Joodo to look for any files with namespaces starting with 'sample-app.controller and add the routes they define to the list of routes. We'll cover this more deeply in a later section."]
[:p "The third section wraps information around the request. By default, the only property explicitly being wrapped is the view context. It sets the template root to the view directory and sets all of the view's namespaces to a view-helper. It is important to note that the template-root represents the location of view pages with a relative path starting at your project's src directory."]
[:p "Since our sample application will be pretty standard, we don't need to modify this file."]


[:h3 "Building Our Controller"]
[:p "By default our controller directory is empty. However as projects grow, this directory becomes more and more important. The files that go here contain additional routes that are related to each other. For our purposes, we will want to create a posts controller that has all of the routing information for our blog posts."]
[:p "To make our posts controller and posts controller tester, create the files src/sample_app/controller/post_controller.clj and spec/sample_app/controller/post_controller_spec.clj. In that controller tester paste the following code:"]
[:pre {:class "brush: clojure"}
"(ns sample-app.controller.post-controller-spec
  (:use
    [speclj.core
      :only (describe around it should= run-specs)]
    [joodo.spec-helpers.controller
      :only (do-get rendered-template rendered-context with-mock-rendering with-routes)]
    [sample-app.controller.post-controller]))

(describe \"post-controller\"
  (with-mock-rendering)
  (with-routes post-controller)

  (it \"directs to the not_found page if the blog post isn't specified\"
    (let [result (do-get \"/post\")]
      (should= 404 (:status result))
      (should= \"not_found\" @rendered-template)
      (should= \"You must specify a blog post.\" (:error @rendered-context))))
)

(run-specs)"]
[:p "This file uses Speclj along with Joodo spec helpers to test out the post controller page. The call to the with-mock-rendering function tells Joodo to keep track of all files rendered without actually rendering them. The with-routes function tells Joodo to use the routes listed in the specified var (in our case it is post-controller). Within our test we are asserting that if a client sends a get request to the /post uri, they will get a response with a status of 404, the not_found page will display, and an error message will get passed."]
[:p "Now run your tests from your terminal by using the following command:"]
[:pre {:class "brush: clojure"} "lein spec -a"]
[:p "You should get an error message stating that no namespace called sample-app.controller.post-controller exists. Let's remedy that by pasting the following code into the src/sample_app/controller/post_controller.clj file we created:"]
[:pre {:class "brush: clojure"}
"(ns sample-app.controller.post-controller
  (:use
    [compojure.core :only (GET defroutes)]
    [joodo.views :only (render-template)]))

(defn- render-not-found [error-msg]
  {:status 404
   :headers {}
   :body (render-template \"not_found\" :error error-msg)})

(defroutes post-controller
  (GET \"/post\" [] (render-not-found \"You must specify a blog post.\")))"]
[:p "This defines our post-controller var and populates it with one route. When the /post route hits, the response is sent back with a status of 404, a body filled with the data stored in the not_found template, and an error message gets passed through as a flash message."]
[:p "A common mistake for controllers is not starting your route with the controller name. For example, we can't list routes in our post controller that start with anything other than post. Another important thing to note is that if we don't include a var named post-controller, none of our routes will get loaded. So if you had a controller named happy_controller.clj, Joodo will expect a var called happy-controller to exist and contain all of the routes from that controller."]
[:p "Now that we have our first passing test, it is time to create a route that will display the specified blog post. But first, let's write the tests for that code in our existing post-controller-spec:"]
[:pre {:class "brush: clojure"}
"(around [it]
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
[:p "Our new tests assert that if a blog post exists, a route will be created for it. This test fails because there is no such var called blog-post-directory. The around function binds any calls to blog-post-directory to a test_posts directory that we use to create mock blog posts. So let's make a file called 20111215_test-post.hiccup.clj in the /spec/sample_app/view/test_posts directory. After we do that, we are ready to write the controller logic to make those tests pass:"]
[:pre {:class "brush: clojure"}
"(ns sample-app.controller.post-controller
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
[:p "Now if you run the tests they pass. So what have we done so far? We are testing that our controller handles routes for all existing blog post files. Our tests pass because we created a test blog post. To get a real blog post to serve up, lets make a real blog post called 20111215_first-post.hiccup.clj in the /src/sample_app/view/posts directory. Now if you open up your browser with your local server running (type 'joodo server' in the command line) and go to " [:a {:href "http://localhost:8080/post/20111215_first-post" :target "_blank"} "http://localhost:8080/post/20111215_first-post"] ", you will see your blog post."]


[:h3 "Working With Our Views"]
[:p "Next thing we want to do is list all of the blog posts on the index page. To do that, we will interact with our view_helpers file. Before we add any functionality, let's create a view_helpers_spec.clj file in the spec/sample_app/view directory, and fill it with our first test:"]
[:pre {:class "brush: clojure"}
"(ns sample-app.view.view-helpers-spec
  (:use
    [speclj.core :only (describe it should= run-specs around)]
    [sample-app.view.view-helpers]))

(describe \"view_helpers\"
  (it \"gets the title of a post\"
    (should= \"test post 1\"
             (get-post-name \"20111215_test-post-1.hiccup.clj\")))
)

(run-specs)"]
[:p "In this test we are asserting that, given the proper file name, the get-post-name function can extract the title of a given post. Simple enough, let's make that test pass by adjusting our view-helpers file:"]
[:pre {:class "brush: clojure"}
"(ns sample-app.view.view-helpers
  \"Put helper functions for views in this namespace.\"
  (:use
    [joodo.views :only (render-partial *view-context*)]
    [joodo.string :only (gsub)]
    [hiccup.page]
    [hiccup.form]
    [sample-app.controller.post-controller :only (blog-post-filenames)]
    [clojure.string :as string :only (split)]))

(defn get-post-name [post-file-name]
  (gsub
    (second (string/split post-file-name #\"(\\.)|(_)\"))
    #\"-\"
    (fn [_] \" \")))"]
[:p "With that code, we created our get-post-name function and told it to split up the post's file name at any periods or underscores. If the file name is formatted correctly, we will extract our post title. Then with that post title, we use Joodo's gsub function to replace all dashes with empty spaces. Addionally we listed the blog-post-filenames function in our ns use section so that when we make our view, it will have acces to that function. The hiccup.page and hiccup.form are included by default to allow your views to use their convenient helper functions. A list of those functions can be found on " [:a {:href "http://weavejester.github.com/hiccup/" :target "_blank"} "hiccup's documentation website"] "."]
[:p "Next we want to extract the date from our post's file name. So let's adjust the view-helper file to look like the following:"]
[:pre {:class "brush: clojure"}
"(ns sample-app.view.view-helpers-spec
  (:use
    [speclj.core :only (describe it should= run-specs around)]
    [joodo.datetime :only (parse-datetime)]
    [sample-app.view.view-helpers]))

(describe \"view_helpers\"
  (it \"gets the title of a post\"
    (should= \"test post 1\"
             (get-post-name \"20111215_test-post-1.hiccup.clj\")))

  (it \"gets the date of a post\"
    (should= (parse-datetime :dense \"20111215000000\")
             (get-post-date \"20111215_test-post-1.hiccup.clj\")))
)

(run-specs)"]
[:p "In this test, we are assuming proper file name formatting as well. However, instead of expecting a string we are expecting a Java Date Object. We are doing this by using the parse-datetime function from Joodo. We are passing it the value of dense and a string that has the date December 15, 2011 formatted like \"YYYYMMDDHHMMSS\". To make our test pass, let's adjust our view-helper file to match the following:"]
[:pre {:class "brush: clojure"}
"(ns sample-app.view.view-helpers
  \"Put helper functions for views in this namespace.\"
  (:use
    [joodo.views :only (render-partial *view-context*)]
    [joodo.string :only (gsub)]
    [joodo.datetime :only (parse-datetime)]
    [hiccup.page]
    [hiccup.form]
    [sample-app.controller.post-controller :only (blog-post-filenames)]
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
    (str (first (post-parts post-file-name)) \"000000\")))"]
[:p "As you can see, we added the joodo.datetime namespace to our list of namespaces that we are using. Then we extracted the post-parts logic into its own function to avoid duplication. And finally, we created the get-post-date function and set it to return a date object with the extracted year, month, and day with zero for the hour, minute, and second values."]
[:p "Lastly, lets add a helper function that orders puts our posts in order of most recent to oldest. Here is the code that tests our new feature:"]
[:pre {:class "brush: clojure"}
"(ns sample-app.view.view-helpers-spec
  (:use
    [speclj.core :only (describe it should= run-specs around with)]
    [joodo.datetime :only (parse-datetime)]
    [sample-app.view.view-helpers]))

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
)

(run-specs)"]
[:p "As you can see, I refactored the test post string into its own variable to make testing easier. Then I created an assertion that tests the order-posts function. The code to make this test pass is pretty simple too:"]
[:pre {:class "brush: clojure"}
"(defn order-posts [post-file-names]
  (sort-by
    #(Integer/parseInt (first (post-parts %)))
    >
    post-file-names))"]
[:p "If you add the order-posts funciton to the view-helpers.clj file then ran the tests, all of your assertions should pass. All we are doing here is extracting the date from the file name, parsing it into an Integer, then sorting it largest to smallest."]
[:p "The last thing our views will need is the route each post is associated with. To get started on that feature, add the following test to your view-helpers-spec:"]
[:pre {:class "brush: clojure"}
"(it \"gets the route for a specified post\"
  (should= \"/post/20111215_test-post-1\"
           (get-post-route @test-post-1)))"]
[:p "Then to make that test pass, we can add the following function to our view-helpers file."]
[:pre {:class "brush: clojure"}
"(defn get-post-route [post-file-name]
  (let [this-posts-parts (post-parts post-file-name)
        date (first this-posts-parts)
        title (second this-posts-parts)]
    (str \"/post/\" date \"_\" title)))"]
[:p "All this funciton does is use our existing post-parts function to split up the file name and take the pieces we need when creating our route string."]
[:p "Now that all of that is done, we can list our blog posts by adding the following code into our index.hiccup.clj file:"]
[:pre {:class "brush: clojure"}
"[:h1 \"Posts\"]
(for [current-post-filename (order-posts (blog-post-filenames))]
  (list
    [:a {:href (get-post-route current-post-filename)}
        (get-post-name current-post-filename)]
    [:span \" - \" (.toString (get-post-date current-post-filename))]
    [:br]))"]
[:p "This is all of the code we will need to list all of our blog posts on our index page. To see this in action, create some blog posts in the src/sample_app/view/posts/ directory with the expected formatting of YYYYMMDD_post-title.hiccup.clj. Then fire up the local server and look at the site in your favorite browser."]
[:p "The last bit of code we want to add is a bit of error handling. Add the following code to the not_found.hiccup.clj file to display the errors specified by our controller:"]
[:pre {:class "brush: clojure"}
"[:h1 \"Not Found\"]
(if-let [error (:error *view-context*)]
  [:p error])"]

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
[:h4 "spec/sample_app/controller/post_controller_spec.clj"]
[:pre {:class "brush: clojure"}
"(ns sample-app.controller.post-controller-spec
  (:use
    [speclj.core
      :only (describe around it should= run-specs)]
    [joodo.spec-helpers.controller
      :only (do-get rendered-template rendered-context with-mock-rendering with-routes)]
    [sample-app.controller.post-controller]))

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
"(ns sample-app.controller.post-controller
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
"(ns sample-app.view.view-helpers-spec
  (:use
    [speclj.core :only [describe it should= run-specs around with]
    [chee.datetime :only [parse-datetime]
    [sample-app.view.view-helpers]))
 
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
"(ns sample-app.view.view-helpers
  \"Put helper functions for views in this namespace.\"
  (:use
    [joodo.views :only (render-partial *view-context*)]
    [joodo.string :only (gsub)]
    [joodo.datetime :only (parse-datetime)]
    [hiccup.page]
    [hiccup.form]
    [sample-app.controller.post-controller :only (blog-post-filenames)]
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