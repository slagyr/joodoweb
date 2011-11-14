(use 'joodo.env)

(def environment {
  :joodo.core.namespace "joodoweb.core"
  ; environment settings go here
  })

(swap! *env* merge environment)