(ns recomotion-backend.recommendations
  (:require
    [recomotion-backend.storages.content :as content]
    [recomotion-backend.storages.events :as events]
    [recomotion-backend.storages.impressions :as impressions]
    )
  )

(defn on-event [event]
  (content/new-content (:content-id body))
  )

(defn recommendations [user-id]

  )
