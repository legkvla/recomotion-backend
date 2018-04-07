(ns recomotion-backend.storages.events
  (:require
    [recomotion-backend.storages.repository :refer :all]
    [recomotion-backend.storages.mongo :refer [mongo-repository]]
    )
  )

(defrecord Event [id content-id user-id event-kind time])

(def ^:dynamic events-storage)

(defn init-events [mongo-db]
  ;Issue if use keyword (str) didn't help somewhy
  (-> (mongo-repository mongo-db "events")
    (fk-index [:content-id] false)
    (fk-index [:user-id] false)
    )
  )

(defn create-event [content-id user-id event-kind time]
  (save events-storage
    {
      :content-id content-id
      :user-id user-id
      :event-kind event-kind
      :time time
      }
    )
  )
