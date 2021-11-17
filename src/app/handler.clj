(ns app.handler
  (:require
    [compojure.core :as cpj]
    [compojure.route :as route]
    [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(cpj/defroutes app-routes
  (cpj/GET "/" [] "Hello World")
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
