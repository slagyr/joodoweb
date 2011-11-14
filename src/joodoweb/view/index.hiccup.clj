[:div {:style "text-align: center;"}
 [:img {:src "/images/joodo.png"}]
 [:h1 "Welcomes You!"]]

[:h3 "Intro"]
[:p "Gaeshi is a clojure framework for building Google AppEngine sites. The project home is at "
 [:a {:href "https://github.com/slagyr/joodo"} "https://github.com/slagyr/joodo"] "."
 "It consists of 3 parts"
 [:ul
  [:li [:b "Kuzushi"] "- which means 'breaking balance', is a Leiningen plugin that supplies all the Geashi commands."]
  [:li [:b "Tsukuri"] "- which means 'entry', is a library containing all the development tools."]
  [:li [:b "Kake"] "- which means 'execution', is the runtime library that contains all the main APIs."]]]

[:h3 "License"]
[:p "Copyright (C) 2011 Micah Martin All Rights Reserved."]
[:p "Distributed under the The MIT License."]

[:p "This app was generated for joodoweb."]


[:pre "joodo new abc
	creating file:       abc/project.clj
	creating file:       abc/Procfile
	creating directory:  abc/public
	creating directory:  abc/public/images
	creating file:       abc/public/images/joodo.png
	creating directory:  abc/public/javascript
	creating file:       abc/public/javascript/abc.js
	creating directory:  abc/public/stylesheets
	creating file:       abc/public/stylesheets/abc.css
	creating directory:  abc/spec
	creating directory:  abc/spec/abc
	creating file:       abc/spec/abc/core_spec.clj
	creating directory:  abc/src
	creating directory:  abc/src/abc
	creating file:       abc/src/abc/core.clj
	creating directory:  abc/src/abc/controller
	creating directory:  abc/src/abc/model
	creating directory:  abc/src/abc/view
	creating file:       abc/src/abc/view/view_helpers.clj
	creating file:       abc/src/abc/view/layout.hiccup.clj
	creating file:       abc/src/abc/view/index.hiccup.clj
	creating file:       abc/src/abc/view/not_found.hiccup.clj
	creating directory:  abc/config
	creating file:       abc/config/environment.clj
	creating file:       abc/config/development.clj
	creating file:       abc/config/production.clj"]


[:span "joodo server
Cleaning up.
Exception in thread \"main\" java.lang.NoClassDefFoundError: joodo/kake/JoodoServer
Caused by: java.lang.ClassNotFoundException: joodo.kake.JoodoServer
	at java.net.URLClassLoader$1.run(URLClassLoader.java:202)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.net.URLClassLoader.findClass(URLClassLoader.java:190)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:306)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:301)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:247)"]


[:span "lein deps
[INFO] snapshot joodo:joodo:0.6.0-SNAPSHOT: checking for updates from clojars
Copying 17 files to /Users/dcmoore/Projects/abc/lib
Copying 4 files to /Users/dcmoore/Projects/abc/lib/dev"]


[:h2 "Installation"]
[:p "Getting started with Joodo is really easy. Since Joodo is integrated with Leiningen it only takes a few steps to get running on your system. Before you can install Joodo, you need to download Leiningen and get the Joodo plugin. To download Leiningen, go through the latest tutorial listed on https://github.com/technomancy/leiningen/blob/master/README.md"]
[:p "Once you have Leiningen properly installed on your system, the next step is to get the Joodo plugin. If you run the command"]
[:code "joodo/lein-joodo 0.6.0-SNAPSHOT"]
[:p "then the Joodo command will get installed on your system in the ~/.lein/bin/ directory. If ~/.lein/bin/ is already apart of your $PATH, then you can use Joodo now. If it isn't all you have to do is edit your .profile file (in your home directory) to include $HOME/.lein/bin/ in your path."]

[:h2 "Making your first app"]
[:p "Part of the beauty of Joodo is how easy it is to build a new project. If you run the following command from your terminal (replacing _proj_name_ with the name of your project), Joodo will do all the heavy lifting and create a project for you:"]
[:code "joodo new _proj_name_"]
[:p "By default, Joodo projects have dependencies on external libraries. Before you run your project, you have to get those libraries. Fortunately, Leiningen makes this as easy as running the following command from your terminal when in your project's directory:"]
[:code "lein deps"]
[:p "Now you can run your project by running the following command:"]
[:code "joodo server"]

[:h2 "The Grand Tour"]
[:p "Because of the amount of functionality that Joodo comes with out of the box, understaning how everthing works can be a little overwhelming if it is your first time seeing a Joodo project. This section will give an overview of the important parts of any Joodo project."]
[:p "The heart of any Leiningen project is the project.clj file. This file lists information about your project. You can add descriptions, change version numbers, add dependencies, and much more in this file. More info about the project.clj file can be found here: https://github.com/technomancy/leiningen/blob/master/README.md"]
[:p "The next most important file is the core.clj file located in your src/_proj_name_/ directory."]