[:h3 "Working With Our Views"]
[:p "Next thing we want to do is list all of the blog posts on the index page. To do that, we will interact with our view_helpers file. Before we add any functionality, let's create a " [:b "view_helpers_spec.clj"] " file in the " [:b "spec/sample_app/view"] " directory, and fill it with our first test:"]
[:pre {:class "brush: clojure"}
"(ns sample_app.view.view-helpers-spec
  (:require [speclj.core :refer [describe it should= run-specs around]]
            [sample_app.view.view-helpers :refer [get-post-name]]))

(describe \"view_helpers\"
  (it \"gets the title of a post\"
    (should= \"test post 1\"
             (get-post-name \"20111215_test-post-1.hiccup\"))))"]

[:p "In this test we are asserting that, given the proper file name, the " [:b "get-post-name"] " function can extract the title of a given post. Simple enough, let's make that test pass by adjusting our view-helpers file:"]
[:pre {:class "brush: clojure"}
"(ns sample_app.view.view-helpers
  \"Put helper functions for views in this namespace.\"
  (:require [joodo.views :refer [render-partial *view-context*]]
            [chee.string :refer [gsub]]
            [hiccup.page-helpers :refer :all]
            [hiccup.form-helpers :refer :all]
            [sample_app.controller.post-controller :refer [blog-post-filenames]]
            [clojure.string :as string :refer [split]]))

(defn get-post-name [post-file-name]
  (gsub
    (second (string/split post-file-name #\"(\\.)|(_)\"))
    #\"-\"
    (fn [_] \" \")))"]
[:p "With that code, we created our " [:b "get-post-name"] " function and told it to split up the post's file name at any periods or underscores. If the file name is formatted correctly, we will extract our post title. Then with that post title, we use chee's gsub function ("[:a {:href "https://github.com/slagyr/joodo"} "chee"] " is a component of Joodo) to replace all dashes with empty spaces. Addionally we listed the blog-post-filenames function in our " [:b "ns :require" ] " section so that when we make our view, it will have access to that function. The " [:b "hiccup.page-helpers"] " and " [:b "hiccup.form-helpers"] " are included by default to allow your views to use their convenient helper functions. A list of those functions can be found on " [:a {:href "http://weavejester.github.com/hiccup/" :target "_blank"} "hiccup's documentation website"] "."]
[:p "Next we want to extract the date from our post's file name. So let's adjust the view_helper_spec file to look like the following, adding " [:b "get-post-date"] " to the " [:b "sample_app.view.view-helpers"] " 'refer' vector:"]
[:pre {:class "brush: clojure"}
"(ns sample_app.view.view-helpers-spec
  (:require [speclj.core :refer [describe it should= run-specs around]]
            [chee.datetime :refer [parse-datetime]]
            [sample_app.view.view-helpers :refer [get-post-name get-post-date]]))

(describe \"view_helpers\"
  (it \"gets the title of a post\"
    (should= \"test post 1\"
             (get-post-name \"20111215_test-post-1.hiccup\")))

  (it \"gets the date of a post\"
    (should= (parse-datetime :dense \"20111215000000\")
             (get-post-date \"20111215_test-post-1.hiccup\"))))
"]
[:p "In this test, we are assuming proper file name formatting as well. However, instead of expecting a string we are expecting a Java Date Object. We are doing this by using the parse-datetime function from " [:a {:href "https://github.com/slagyr/joodo/tree/master/chee"} "Chee"]". We are passing it the value of dense and a string that has the date December 15, 2011 formatted like \"YYYYMMDDHHMMSS\". To make our test pass, let's adjust our view-helper file to match the following:"]
[:pre {:class "brush: clojure"}
"(ns sample_app.view.view-helpers
  \"Put helper functions for views in this namespace.\"
  (:require [joodo.views :refer [render-partial *view-context*]]
            [chee.string :refer [gsub]]
            [chee.datetime :refer [parse-datetime]]
            [hiccup.page-helpers :refer :all]
            [hiccup.form-helpers :refer :all]
            [sample_app.controller.post-controller :refer [blog-post-filenames]]
            [clojure.string :as string :refer [split]]))

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
[:p "As you can see, we added the " [:b "chee.datetime"] " namespace to our list of namespaces that we are using. Then we extracted the post-parts logic into its own function to avoid duplication. And finally, we created the get-post-date function and set it to return a date object with the extracted year, month, and day with zero for the hour, minute, and second values."]
[:p "Lastly, lets add a helper function that orders puts our posts in order of most recent to oldest. Here is the code that tests our new feature:"]
[:pre {:class "brush: clojure"}
"(ns sample_app.view.view-helpers-spec
  (:require [speclj.core :only (describe it should= run-specs around with)]
            [joodo.datetime :only (parse-datetime)]
            [sample_app.view.view-helpers :refer [get-post-name get-post-date
                                                  order-posts]]))

(describe \"view_helpers\"
  (with test-post-1 \"20111215_test-post-1.hiccup\")
  (with test-post-2 \"20111216_test-post-2.hiccup\")
  (with test-post-3 \"20111217_test-post-3.hiccup\")

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
[:p "As you can see, I refactored the test post string into its own variable to make testing easier. I also added the " [:b "with"] " function to the speclj.core 'refer' vector. Then I created an assertion that tests the order-posts function. The code to make this test pass is pretty simple too:"]
[:pre {:class "brush: clojure"}
"(defn order-posts [post-file-names]
  (sort-by
    #(Integer/parseInt (first (post-parts %)))
    >
    post-file-names))"]
[:p "If you add the order-posts function to the view-helpers.clj file then ran the tests, all of your assertions should pass. All we are doing here is extracting the date from the file name, parsing it into an Integer, then sorting it largest to smallest."]
[:p "The last thing our views will need is the route each post is associated with. To get started on that feature, add the following test to your view-helpers-spec, and remember to add the new function we're testing " [:b "get-post-route"] "to the 'refer' vector:"]
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
[:p "All this function does is use our existing post-parts function to split up the file name and take the pieces we need when creating our route string."]
[:p "Now that all of that is done, we can list our blog posts by adding the following code into our index.hiccup.clj file:"]
[:pre {:class "brush: clojure"}
"[:h1 \"Posts\"]
(for [current-post-filename (order-posts (blog-post-filenames))]
  (list
    [:a {:href (get-post-route current-post-filename)}
        (get-post-name current-post-filename)]
    [:span \" - \" (.toString (get-post-date current-post-filename))]
    [:br]))"]
[:p "This is all of the code we will need to list all of our blog posts on our index page. To see this in action, create some blog posts in the src/sample_app/view/posts/ directory with the expected formatting of YYYYMMDD_post-title.hiccup. Then fire up the local server and look at the site in your favorite browser."]
[:p "The last bit of code we want to add is a bit of error handling. Add the following code to the not_found.hiccup.clj file to display the errors specified by our controller:"]
[:pre {:class "brush: clojure"}
"[:h1 \"Not Found\"]
(if-let [error (:error *view-context*)]
  [:p error])"]
[:br][:br]
[:a {:href "/tutorial/controllers"} "<-- Previous Step"]
[:span " | "]
[:a {:href "/tutorial/reference"} "Next Step -->"]