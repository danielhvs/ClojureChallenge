(ns frontend.subs
  (:require
    [re-frame.core :as re-frame]))

(re-frame/reg-sub
  ::name
  (fn [db]
    (:name db)))

(re-frame/reg-sub
  ::word
  (fn [db]
    (:word db)))

(re-frame/reg-sub
  ::scrambled-word
  (fn [db]
    (:scrambled-word db)))

(re-frame/reg-sub
  ::response
  (fn [db]
    (:response db)))
