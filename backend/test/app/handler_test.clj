(ns app.handler-test
  (:require
    [app.handler :as h]
    [clojure.data.json :as json]
    [clojure.test :refer [deftest testing is]]
    [ring.mock.request :as mock]))

(deftest test-app
  (testing "Scrambled is subset"
    (let [response (h/app (mock/request :get "/scramble/?scrambled-word=hsoue&word=house"))]
      (is (= (:status response) 200))
      (is (= (:body response)
             (json/write-str {:superset? true})))))

  (testing "Scrambled is not subset"
    (let [response (h/app (mock/request :get "/scramble/?scrambled-word=houe&word=house"))]
      (is (= (:status response) 200))
      (is (= (:body response)
             (json/write-str {:superset? false})))))

  (testing "Invalid input is the scrambled word"
    (let [response (h/app (mock/request :get "/scramble/?scrambled-word=ho2e&word=house"))]
      (is (= (:status response) 400))
      (is (= (:body response)
             (json/write-str {:error-msg "Only lower case letters are accepted"
                              :invalid-inputs ["ho2e"]})))))

  (testing "Invalid input is the word"
    (let [response (h/app (mock/request :get "/scramble/?scrambled-word=house&word=House"))]
      (is (= (:status response) 400))
      (is (= (:body response)
             (json/write-str {:error-msg "Only lower case letters are accepted"
                              :invalid-inputs ["House"]})))))

  (testing "Both invalid inputs"
    (let [response (h/app (mock/request :get "/scramble/?scrambled-word=ho2e&word=House"))]
      (is (= (:status response) 400))
      (is (= (:body response)
             (json/write-str {:error-msg "Only lower case letters are accepted"
                              :invalid-inputs ["ho2e" "House"]})))))

  (testing "not-found route"
    (let [response (h/app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))

