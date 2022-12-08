(ns mulli.aoc
  (:require [clojure.string :as str])
  (:require [clojure.set :as cset]))

(println "AOC - Day06")

(def INPUT-FILENAME "./day06/day06.txt")

(defn split-on-newline [raw]
  (str/split raw #"\n"))

(def input (split-on-newline (slurp INPUT-FILENAME)))

;(def part1-UNIQUES 4)
;(def part2-UNIQUES 14)
(def UNIQUES 14)

(defn all-diff? [s]
  (= UNIQUES (count (set s))))

(defn first-index-all-unique [test-input] 
  (let [attempts (map #(subs test-input (- % UNIQUES) %) (range UNIQUES (count test-input)))]
    (+ UNIQUES (count (first (partition-by true? (map all-diff? attempts)))))))

(println "First all-unique substring index: " (first-index-all-unique (first input)))
