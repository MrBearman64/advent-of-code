(ns aoc-clojure.day1-test
  (:require [clojure.test :refer :all]
            [aoc-clojure.day1 :refer :all]))

(def rotations ["L68" "L30" "R48" "L5" "R60" "L55" "L1" "L99" "R14" "L82"])

(deftest example-test
  (testing "Day 1 - Part 1"
    (is (= (solveDay1Part1 50 0 rotations) 3))))

(deftest day1-file-test
  (testing "Day 1 - Part 1 - File Input"
    (let [input (seq (readChallengeInput))]
      (is (#(not (zero? %))) (solveDay1Part1 50 0 input)))))

