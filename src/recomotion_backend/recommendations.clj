(ns recomotion-backend.recommendations
  (:require
    [recomotion-backend.storages.content :as content]
    [recomotion-backend.storages.events :as events]
    [recomotion-backend.storages.impressions :as impressions]
    )
  )

(defn on-event [event]
  (content/new-content (:content-id event))
  (impressions/update-impression
    (:content-id event)
    (:user-id event)
    (int
      (reduce
        #(-> %1 (+ %2) (/ 2) double) ;avg
        (map
          #(:score %)
          (events/lookup-events (:content-id event) (:user-id event))
          )
        )
      )
    )
  )

(defn recommendations [user-id]
  (let [
    user-impressions (impressions/lookup-impressions-per-user user-id)
    user-content (into #{} (map #(:content-id %) user-impressions))
    ]
    (->> user-impressions
      (take 3)
      ;loading users who also liked this
      (map #(impressions/lookup-impressions-per-content (:content-id %)))
      flatten
      (filter #(not= (:user-id %) user-id))
      (map #(impressions/lookup-impressions-per-user (:user-id %)))
      flatten
      ;excluding what user already watched
      (filter #(not (contains? user-content %)))
      (take 10)
      )
    )
  )
