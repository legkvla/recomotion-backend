(ns recomotion-backend.storages.repository)

(defprotocol Repository
  (index [this fields unique])

  (find-one [this id])
  (find-all [this where-fields sort-fields]
    "Please use array-map for sort-fields to save order")

  (save [this rec])
  (upsert [this where-fields rec])

  (find-and-modify [this id field expected-value new-value])
  (apply-change [this id command field value])

  (delete-one [this id])
  (delete-all [this fields])
  )

(defn fk-index [repository fields unique]
  (index repository fields unique)
  repository
  )

(defn find-unique [repo where-fields]
  "Make sure there is only one document with where-fields and return it"
  (let [matches (find-all repo where-fields nil)]
    (-> matches second nil? assert)
    (first matches)
    )
  )
