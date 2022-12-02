(ns nitor.aoc)

(def INPUT-FILENAME "./day02/input02.txt")
(def input (slurp INPUT-FILENAME))

(def score-lookup1 (hash-map "A X" 4  "A Y" 8 "A Z" 3 "B X" 1 "B Y" 5 "B Z" 9 "C X" 7 "C Y" 2 "C Z" 6))
(def score-lookup2 (hash-map "A X" 3  "A Y" 4 "A Z" 8 "B X" 1 "B Y" 5 "B Z" 9 "C X" 2 "C Y" 6 "C Z" 7))

(defn split-on-newline [raw]
  (clojure.string/split raw #"\n"))

(defn score-mark1 [mark]
  (get score-lookup1 mark))

(defn score-mark2 [mark]
  (get score-lookup2 mark))

(def rounds (split-on-newline input))

(println (reduce + (map #(score-mark1 %) rounds)))
(println (reduce + (map #(score-mark2 %) rounds)))
