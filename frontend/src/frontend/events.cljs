(ns frontend.events
  (:require
    [ajax.core :as ajax]
    [day8.re-frame.http-fx]
    [frontend.db :as db]
    [re-frame.core :as re-frame]))

(re-frame/reg-event-db
  ::word
  (fn [db [_ input]]
    (assoc db :word input)))

(re-frame/reg-event-db
  ::scrambled-word
  (fn [db [_ input]]
    (assoc db :scrambled-word input)))

(re-frame/reg-event-db
  ::initialize-db
  (fn [_ _]
    db/default-db))

(re-frame/reg-event-db
  ::error-http
  (fn [db [_ result]]
    (assoc db :response (:status-text result))))

(re-frame/reg-event-db
  ::sucess
  (fn [db [_ result]]
    (assoc db :response result)))

(re-frame/reg-event-fx
  ::scramble
  (fn [{:keys [db]} [_ scrambled-word word]]
    (let [api (str "http://localhost:3000/scramble/?scrambled-word=" scrambled-word "&word=" word)]
      {:db db
       :http-xhrio {:method :get
                    :uri api
                    :timeout 10000
                    :format (ajax/json-request-format)
                    :response-format (ajax/json-response-format {:keywords? true})
                    :on-success [::sucess]
                    :on-failure [::error-http]}})))
