(ns app.handler-test
  (:require
    [app.handler :as h]
    [clojure.data.json :as json]
    [clojure.test :refer [deftest testing is]]
    [ring.mock.request :as mock]))

(deftest test-app
  (testing "Scrambled true"
    (let [response (h/app (mock/request :get "/scramble/hsoue/house"))]
      (is (= (:status response) 200))
      (is (= (:body response)
             (json/write-str {:scrambled true})))))

  (testing "Scrambled false"
    (let [response (h/app (mock/request :get "/scramble/houe/house"))]
      (is (= (:status response) 200))
      (is (= (:body response)
             (json/write-str {:scrambled false})))))

  (testing "not-found route"
    (let [response (h/app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))
