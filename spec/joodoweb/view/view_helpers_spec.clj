(ns joodoweb.view.view-helpers-spec
	(:use
	    [speclj.core]
	    [joodoweb.view.view-helpers]))
	
(describe "View Helper"
	(it "converts a namespace to a url friendly string"
		(should= "joodo_env" (ns->url-safe 'joodo.env)))
	
	(it "converts a url friendly string to a namespace symbol"
		(should= 'joodo.env (url-safe->ns "joodo_env")))

	(it "gets the url for the source code of a given namespace"
		(should=
			"https://github.com/slagyr/joodo/blob/master/joodo/src/joodo/keyword_cookies.clj"
			(ns->github-url 'joodo.keyword-cookies)))
)