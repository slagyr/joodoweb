[:h2 "sample application"]

[:p "Coming soon"]

; [:p "
; 
; 
; joodo new sample_blog
; lein deps
; joodo server
; lein spec -a
; 
; Change to layout.hiccup.clj
; -Replace joodo.png in public/images/
; -
; 
; $$$$$ Common mistake for controllers $$$$$
; not starting your route with the controller name
; not naming your defroutes macro with the correct name
; 
; "]


; do a screencast
; joodo new sample_blog
; store posts as partials

; user=> (def f (clojure.java.io/file (str *compile-path* "/../src/joodoweb/view/docs")))
; #'user/f
; user=> (def fs (file-seq f))                                                                   
; #'user/fs
; user=> fs
; (#<File /Users/dcmoore/Projects/joodoweb/src/joodoweb/view/docs> #<File /Users/dcmoore/Projects/joodoweb/src/joodoweb/view/docs/.DS_Store> #<File /Users/dcmoore/Projects/joodoweb/src/joodoweb/view/docs/fn_doc.hiccup.clj> #<File /Users/dcmoore/Projects/joodoweb/src/joodoweb/view/docs/index.hiccup.clj> #<File /Users/dcmoore/Projects/joodoweb/src/joodoweb/view/docs/not_found.hiccup.clj> #<File /Users/dcmoore/Projects/joodoweb/src/joodoweb/view/docs/ns_doc.hiccup.clj>)