(ns clj.core-test
  (:require [clojure.test :refer :all]
            [clj.core :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
    (println "woof")
    (is (= 1 1))))

(deftest content-type-test
    (testing "returns text/html when file name ends with .html"
        (is (= "text/html" (content_type {:params {:name "index.html"}} "name"))))

    (testing "returns binary/image when file name does not end with .html"
        (is (= "binary/image" (content_type {:params {:name "image.png"}} "name")))))
