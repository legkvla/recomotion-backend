(ns recomotion-backend.storages.config
  (:require
    [recomotion-backend.storages.events :refer [events-storage init-events]]
    )
  )

(defn wrap-storages [handler mongo-db]
  (fn [& args]
    (binding [
      events-storage (init-events mongo-db)
      content-storage (init-content mongo-db)
      ]
      (apply handler args)
      )
    )
  )
