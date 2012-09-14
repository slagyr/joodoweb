[:h2 "sample application"]
[:p "In this section, we will be building a very simple blogging platform to show you how easy it is to build a site with Joodo. This application will not have any bells or whistles, as it is meant for the sole purpose of teaching you how to use Joodo."]

[:h3 "Creating the App Skeleton"]
[:p "If you have followed our " [:a {:href "/"} "installation instructions"] " creating our starting point is as simple as running the following command, where sample_app is the name you'd like to give your application:"]
[:pre {:class "brush: clojure"} "lein joodo new sample_app"]
[:p "To see what that made for us, change into the sample_app's directory in terminal (" [:b "cd sample_app"] ") and download the project's dependencies:"]
[:pre {:class "brush: clojure"} 
 "lein deps"]
[:p "Now, start the local server with the following command:"]
[:pre {:class "brush: clojure"} "lein joodo server"]
[:p "Then go to " [:a {:href "http://localhost:8080" :target "_blank"} "http://localhost:8080"] " in your favorite browser, where you should see the joodo welcome page."]
[:p "Open up the sample app directory to take a look at the files. Your directory should look like this:"]
[:div.body-image [:img {:src "/images/directory.jpg"}]]
[:p "The heart of any Leiningen project is the " [:b "project.clj"] " file. This file lists information about your project. You can add descriptions, change version numbers, add dependencies, and much more in this file. More info about the project.clj file can be found on the " [:a {:href "https://github.com/technomancy/leiningen/blob/master/README.md" :target "_blank"} "latest tutorial"] " on Leiningen's github account. " [:b "NOTE: "] "The project.clj file in the sample app includes set-up code for both Leiningen 1 and Leiningen 2. Delete the code for the version you are not using."]
[:p "Let's add a logo. All images used by a Joodo application are stored in the public/images/ directory, and your views are located in src/sample_app/view. The latest version of Joodo uses the .hiccup extenstion for all view files, but also supports the older .hiccup.clj extension."]
[:p "Place an image file with your logo into the images directory and then we can display it by editing our " [:b "src/sample_app/view/layout.hiccup.clj"] " file. Add the following inside the body of your layout file:"]
[:pre {:class "brush: clojure"} "[:img {:src \"/images/logo.png\"}]"]
[:p "As you'll notice, your page is displaying your logo and Joodo's starting page. Delete all of the markup in the src/sample_app/view/index.hiccup.clj file to clean up the page."]


[:h3 "The Basics"]
[:p "The most important file in your project is the " [:b "src/root.clj"] "."] 
[:pre {:class "brush: clojure"}
"(ns joodo_sample.core
  (:require [compojure.core :only (defroutes GET)]
            [compojure.route :only (not-found)]
            [joodo.middleware.view-context :only (wrap-view-context)]
            [joodo.views :only (render-template render-html)]
            [joodo.controllers :only (controller-router)]))

(defroutes joodo_sample-routes
  (GET \"/\" [] (render-template \"index\"))
  (controller-router 'joodo_sample.controller)
  (not-found (render-template \"not_found\" :template-root \"joodo_sample/view\" 
                                          :ns `joodo_sample.view.view-helpers)))

(def app-handler
  (->
    joodo_sample-routes
    (wrap-view-context :template-root \"joodo_sample/view\" :ns `joodo_sample.view.view-helpers)))"
]

[:p "By default there are three sections of root.clj. It is important to keep in mind that you can modify/add sections to fit your project's needs. These are just there to get you started."]
[:p "The " [:b "first section"] " declares the file's namespace and lists all of the file's dependencies. If you want a deeper look into what root.clj does you can find the method/macro definitions that are being used in this section."]
[:p "The " [:b "second section"] " calls a macro called defroutes. This macro is responsible for defining the routes of the website. By default it sets a GET request on the '/' route to render a pre-made index page. It also tells the application to render a pre-made 404 page if a non-existent route is accessed."]
[:p "The most interesting part of the second section tells Joodo to look for any files with namespaces starting with 'sample_app.controller and add the routes that they define to the list of routes. We'll cover this more deeply in a later section."]
[:p "The " [:b "third section"] " wraps information around the request. By default, the only property explicitly being wrapped is the view context. It sets the template root to the view directory and sets all of the view's namespaces to a view-helper. It is important to note that the template-root represents the location of view pages with a relative path starting at your project's src directory."]
[:p "Since our sample application will be pretty standard, we don't need to modify this file."]
[:br][:br]
[:a {:href "/tutorial/controllers"} "Next Step -->"]
