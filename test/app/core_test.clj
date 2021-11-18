(ns app.core-test
  (:require
    [app.core :as app]
    [clojure.test :refer [deftest testing is]]))

(deftest scrambled
  (testing "Scrambled words"
    (is (true? (app/scramble? "rekqodlw" "world")))
    (is (true? (app/scramble? "cedewaraaossoqqyt" "codewars"))))
  (testing "Not scrambled words"
    (is (false? (app/scramble? "katas" "steak")))))
