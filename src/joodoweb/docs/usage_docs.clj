(ns joodoweb.docs.usage_docs)

(def usage-docs {
  	:joodo.controllers {
		:resolve-controller
		"(resolve-controller 'sample-app.the-missing-controller)"
		:controller-router
		"(controller-router 'sample-app.controller)"
	}

	:joodo.env {
    :alter-env!
    "(alter-env!)"
		:*env*
		"(get @*env* :joodo-env)"
		:env
		"(env :joodo-env)"
		:development?
		"(development?)"
		:production?
		"(production?)"
    :load-config
    "(load-config path-to-joodo-config)"
    :load-config?
    "(load-config? path-to-config)"
    :load-configurations
    "(load-configurations path-to-config)"
    :load-configurations-unchecked
    "(load-config-unchecked path-to-config)"
	}

  :joodo.middleware.favicon {
      :wrap-favicon-bouncer
      ";Include wrap-favicon-bouncer in your app-handler:\n(def app-handler (-> app-routes wrap-favicon-bouncer))\n; Now your app will respond to all favicon requests with a 404 file not found"
   }

	:joodo.middleware.keyword-cookies {
		:wrap-keyword-cookies
		";Include wrap-keyword-cookies in your app-handler:\n(def app-handler (-> app-routes wrap-keyword-cookies))\n;Then you can create a cookie with the following syntax:\n(assoc\n  (redirect \"/route\")\n  :cookies {:cookie-name {:value \"stuff\" :path \"/\" :expires (days-from-now 30)}})\n;To access the cookie info, do the following:\n(:cookie-name (:cookies *request*))"
	}

	:joodo.middleware.locale {
		:uri
		";Using the defroutes macro from Compojure\n(defroutes app-routes\n  (GET \"/\" [] \"<h1>Hello world</h1>\")\n  (route/not-found \"<h1>not found</h1>\"))\n\n(def app-handler\n  (->\n    app-routes\n    (wrap-locale\n      :locale-augmenters [joodo.middleware.locale/uri]\n      :accepted-locales #{\"en-us\" \"fr\"})))"
		:cookie
		";Using the defroutes macro from Compojure\n(defroutes app-routes\n  (GET \"/\" [] \"<h1>Hello world</h1>\")\n  (route/not-found \"<h1>not found</h1>\"))\n\n(def app-handler\n  (->\n    app-routes\n    (wrap-locale\n      :locale-augmenters [joodo.middleware.locale/cookie]\n      :accepted-locales #{\"en-us\"})))"
		:wrap-locale
		";Include wrap-locale in your app-handler:\n(def app-handler (-> app-routes wrap-locale))"
		:accept-header
		";Using the defroutes macro from Compojure\n(defroutes app-routes\n  (GET \"/\" [] \"<h1>Hello world</h1>\")\n  (route/not-found \"<h1>not found</h1>\"))\n\n(def app-handler\n  (->\n    app-routes\n    (wrap-locale\n      :locale-augmenters [joodo.middleware.locale/accept-header]\n      :accepted-locales #{\"en-us\"})))"
		:query-param
		";Using the defroutes macro from Compojure\n(defroutes app-routes\n  (GET \"/\" [] \"<h1>Hello world</h1>\")\n  (route/not-found \"<h1>not found</h1>\"))\n\n(def app-handler\n  (->\n    app-routes\n    (wrap-locale\n      :locale-augmenters [joodo.middleware.locale/query-param]\n      :accepted-locales #{\"en-us\" \"fr\"})))"
	}

	:joodo.middleware.refresh {
		:wrap-refresh
		";Include wrap-refresh in your app-handler:\n(def app-handler (-> app-routes wrap-refresh))"
    :handler
    ";Include refresh in your main defroutes.  Its argument should be an unqualified namespace and function name:\n(defroutes app-routes (handler 'full-namespace.more-namespace/function-name))"
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

  :joodo.middleware.util {
    :attempt-wrap
    ";Include attempt-wrap in your app-handler:\n(def app-handler (-> app-routes (wrap-bind-request middleware-to-wrap))"
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
		"(do-post \"/uri\")"
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
