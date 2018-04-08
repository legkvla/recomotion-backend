(ns recomotion-backend.web.middleware
  (:require [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.cookies :refer [wrap-cookies]]
            [ring.logger :as logger]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [ring.middleware.file :refer [wrap-file]]

            ; [buddy.auth.backends.token :refer [token-backend]]
            ; [buddy.auth :refer [authenticated? throw-unauthorized]]
            ; [buddy.auth.middleware :refer [wrap-authentication wrap-authorization]]
            [environ.core :refer [env]]
    )
  )

(defn wrap-client-side-routes [handler]
  (fn [req]
    (handler (update-in req [:uri]
      #(if (or (= "/" %) (= "/login" %)) "/index.html" % )))
    )
  )

(defn wrap-dev-middleware [handler]
  (if (env :dev)
    (-> handler
        logger/wrap-with-body-logger
        wrap-reload
      )
    handler
    )
  )

; (def auth-backend
;   (token-backend {
;     :authfn (fn [req token] (sessions/lookup-session token))
;     })
;   )

(defn wrap-middleware [handler]
  (-> handler
    wrap-params
    wrap-cookies
    (wrap-json-body {:keywords? true :bigdecimals? true})
    wrap-json-response
    logger/wrap-with-logger ;todo this mw adds some garbage in logs
    wrap-dev-middleware
    wrap-client-side-routes
    (wrap-file "resources")
    (wrap-file-info)
    ; (wrap-authentication auth-backend)
    ;todo wrap-keyword-params
    )
  )
