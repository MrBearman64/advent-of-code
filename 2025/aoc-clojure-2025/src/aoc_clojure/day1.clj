(ns aoc-clojure.day1
  (:require
   [clojure.java.io :as io]))
(require '[clojure.string :as str]
         '[clojure.java.io :as io])

(defn readChallengeInput
  []
  (with-open [rdr (io/reader ".\\resources\\day1\\input.txt")]
    (->> rdr
         .lines
         .toList
         (map str/trim)
         (filter #(not (empty? %))))))

(defn solveDay1Part1
  ([pos acc [rotation & rotations]]
   (println rotation)
   (if (nil? rotation) acc
       (let [[direction & p2] rotation
             rot (Integer/parseInt (str/join p2))
             newPos (mod (case direction
                           \L (+ pos rot)
                           \R (- pos rot)) 100)
             newAcc (if (zero? newPos) (inc acc) acc)]

         (printf "rot %s, dir: %s, rot: %s, newPos: %s, newAcc: %s" rotation direction rot newPos newAcc)
         (recur newPos newAcc rotations)))))
