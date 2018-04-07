(ns recomotion-backend.storages.mongo
  (:require
    [environ.core :refer [env]]
    [monger.core :as mg]
    [monger.collection :as mc]
    [monger.query :as mq]
    [monger.operators :refer :all]
    [clojure.set :refer [rename-keys]]

    [recomotion-backend.storages.repository :refer :all]
    )
  (:import org.bson.types.ObjectId)
  ; (:use clojure.tools.trace)
  )

(def db-url (if-let [url (env :mongodb-uri)] url "mongodb://127.0.0.1/reco"))
(defn init-mongo-db []
  ((mg/connect-via-uri db-url) :db)
  )

;todo question about pool in monger

(defn mongify [obj]
  (if-let [id (:id obj)]
    (-> obj (assoc :_id (ObjectId. id)) (dissoc :id) )
    obj
    )
  )

(defn unmongify [result]
  (if result
    (-> result
      (assoc :id (str (:_id result)))
      (dissoc :_id)
      )
    )
  )

(deftype MongoRepository [db collection-name]
  Repository

  (index [this fields unique]
    ; unique can be a boolean or a keyword
    (let [unique? (case unique :unique true :non-unique false unique)]
      (mc/ensure-index db collection-name fields {:unique unique?})
      )
    )

  (find-one [this id]
    (unmongify (mc/find-map-by-id db collection-name (ObjectId. id)))
    )

  (find-all [this where-fields sort-fields]
    (map unmongify
      (mq/with-collection db collection-name
        (mq/find (mongify where-fields))
        (mq/sort sort-fields)
        )
      )
    )

  (save [this rec]
    (->> rec
      (mongify)
      (mc/save-and-return db collection-name)
      (unmongify)
      )
    )

  (upsert [this where-fields rec]
    (mc/upsert db collection-name where-fields (dissoc rec :_id))
    (unmongify
      (mc/find-one-as-map db collection-name rec)
      )
    )

  (find-and-modify [this id field expected-value new-value]
    (mc/find-and-modify db collection-name
      {:_id id field expected-value}
      {:$set {field new-value}}
      :return-new true
      )
    )

  (apply-change [this id command field value]
    (let [
      operator (cond
        (= command :inc) $inc
        (= command :set) $set
        )
      ]
      ; (trace "apply-change-response"
        (assert operator)
        (mc/update-by-id db collection-name (ObjectId. id)
          {operator {field value}}
          )
        ; )
      )
    )

  (delete-one [this id] (mc/remove-by-id db collection-name (ObjectId. id)))
  (delete-all [this fields] (mc/remove db collection-name fields))
  )

(defn mongo-repository [db collection-name] (MongoRepository. db collection-name))
