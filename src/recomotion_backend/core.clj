(ns recomotion-backend.core
  (:require [recomotion-backend.web.handler :refer [app]]
            [environ.core :refer [env]]
            [ring.adapter.jetty :refer [run-jetty]]

            [recomotion-backend.storages.config :refer [wrap-storages]]
            [recomotion-backend.storages.mongo :refer [init-mongo-db]]
    )

  (:gen-class)
  )

(defn -main
  "Jetty bootstrap"
  [& args]
  (run-jetty
    (wrap-storages app (init-mongo-db))
    {:port (Integer/parseInt (or (env :port) "3000")) :join? false}
    )
  (println "recomotion-backend started")
  )
