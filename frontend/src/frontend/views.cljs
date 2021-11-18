(ns frontend.views
  (:require
    [frontend.events :as events]
    [frontend.subs :as subs]
    [re-frame.core :as re-frame]))

(defn input-field []
  (let [value (re-frame/subscribe [::subs/scrambled-word])]
    [:input {:type "text"
             :value @value
             :on-change #(re-frame/dispatch
                           [::events/scrambled-word (-> % .-target .-value)])}]))

(defn main-panel []
  [:div
   [input-field]])

