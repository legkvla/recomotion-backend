(ns recomotion-backend.storages.events
  (:require
    [recomotion-backend.storages.repository :refer :all]
    [recomotion-backend.storages.mongo :refer [mongo-repository]]
    )
  )

(defrecord Event [id content-id user-id kind facial-expression score time])

(def ^:dynamic events-storage)

(defn init-events [mongo-db]
  ;Issue if use keyword (str) didn't help somewhy
  (-> (mongo-repository mongo-db "events")
    (fk-index [:content-id :user-id] false)
    (fk-index [:content-id] false)
    (fk-index [:user-id] false)
    )
  )

(defn calc-score [facial-expression]
  (-> (:mouthSmile_L facial-expression)
    (+ (:mouthSmile_R facial-expression))
    (/ 2)
    (* 100)
    int
    )
  )

(defn create-event [content-id user-id event-kind facial-expression time]
  (save events-storage
    {
      :content-id content-id
      :user-id user-id
      :kind event-kind
      :facial-expression facial-expression
      :score (calc-score facial-expression)
      :time time
      }
    )
  )

(defn lookup-events [content-id user-id]
  (find-all events-storage
    {:content-id content-id :user-id user-id}
    (array-map :_id 1)
    )
  )

(defn lookup-events-per-user [user-id]
  (find-all events-storage {:user-id user-id} (array-map :_id 1))
  )

(defn lookup-events-per-content [content-id]
  (find-all events-storage {:content-id content-id} (array-map :_id 1))
  )
