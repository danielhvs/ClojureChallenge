(ns app.handler
  (:require
    [app.core :as s]
    [clojure.data.json :as json]
    [compojure.core :as cpj]
    [compojure.route :as route]
    [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
    [ring.util.response :as r]))

(defn scramble? [scrambled-word word]
  (r/response
    (json/write-str {:scrambled
                       (s/scramble? scrambled-word word)})))

(cpj/defroutes app-routes
  (cpj/GET "/scramble/:scrambled-word/:word"
           [scrambled-word word]
           (scramble? scrambled-word word))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
