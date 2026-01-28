(ns aoc-clojure.day1-test
  (:require [clojure.test :refer :all]
            [aoc-clojure.day1 :refer :all]))

(deftest a-test
  (testing "Day 1 - Part 1"
    (let [rotations [
                     "L68"
                     "L30"
                     "R48"
                     "L5"
                     "R60"
                     "L55"
                     "L1"
                     "L99"
                     "R14"
                     "L82"
                     ]]
    (is (= (solveDay1Part1 0 50 rotations) 3)))))
