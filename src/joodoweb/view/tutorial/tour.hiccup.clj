[:h2 "the grand tour"]
[:p "Because of the amount of functionality that Joodo comes with out of the box, understanding how everything works can be a little overwhelming if it is your first time seeing a Joodo project. This section will give an overview of the important parts of any Joodo project."]

[:h3 "project.clj"]
[:p "The heart of any Leiningen project is the project.clj file. This file lists information about your project. You can add descriptions, change version numbers, add dependencies, and much more in this file. More info about the project.clj file can be found on the " [:a {:href "https://github.com/technomancy/leiningen/blob/master/README.md" :target "_blank"} "latest tutorial"] " on Leiningen's github account."]

[:h3 "core.clj"]
[:p "The next most important file is the core.clj file buried in your src/ directory. By default there are three sections of core.clj. It is important to keep in mind that you can modify/add sections to fit your project's needs. These are just there to get you started."]
[:p "The first section declares the file's namespace and lists all of the file's dependencies. If you want a deeper look into what core.clj does you can find the method/macro definitions that are being used in this section."]
[:p "The second section calls a macro called defroutes. This macro is responsible for defining the routes of the website. By default it sets a GET request on the '/' route to render a pre-made index page. It also tells the application to render a pre-made 404 page if a non-existent route is accessed."]
[:p "The most interesting part of the second section tells Joodo to look for any files with namespaces starting with '_project_name_.controller and add the routes they define to the list of routes. More about controllers can be found in the controllers section below."]
[:p "The third section wraps information around the request. By default, the only property being wrapped is the view context. It sets the template root to the view directory and sets all of the view's namespaces to a view-helper."]

[:h3 "controller directory"]
[:p "By default this directory is empty. However as your project grows, this directory will become more and more important. The files that go here contain additional routes that are related to each other. For example, in this website there is a controller called tutorial_controller.clj that holds the routes for all of the tutorial pages."]
[:p "All files in this directory should have an extension of _controller.clj if it wants Joodo to load it as a controller. Additionally, the namespace for the controller should be joodo.controller.name-controller (where name is replaced by your controller's). Then just define your routes using the defroutes macro. Just make sure to name the defroutes macro call name-controller (again replacing name with the name of the controller)."]

[:h3 "model directory"]
[:p "This directory is empty by default as well. If your application requires the ability to store data, all of that logic can go in this directory. At the moment, Joodo makes no assumptions as to how you want to store data."]

[:h3 "view directory"]
[:p "Files in the view directory are used to create html that will be rendered in browsers and viewed by your visitors. This is done by using a markup language called " [:a {:href "https://github.com/weavejester/hiccup" :target "_blank"} "hiccup"] "."]
[:p "By default, the file called layout.hiccup.clj is set to be the layout for every page. The body content gets inserted at the (eval (:template-body joodo.views/*view-context*)) statement."]
[:p "If you want to make these pages dynamic, you can write clojure logic directly in the hiccup file. If you want to limit the amount of logic in your views you can extract logic into your view_helpers.clj file and access those functions in all of your views."]

[:h3 "helper functions"]
[:p "Joodo supplies many functions that handle the most common behaviors of a web application. They were built specifically for controllers, models, views, and tests. The best way to get familiar with these type of functions is to dive into the " [:a {:href "/docs"} "documentation"] "."]