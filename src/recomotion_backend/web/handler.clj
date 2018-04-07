(ns recomotion-backend.web.handler
  (:require
    [compojure.core :refer [GET POST DELETE PUT defroutes context]]
    [compojure.route :refer [not-found resources]]
    [environ.core :refer [env]]
    [ring.util.response :refer
      [response status charset content-type set-cookie redirect]]

    [recomotion-backend.storages.events :as events]
    [recomotion-backend.web.middleware :refer [wrap-middleware]]
    )
  )

(defroutes service-routes
  (context "/api" []
    (POST "/events" {:keys [body]}
      (do
        (events/create-event
          (:content-id body)
          (:user-id body)
          (:event-kind body)
          (:time body)
          )
        (response {:status :ok})
        )
      )
    )
  )

(defroutes app
  (wrap-middleware service-routes)
  )
