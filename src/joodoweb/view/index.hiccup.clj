[:h2 "a web application framework for Clojure"]

[:br]
[:p "Born of a need to build fast, clean web applications, Joodo uniquely satisfies a need of Clojure frameworks. Taking inspiration from the self-discipline of martial arts and the attention to detail of Old World artisans, Joodo is a carefully crafted tool that will aid you in forging the best applications you can imagine."]
[:p "Joodo is an open-source project, free for all, and constantly updated."]

[:h3 "installation"]
[:p "Installing Joodo is as easy as your favorite pie (eating, not baking): all you need is Leiningen and the Joodo plugin. Follow the " [:a {:href "https://github.com/technomancy/leiningen/blob/master/README.md" :target "_blank"} "latest tutorial"] " on Leiningen's github account to acquire the latest version, then visit your terminal to install the Joodo plugin:"]
[:pre {:class "brush: clojure"} "joodo/lein-joodo 0.6.0-SNAPSHOT"]
[:p "The 'joodo' command will be installed on your system in the '~/.lein/bin/' directory. Next, edit the '.profile' (found in your home directory) to include '$HOME/.lein/bin/' in your path. Run the following in your terminal:"]
[:pre {:class "brush: clojure"} "export PATH=$PATH:~/.lein/bin"]
[:p "Restart your terminal to apply these changes, and consider your pie eaten. Delicious, wasn't it?"]

[:h3 "usage"]
[:p "Part of the beauty of Joodo is how easy it is to build a new project. If you run the following command from your terminal (replacing _proj_name_ with the name of your project), Joodo will do all the heavy lifting and create a project for you:"]
[:pre {:class "brush: clojure"} "joodo new _proj_name_"]
[:p "By default, Joodo projects have dependencies on external libraries. Before you run your project, you have to get those libraries. Fortunately, Leiningen makes this as easy as running the following command from your terminal when in your project's directory:"]
[:pre {:class "brush: clojure"} "lein deps"]
[:p "Now you can run your project by running the following command:"]
[:pre {:class "brush: clojure"} "joodo server"]

[:h3 "deployment"]
[:p "If you use git and want to deploy on Heroku, pushing your project to production is as easy as creating a git repo, making a commit, then running the following commands:"]
[:pre {:class "brush: clojure"} "heroku create --stack cedar
git push heroku master"]