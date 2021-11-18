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

(defn button [on-click]
  [:button
   {:type "submit" :on-click on-click}
   "Go!"])

(defn main-panel []
  (let [response (re-frame/subscribe [::subs/response])
        scrambled-word (re-frame/subscribe [::subs/scrambled-word])
        word (re-frame/subscribe [::subs/word])]
    [:div
     [:h1 (str @response)]
     [input-field ::subs/scrambled-word ::events/scrambled-word]
     [input-field ::subs/word ::events/word]
     [button #(re-frame/dispatch [::events/scramble @scrambled-word @word])]]))

