(ns frontend.views
  (:require
    [frontend.events :as events]
    [frontend.subs :as subs]
    [re-frame.core :as re-frame]))

(defn input-field [subscription event opts]
  (let [value (re-frame/subscribe [subscription])]
    [:input (merge (or opts {})
                   {:type "text"
                    :value @value
                    :on-change #(re-frame/dispatch
                                  [event (-> % .-target .-value)])})]))

(defn button [on-click]
  [:button
   {:type "submit" :on-click on-click}
   "Analize"])

(defn parse-response [response]
  (or (get {true "Yes! The scrambled word It's a 'superset'."
            false "Nope. The scrambled word is not a 'superset'."}
           (:superset? response))
      response))

(defn main-panel []
  (let [response (re-frame/subscribe [::subs/response])
        scrambled-word (re-frame/subscribe [::subs/scrambled-word])
        word (re-frame/subscribe [::subs/word])]
    [:div
     [:img {:src "https://flexiana.com/app/themes/flexianacom/dist/assets/images/logo_flexiana_gold.svg"}]
     [:div
      [:label {:for "fname"} "The word (only lower case letters)"]
      [input-field ::subs/word ::events/word
       {:placeholder "Type the regular word"}]
      [:label {:for "fname"} "Scrambled word (only lower case letters too)"]
      [input-field ::subs/scrambled-word ::events/scrambled-word
       {:placeholder "Type the scrambled word"}]
      [button #(re-frame/dispatch [::events/scramble @scrambled-word @word])]
      [:h3.result (->> @response
                       parse-response)]]]))
