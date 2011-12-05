(ns joodoweb.view.view-helpers-spec
	(:use
	    [speclj.core]
	    [joodoweb.view.view-helpers]))
	
(describe "View Helper"
	(it "converts a namespace to a url friendly string"
		(should= "joodo_env" (ns->url 'joodo.env)))
	
	(it "converts a url friendly string to a namespace symbol"
		(should= 'joodo.env (url->ns "joodo_env")))
	
	; (it "converts a function to a url friendly string"
	; 	(should= "development-env-q" (fn->url 'development-env?)))
	; 
	; (it "converts a url friendly string to a function symbol"
	; 	(should= 'development-env? (url->fn "development-env-q")))

	(it "gets the url for the source code of a given namespace"
		; (should= "" (ns->github-url 'joodo.env))
		)
)