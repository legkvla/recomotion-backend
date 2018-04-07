(ns recomotion-backend.web.handler
  (:require
    [compojure.core :refer [GET POST DELETE PUT defroutes context]]
    [compojure.route :refer [not-found resources]]
    [environ.core :refer [env]]
    [ring.util.response :refer
      [response status charset content-type set-cookie redirect]]

    [recomotion-backend.web.middleware :refer [wrap-middleware]]
    )
  )

(defroutes service-routes
  (context "/api" []
    
    )
  )

(defroutes app
  (wrap-middleware service-routes)
  )
