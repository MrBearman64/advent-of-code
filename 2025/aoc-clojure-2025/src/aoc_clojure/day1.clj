(ns aoc-clojure.day1)

(defn solveDay1Part1
  ([_ acc]
  acc)
  ([pos acc [[dir & rot] & rotations]]
   (println dir rot)
   (println (class dir))
  (let [
        newPos (case dir
                \L (+ pos rot)
                \R (- pos rot))
        newAcc (if (zero? newPos) (inc acc) acc)
        ]
    (recur newPos newAcc rotations))))
