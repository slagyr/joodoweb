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
	}
	
	:joodo.env {}
	:joodo.middleware.flash {}
	:joodo.middleware.keyword-cookies {}
	:joodo.middleware.multipart-params {}
	:joodo.middleware.refresh {}
	:joodo.middleware.request {}
	:joodo.middleware.servlet-session {}
	:joodo.middleware.verbose {}
	:joodo.middleware.view-context {}
	:joodo.spec-helpers.controller {}
	:joodo.spec-helpers.view {}
	:joodo.string {}
	:joodo.views {}
})