[:h2 "Latest Version of Joodo"]

[:h3 "Installation"]
[:p "The latest version of Joodo may have exposed a small bug in Leiningen. Therefore, an extra step is required to install the latest version of Joodo."]
[:p "Follow the " [:a {:href "https://github.com/technomancy/leiningen/blob/master/README.md" :target "_blank"} "latest tutorial"] " on Leiningen's github account to acquire the latest version, then visit your terminal to install the Joodo plugin:"]
[:pre {:class "brush: clojure"} "lein plugin install joodo/lein-joodo 0.7.1"]
[:p "The 'joodo' command will be installed on your system in the '~/.lein/bin/' directory. However due to a minor glitch in Leiningen, the joodo command's classpath will need to be altered. To fix this, open up ~/.lein/bin/joodo in an editor and replace '/Users/micahmartin' with '$HOME'."]
[:p "Next, edit the '.profile' (found in your home directory) to include '$HOME/.lein/bin/' in your path. Restart your terminal to apply these changes, and you're done."]