(ns app.server
  (:require
    [app.handler :as h]
    [environ.core :refer [env]]
    [ring.adapter.jetty :as jetty])
  (:gen-class))

(defn -main [& _]
  (let [port (Integer. (or (env :port) 3000))]
    (jetty/run-jetty h/app
                     {:port port})))
