[:h2 "making your first app"]
[:p "Part of the beauty of Joodo is how easy it is to build a new project. If you run the following command from your terminal (replacing _proj_name_ with the name of your project), Joodo will do all the heavy lifting and create a project for you:"]
[:pre {:class "brush: clojure"} "joodo new _proj_name_"]
[:p "By default, Joodo projects have dependencies on external libraries. Before you run your project, you have to get those libraries. Fortunately, Leiningen makes this as easy as running the following command from your terminal when in your project's directory:"]
[:pre {:class "brush: clojure"} "lein deps"]
[:p "Now you can run your project by running the following command:"]
[:pre {:class "brush: clojure"} "joodo server"]

[:h3 "a common mistake"]
[:p "Many times, people forget to get the libraries Joodo depends before they try running their server. You know you made that mistake if you get the following error when trying to start your server:"]

[:pre {:class "brush: clojure"} "Exception in thread \"main\" java.lang.NoClassDefFoundError: joodo/kake/JoodoServer
Caused by: java.lang.ClassNotFoundException: joodo.kake.JoodoServer
	at java.net.URLClassLoader$1.run(URLClassLoader.java:202)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.net.URLClassLoader.findClass(URLClassLoader.java:190)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:306)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:301)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:247)"]
	
[:p "To remedy this, simply run"]
[:pre {:class "brush: clojure"} "lein deps"]
[:p "and then start your server again."]