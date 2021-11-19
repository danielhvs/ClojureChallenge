(ns app.handler
  (:require
    [app.core :as s]
    [clojure.data.json :as json]
    [compojure.core :as cpj]
    [compojure.route :as route]
    [ring.middleware.cors :refer [wrap-cors]]
    [ring.middleware.defaults :refer [api-defaults wrap-defaults]]
    [ring.middleware.keyword-params :refer [wrap-keyword-params]]
    [ring.util.response :as r]))

(def only-lower-case-letters? (partial re-matches #"[a-z]+"))
(def invalid? (complement only-lower-case-letters?))

(defn scramble [{:keys [params]}]
  (let [{word :word
         scrambled-word :scrambled-word} params
        invalid-inputs (filter invalid? [scrambled-word word])
        bad-request 400]
    (cond (seq invalid-inputs)
            (-> {:error-msg "Only lower case letters are accepted"
                 :invalid-inputs invalid-inputs}
                json/write-str
                r/response
                (r/status bad-request))
          :else
            (-> {:scrambled (s/scramble? scrambled-word word)}
                json/write-str
                r/response))))

(cpj/defroutes app-routes
  (cpj/GET "/scramble/" request (scramble request))
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      (wrap-defaults api-defaults)
      (wrap-cors
        :access-control-allow-origin [#"http://localhost:.*"]
        :access-control-allow-methods [:get])
      wrap-keyword-params))
