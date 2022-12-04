(ns mulli.aoc
  (:require [clojure.string :as str])
  (:require [clojure.set :as cset]))

(println "AOC - Day04")

(def INPUT-FILENAME "./day04/day04.txt")

(defn split-on-newline [raw]
  (str/split raw #"\n"))

(def input (split-on-newline (slurp INPUT-FILENAME)))

; needs tuning for higher numbers
(defn extract-numbers [in]
  (map read-string (re-seq #"\d{1,3}" in)))

(defn both-ranges [r]
  (partition 2 r))

; directional version where order matters
(defn fully-contains? [range-pair]
  (let [sub (first range-pair) container (second range-pair)]
    (and 
      (>= (first sub) (first container)) 
      (<= (second sub) (second container)))))

; check both orders
(defn either-fully-overlapped? [both]
  (or 
    (fully-contains? both) 
    (fully-contains? (reverse both))))

; TODO -- THIS IS A BIT HACKY AT THE MOMENT
(defn any-overlap? [both]
  (let [range1 (first both) range2 (second both)]
    (< 
      0
      (count
        (cset/intersection
          (set (range (first range1) (inc (second range1))))
          (set (range (first range2) (inc (second range2)))))))))

; i feel like there's got to be a better version of this, doesn't feel very 'clojurific'
(defn bool-to-int [b]
  (if b
    1
    0))

(defn part1 [in]
  (->> in
    (map extract-numbers)
    (map #(partition 2 %))
    (map either-fully-overlapped?)
    (map bool-to-int)
    (reduce +)))

(defn part2 [in]
  (->> in
    (map extract-numbers)
    (map #(partition 2 %))
    (map any-overlap?)
    (map bool-to-int)
    (reduce +)))

;; helpers above
;(println "Input:" input)

(println "Part 1:" (part1 input))
(println "Part 2:" (part2 input))
