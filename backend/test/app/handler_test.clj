(ns app.handler-test
  (:require
    [app.handler :as h]
    [clojure.data.json :as json]
    [clojure.test :refer [deftest testing is]]
    [ring.mock.request :as mock]))

(deftest test-app
  (testing "Scrambled is subset"
    (let [response (h/app (mock/request :get "/scramble/hsoue/house"))]
      (is (= (:status response) 200))
      (is (= (:body response)
             (json/write-str {:scrambled true})))))

  (testing "Scrambled is not subset"
    (let [response (h/app (mock/request :get "/scramble/houe/house"))]
      (is (= (:status response) 200))
      (is (= (:body response)
             (json/write-str {:scrambled false})))))

  (testing "Invalid input is the scrambled word"
    (let [response (h/app (mock/request :get "/scramble/ho2e/house"))]
      (is (= (:status response) 400))
      (is (= (:body response)
             (json/write-str {:error-msg "Only lower case letters are accepted"
                              :invalid-inputs ["ho2e"]})))))

  (testing "Invalid input is the word"
    (let [response (h/app (mock/request :get "/scramble/house/House"))]
      (is (= (:status response) 400))
      (is (= (:body response)
             (json/write-str {:error-msg "Only lower case letters are accepted"
                              :invalid-inputs ["House"]})))))

  (testing "Both invalid inputs"
    (let [response (h/app (mock/request :get "/scramble/ho2e/House"))]
      (is (= (:status response) 400))
      (is (= (:body response)
             (json/write-str {:error-msg "Only lower case letters are accepted"
                              :invalid-inputs ["ho2e" "House"]})))))

  (testing "not-found route"
    (let [response (h/app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))

