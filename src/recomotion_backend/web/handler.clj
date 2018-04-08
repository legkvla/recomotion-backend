(ns recomotion-backend.web.handler
  (:require
    [compojure.core :refer [GET POST DELETE PUT defroutes context]]
    [compojure.route :refer [not-found resources]]
    [environ.core :refer [env]]
    [ring.util.response :refer
      [response status charset content-type set-cookie redirect]]

    [recomotion-backend.web.middleware :refer [wrap-middleware]]

    [recomotion-backend.storages.content :as content]
    [recomotion-backend.storages.events :as events]

    [recomotion-backend.recommendations :as recommendations]
    [recomotion-backend.web.templates.dashboard :as dashboard]
    )
  )

(defroutes service-routes
  (context "/api" []
    (POST "/events" {:keys [body]}
      (do
        (recommendations/on-event
          (events/create-event
            (:content-id body)
            (:user-id body)
            (:event-kind body)
            (:facial-expression body)
            (:time body)
            )
          )
        (response {:status :ok})
        )
      )
    (GET "/content/:content-id/events" {:keys [params]}
      (events/lookup-events-per-content (:content-id params))
      )
    (GET "/users/:user-id/events" {:keys [params]}
      (events/lookup-events-per-user (:user-id params))
      )
    (GET "/recommendations/:user-id" {:keys [params]}
      (recommendations/recommendations (:user-id params))
      )
    )
  (context "/site" []
    (GET "/dashboard" []
      (dashboard/dashboard)
      )
    )
  )

(defroutes app
  (wrap-middleware service-routes)
  )
