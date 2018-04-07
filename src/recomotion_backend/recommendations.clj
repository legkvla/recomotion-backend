(ns recomotion-backend.recommendations
  (:require
    [recomotion-backend.storages.content :as content]
    [recomotion-backend.storages.events :as events]
    [recomotion-backend.storages.impressions :as impressions]
    )
  )

(defn calc-score [event] 100)

(defn on-event [event]
  (content/new-content (:content-id event))
  (impressions/update-impression
    (:content-id event)
    (:user-id event)
    (reduce
      #(-> %1 (+ %2) (/ 2)) ;avg
      (map
        #(calc-score %)
        (events/lookup-events (:content-id event) (:user-id event))
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
      (map #(impressions/lookup-impressions-per-content (:content-id %)))
      flatten
      (filter #(not= (:user-id %) user-id))
      (map #(impressions/lookup-impressions-per-user (:user-id %)))
      flatten
      (filter #(not (contains? user-content %)))
      (take 10)
      )
    )
  )
