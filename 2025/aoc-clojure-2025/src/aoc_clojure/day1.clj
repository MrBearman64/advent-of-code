(ns aoc-clojure.day1
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(defn read-challenge-input
  []
  (->> (io/resource "day1/input.txt")
       io/reader
       line-seq
       (map str/trim)
       (remove str/blank?)))

(defn solve-day1-part1
  ([pos acc [rotation & rotations]]
   ;;(println rotation)
   (if (str/blank? rotation)
     (do
       (printf "rot %s, newPos: %s, newAcc: %s%n" rotation pos acc)
       acc)
     (let [[direction & p2] rotation
           rot (Integer/parseInt (str/join p2))
           newPos (mod (case direction
                         \L (+ pos rot)
                         \R (- pos rot)) 100)
           newAcc (if (zero? newPos) (inc acc) acc)]
       (recur newPos newAcc rotations)))))

;; Style critique by daddy GPT

(defn- step [{:keys [pos acc]} rotation]
  (let [dir (first rotation)
        amount (parse-long (apply str (subs rotation 1)))
        pos' (mod (({\L + \R -} dir) pos amount) 100)
        acc' (if (zero? pos') (inc acc) acc)]
    {:pos pos' :acc acc'}))

(defn solve-day1-part1-functional [pos rotations]
  (:acc (reduce step {:pos pos :acc 0} rotations)))
