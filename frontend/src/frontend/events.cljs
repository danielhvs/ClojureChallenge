(ns frontend.events
  (:require
    [frontend.db :as db]
    [re-frame.core :as re-frame]))

(re-frame/reg-event-db
  ::scrambled-word
  (fn [db [_ input]]
    (assoc db :scrambled-word input)))

(re-frame/reg-event-db
  ::initialize-db
  (fn [_ _]
    db/default-db))
