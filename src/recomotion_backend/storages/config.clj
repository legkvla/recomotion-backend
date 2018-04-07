(ns recomotion-backend.storages.config
  (:require
    [recomotion-backend.storages.content :refer [content-storage init-content]]
    [recomotion-backend.storages.events :refer [events-storage init-events]]
    )
  )

(defn wrap-storages [handler mongo-db]
  (fn [& args]
    (binding [
      content-storage (init-content mongo-db)
      events-storage (init-events mongo-db)
      impressions-storage (init-impressions mongo-db)
      ]
      (apply handler args)
      )
    )
  )
