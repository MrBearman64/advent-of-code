(ns aoc-clojure.day1-test
  (:require
   [aoc-clojure.day1 :refer [read-challenge-input solve-day1-part1
                             solve-day1-part1-functional]]
   [clojure.test :refer [deftest is testing]]))

(def rotations ["L68" "L30" "R48" "L5" "R60" "L55" "L1" "L99" "R14" "L82"])

(deftest example-test
  (testing "Day 1 - Part 1"
    (is (= (solve-day1-part1 50 0 rotations) 3))))

(deftest example-test-functional
  (testing "Day 1 - Part 1"
    (is (= (solve-day1-part1-functional 50 rotations) 3))))

(deftest day1-file-test
  (testing "Day 1 - Part 1 - File Input"
    (let [input (seq (read-challenge-input))]
      (is ((complement zero?) (solve-day1-part1 50 0 input))))))

