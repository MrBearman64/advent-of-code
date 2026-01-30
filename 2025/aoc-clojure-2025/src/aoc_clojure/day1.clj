(ns aoc-clojure.day1
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]
   [clojure.core.match :refer [match]]))

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

(defn- hits-exactly-zero [{:keys [pos acc]} rotation]
  (let [dir (first rotation)
        amount (parse-long (apply str (subs rotation 1)))
        pos' (mod (({\L + \R -} dir) pos amount) 100)
        acc' (if (zero? pos') (inc acc) acc)]
    {:pos pos' :acc acc'}))

(defn solve-day1-part1-functional [pos rotations]
  (:acc (reduce hits-exactly-zero {:pos pos :acc 0} rotations)))

(defn- count-hits
  "Count how many times 0 is hit in one rotation"
  [pos pos' amount rotations]
  ;; (if (and (or (not= pos' real-pos) (zero? pos')) ((complement zero?) pos)) (inc acc) acc)]
  (match [pos pos' amount rotations]
    [0 _ _ 0] 0
    [_ 0 _ r] (inc r)
    :else (if (not= ()))))

(defn- hits-zero [{:keys [pos acc]} rotation-str]
  (let [dir (first rotation-str)
        amount (parse-long (apply str (subs rotation-str 1)))
        op ({\L - \R +} dir)
        pos' (mod (op pos amount) 100)
        rotations (abs (/ (op pos amount) 100))
        acc' (+ (count-hits pos pos' amount rotations) acc)]
    {:pos pos' :acc acc'}))

(defn solve-day1-part2-functional [pos rotations]
  (:acc (reduce hits-zero {:pos pos :acc 0} rotations)))

