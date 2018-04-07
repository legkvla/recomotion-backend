(ns recomotion-backend.storages.impressions
  (:require
    [recomotion-backend.storages.repository :refer :all]
    [recomotion-backend.storages.mongo :refer [mongo-repository]]
    )
  )


(defrecord impression [id content-id user-id kind facial-expression time])

(def ^:dynamic impressions-storage)

(defn init-impressions [mongo-db]
  ;Issue if use keyword (str) didn't help somewhy
  (-> (mongo-repository mongo-db "events")
    (fk-index [:content-id :user-id] true)
    )
  )

(defn lookup-impression [content-id user-id]
  (first
    (find-all impressions-storage {:content-id content-id :user-id user-id} nil)
    )
  )

(defn update-impression [content-id user-id score]
  (if-let [impression (lookup-impression content-id user-id)]
    (apply-change impressions-storage content-id :set :score score)
    (save impressions-storage
      {
        :content-id content-id
        :user-id user-id
        :score score
        }
      )
    )
  )
