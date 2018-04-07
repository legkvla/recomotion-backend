(ns recomotion-backend.storages.content
  (:require
    [recomotion-backend.storages.repository :refer :all]
    [recomotion-backend.storages.mongo :refer [mongo-repository]]
    )
  )

(defrecord Content [id content-id like-count dislike-count])

(def ^:dynamic content-storage)

(defn init-content [mongo-db]
  ;Issue if use keyword (str) didn't help somewhy
  (-> (mongo-repository mongo-db "content")
    (fk-index [:content-id] true)
    )
  )

(defn lookup-content [content-id]
  (first
    (find-all content-storage {:content-id content-id} nil)
    )
  )

(defn new-content [content-id]
  (if-not (lookup-content content-id)
    ;todo add try catch here
    (save content-storage
      {:content-id content-id :like-count 0 :dislike-count 0}
      )
    )
  )

(defn like [content-id]
  (new-content content-id)
  (apply-change content-storage content-id :inc :like-count 1)
  )

(defn dislike [content-id]
  (new-content content-id)
  (apply-change content-storage content-id :inc :dislike-count 1)
  )
