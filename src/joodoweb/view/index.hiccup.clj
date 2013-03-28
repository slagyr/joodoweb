[:h2 "a web application framework for Clojure"]

[:br]
[:p "Born of a need to build fast, clean web applications, Joodo uniquely satisfies a need of Clojure frameworks. Taking inspiration from the self-discipline of martial arts and the attention to detail of Old World artisans, Joodo is a carefully crafted tool that will aid you in forging the best applications you can imagine."]
[:p "Joodo is an open-source project, free for all, and constantly updated."]

[:h3 "installation"]
[:p "Installing Joodo is as easy as your favorite pie (eating, not baking): all you need is Leiningen and the Joodo plugin. Follow the " [:a {:href "https://github.com/technomancy/leiningen/blob/master/README.md" :target "_blank"} "latest tutorial"] " on Leiningen's github account to acquire the latest version."]
[:p "The 'joodo' command will be installed on your system in the '~/.lein/bin/' directory. You need to make sure that '$HOME/.lein/bin/' is on your path if you didn't do it during the Leiningen installation. Open (or create) the file named '.bash_profile' at the root level of your user folder and add the following line:"]
[:pre {:class "brush: clojure"} "PATH=$PATH:$HOME/.lein/bin"]
[:p "Restart your terminal to apply these changes, then type $PATH at the command prompt to confirm that it now includes '$HOME/.lein/bin'. "] 

[:h4 "Leiningen 2"]
[:p "If you are using Leiningen 2.0 or later you can install the lein-joodo plugin by updating (or creating) your profiles.clj file located inside ~/.lein with the following:"]
[:pre {:class "brush: clojure"}
"{
  :user {:plugins [[joodo/lein-joodo \"1.1.2\"]]}
}"]

[:h4 "Leiningen 1"]
[:p "If you are using Leiningen 1.5 or later you can install Joodo with the following command in your terminal:"]
[:pre {:class "brush: clojure"} "lein plugin install joodo/lein-joodo 0.10.0"]
[:p "Users of Leiningen 1 should also note that they will omit the leading 'lein' from all terminal commands in the " [:a {:href "/tutorial"} "tutorial"] "."]

[:p "Consider your pie eaten. Delicious, wasn't it?"]

[:h3 "usage"]
[:p "Part of the beauty of Joodo is how easy it is to build a new project. If you run the following command from your terminal (replacing _proj_name_ with the name of your project), Joodo will do all the heavy lifting and create a project for you:"]
[:pre {:class "brush: clojure"} "lein joodo new _proj_name_"]
[:p "By default, Joodo projects have dependencies on external libraries. Before you run your project, you have to get those libraries. Fortunately, Leiningen makes this as easy as running the following command from your terminal when in your project's directory:"]
[:pre {:class "brush: clojure"} "lein deps"]
[:p "Now you can run your project by running the following command:"]
[:pre {:class "brush: clojure"} "lein joodo server"]

[:h3 "deployment"]
[:p "If you use git and want to deploy on Heroku, pushing your project to production is as easy as creating a git repo, making a commit, creating a repo on heroku, and pushing to it:"]
[:pre {:class "brush: clojure"}
"git init
git add .
git commit -m \"Initial Commit\"
heroku create --stack cedar
git push heroku master"]