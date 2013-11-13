(defproject joodoweb "0.0.1"
  :description "A website deployable to AppEngine"
  :dependencies [[org.clojure/clojure "1.5.1"]
;                 [joodo "1.2.2"]
                 [joodo "2.0.0"]
                 [speclj "2.8.0"]]
  :dev-dependencies [[speclj "2.5.0"]]
  :min-lein-version "2.0.0"
  :test-paths ["spec/"]
  :java-source-paths ["src/"]
  :repl-init-script "config/development/repl_init.clj"
  :profiles {:dev {:dependencies [[speclj "2.8.0"]]}}
  :plugins [[speclj "2.8.0"]
;            [joodo/lein-joodo "1.1.2"]
            [lein-ring "0.8.8"]]
;  :joodo-core-namespace joodoweb.core
  :ring {:handler joodoweb.core/app}
  )
