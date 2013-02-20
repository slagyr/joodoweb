(defproject joodoweb "0.0.1"
  :description "A website deployable to AppEngine"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [joodo "1.1.2"]
                 [speclj "2.5.0"]]
  :dev-dependencies [[speclj "2.5.0"]]
  :test-paths ["spec/"]
  :java-source-paths ["src/"]
  :repl-init-script "config/development/repl_init.clj"
  :profiles {:dev {:dependencies [[speclj "2.5.0"]]}}
  :plugins [[speclj "2.5.0"]
            [joodo/lein-joodo "1.1.2"]]
  :joodo-core-namespace joodoweb.core)
