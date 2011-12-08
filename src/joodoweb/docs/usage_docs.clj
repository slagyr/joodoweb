(ns joodoweb.docs.usage_docs)

(def usage-docs {
  	:joodo.controllers {
		:resolve-controller
		"(resolve-controller 'sample_app.the-missing-controller)"
		:controller-router
		"(controller-router 'sample_app.controller)"
	}

	:joodo.datetime {
		:now
		"(now)"
		:datetime
		"(datetime 2011 12 28)\n(datetime 2011 12 28 1)\n(datetime 2011 12 28 1 38)\n(datetime 2011 12 28 1 38 59)"
		:before?
		"(before? (datetime 2011 1 1) (datetime 2011 1 2))"
		:after?
		"(after? (datetime 2011 1 3) (datetime 2011 1 2))"
		:between?
		"(between? (datetime 2011 1 2) (datetime 2011 1 1) (datetime 2011 1 3))"
		:seconds
		"(seconds 5)"
		:minutes
		"(minutes 5)"
		:hours
		"(hours 5)"
		:days
		"(days 5)"
		:months
		"(months 5)"
		:years
		"(years 5)"
		:to-calendar
		"(to-calendar (Date.))"
		:before
		"(before (now) (seconds 5))"
		:after
		"(after (now) (seconds 5))"
		:seconds-ago
		"(seconds-ago 5)"
		:seconds-from-now
		"(seconds-from-now 5)"
		:minutes-ago
		"(minutes-ago 5)"
		:minutes-from-now
		"(minutes-from-now 5)"
		:hours-ago
		"(hours-ago 5)"
		:hours-from-now
		"(hours-from-now 5)"
		:days-ago
		"(days-ago 5)"
		:days-from-now
		"(days-from-now 5)"
		:months-ago
		"(months-ago 5)"
		:months-from-now
		"(months-from-now 5)"
		:years-ago
		"(years-ago 5)"
		:years-from-now
		"(years-from-now 5)"
		:parse-datetime
		"(parse-datetime :http \"Sun, 06 Nov 1994 08:49:37 GMT\")\n(parse-datetime :rfc1123 \"Sun, 06 Nov 1994 08:49:37 GMT\")\n(parse-datetime :iso8601 \"1994-11-06 08:49:12 GMT\")\n(parse-datetime :dense \"19941106084912\")\n(parse-datetime (SimpleDateFormat. \"MMM d, yyyy HH:mm\") \"Nov 6, 1994 08:49\")\n(parse-datetime \"MMM d, yyyy HH:mm\" \"Nov 6, 1994 08:49\")"
		:format-datetime
		"(format-datetime :http (Date.))\n(format-datetime :rfc1123 (Date.))\n(format-datetime :iso8601 (Date.))\n(format-datetime :dense (Date.))\n(format-datetime (SimpleDateFormat. \"MMM d, yyyy HH:mm\") (Date.))\n(format-datetime \"MMM d, yyyy HH:mm\" (Date.))"
		:year
		"(year (now))"
		:month
		"(month (now))"
		:day
		"(day (now))"
	}
	
	:joodo.env {
		:*env*
		"(get @*env* :joodo-env)"
		:env
		"(env :joodo-env)"
		:development-env?
		"(development-env?)"
		:production-env?
		"(production-env?)"
	}
	
	:joodo.middleware.flash {
		:wrap-flash
		";Include wrap-flash in your app-handler:\n(def app-handler (-> app-routes wrap-flash))\n;Then you can append flash messages with the following syntax:\n(assoc\n  (redirect \"/route\")\n  :flash {:errors [\"Error Message 1\" \"Error Message 2\"]})\n;To access the flash data, do the following:\n(:flash *request*)"
	}
	
	:joodo.middleware.keyword-cookies {
		:wrap-keyword-cookies
		";Include wrap-keyword-cookies in your app-handler:\n(def app-handler (-> app-routes wrap-keyword-cookies))\n;Then you can create a cookie with the following syntax:\n(assoc\n  (redirect \"/route\")\n  :cookies {:cookie-name {:value \"stuff\" :path \"/\" :expires (days-from-now 30)}})\n;To access the cookie info, do the following:\n(:cookie-name (:cookies *request*))"
	}
	
	:joodo.middleware.multipart-params {
		:wrap-multipart-params
		";Include wrap-multipart-params in your app-handler:\n(def app-handler (-> app-routes wrap-multipart-params))"
	}
	
	:joodo.middleware.refresh {
		:wrap-refresh
		";To use, include wrap-refresh in your app-handler:\n(def app-handler (-> app-routes wrap-refresh))"
	}
	
	:joodo.middleware.request {
		:*request*
		";Use it like any old map:\n(:params *request*)"
		:wrap-bind-request
		";Include wrap-bind-request in your app-handler:\n(def app-handler (-> app-routes wrap-bind-request))\n;Then all request information gets loaded in the *request* var."
	}
	
	:joodo.middleware.servlet-session {
		:wrap-servlet-session
		";Include wrap-servlet-session in your app-handler:\n(def app-handler (-> app-routes wrap-servlet-session))\n;Then you can create a session with the following syntax\n(assoc\n  (redirect \"/route\")\n  :session {:session-name {:value \"stuff\" :expires (hours-from-now 1)}})\n;To access session info, use the following syntax:\n(:session *request*)"
	}
	
	:joodo.middleware.verbose {
		:wrap-verbose
		";Include wrap-verbose in your app-handler:\n(def app-handler (-> app-routes wrap-verbose))"
	}
	
	:joodo.middleware.view-context {
		:wrap-view-context
		";Include wrap-view-context in your app-handler:\n(def app-handler (-> app-routes (wrap-view-context :template-root \"app_name/view\" :ns `app_name.view.view-helpers)))\n;To bind information to *view-request* use the following syntax:\n(render-template \"template_name\" :data data)\n;Then access that information in the view pages like it were a map:\n(:data *view-context*)"
	}
	
	:joodo.spec-helpers.controller {
		:*routes*
		"(*routes* {:request-method :get :uri \"/uri\"})"
		:with-routes
		"(with-routes sample-controller)"
		:request
		"(request :get \"/uri\")"
		:do-get
		"(do-get \"/uri\")"
		:do-post
		"(do-get \"/uri\")"
		:rendered-template
		"@rendered-template"
		:rendered-html
		"@rendered-html"
		:rendered-context
		"@rendered-context"
		:mock-render-template
		"(mock-render-template \"template_name\" :optional-data \"additional data\")"
		:mock-render-html
		"(mock-render-html \"<html><body><p>Sup Bro?</p></body></html>\" :optional-data \"additional data\")"
		:with-mock-rendering
		"(with-mock-rendering)"
		:should-redirect-to
		"(should-redirect-to (do-get \"/uri\") \"/other-uri\")"
	}
	
	:joodo.spec-helpers.view {
		:tag-matches?
		"(tag-matches? {:tag \"match\" :other \"tag\"} {:tag \"match\"})"
		:find-tag
		"(find-tag (rendered-template \"template\") {:id \"something\"})"
		:rendered-html
		"(rendered-html \"template\")"
		:rendered-hiccup
		"(rendered-hiccup \"template\")"
		:rendered-template
		"(rendered-template \"template\")"
		:should-have-tag
		"(should-have-tag (rendered-template \"template\") {:tag :h1, :content [\"Welcome To My Site!\"]})"
		:with-view-context
		"(with-view-context :template-root \"sample_app/view\" :ns `sample_app.view.view-helpers)"
	}
	
	:joodo.string {
		:gsub
		"(gsub \"1-2-3\" #\"\\-\" (fn [_] \".\"))"
	}
	
	:joodo.views {
		:*view-context*
		";Use it like any old map:\n(:template-root *veiw-context*)"
		:render-hiccup
		"(render-hiccup `[:p foo])"
		:render-html
		"(render-html \"<html><body><p>Sup Bro?</p></body></html>\")"
		:render-template
		"(render-template \"template\")"
		:render-partial
		"(render-partial \"partial\")"
	}
})