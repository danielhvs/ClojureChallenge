(ns app.handler
  (:require
    [app.core :as s]
    [clojure.data.json :as json]
    [compojure.core :as cpj]
    [compojure.route :as route]
    [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
    [ring.util.response :as r]))

(def only-lower-case-letters? (partial re-matches #"[a-z]+"))
(def invalid? (complement only-lower-case-letters?))

(defn scramble? [scrambled-word word]
  (let [invalid-inputs (filter invalid? [scrambled-word word])
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
                r/response
                (r/header "Access-Control-Allow-Origin" "*")))))

(cpj/defroutes app-routes
  (cpj/GET "/scramble/:scrambled-word/:word"
           [scrambled-word word]
           (scramble? scrambled-word word))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
