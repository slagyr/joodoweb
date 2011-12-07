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