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
[:p "The most interesting part of the second section tells Joodo to look for any files with namespaces starting with 'sample_app.controller and add the routes they define to the list of routes. We'll cover this more deeply in a later section."]
[:p "The third section wraps information around the request. By default, the only property explicitly being wrapped is the view context. It sets the template root to the view directory and sets all of the view's namespaces to a view-helper. It is important to note that the template-root represents the location of view pages with a relative path starting at your project's src directory."]
[:p "Since our sample application will be pretty standard, we don't need to modify this file."]
[:br][:br]
[:a {:href "/tutorial/controllers"} "Next Step -->"]