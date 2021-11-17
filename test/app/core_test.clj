(ns app.core-test
  (:require
    [app.core :as app]
    [clojure.test :refer [deftest testing is]]))

(def valid-input "abc")

(defn test-invalid-input [scrambled-word word]
  (is (thrown? java.lang.AssertionError
               (app/scramble? scrambled-word word))))

(deftest scrambled
  (testing "Invalid input"
    (test-invalid-input valid-input "")
    (test-invalid-input valid-input "123")
    (test-invalid-input valid-input "a.c")
    (test-invalid-input "" valid-input)
    (test-invalid-input "123" valid-input)
    (test-invalid-input "a.c" valid-input))
  (testing "Scrambled words"
    (is (true? (app/scramble? "rekqodlw" "world")))
    (is (true? (app/scramble? "cedewaraaossoqqyt" "codewars"))))
  (testing "Not scrambled words"
    (is (false? (app/scramble? "katas" "steak")))))
