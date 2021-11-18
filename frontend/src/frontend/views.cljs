(ns frontend.views
  (:require
    [frontend.events :as events]
    [frontend.subs :as subs]
    [re-frame.core :as re-frame]))

(defn input-field [subscription event]
  (let [value (re-frame/subscribe [subscription])]
    [:input {:type "text"
             :value @value
             :on-change #(re-frame/dispatch
                           [event (-> % .-target .-value)])}]))

(defn main-panel []
  [:div
   [input-field ::subs/scrambled-word ::events/scrambled-word]
   [input-field ::subs/word ::events/word]])

