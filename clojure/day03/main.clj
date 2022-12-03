(ns mulli.aoc
  (:require [clojure.set :as cljset]))

(println "AOC - Day03")

(def INPUT-FILENAME "./day03/day03.txt")
(def input (slurp INPUT-FILENAME))

(defn split-on-newline [raw]
  (clojure.string/split raw #"\n"))

(defn compartments [backpack]
  (partition (/ (count backpack) 2) backpack))

(defn shared-items [both]
  (apply str (apply clojure.set/intersection (map set both))))

(defn find-priority [x]
  ; check for lowercase vs uppercase and do the right conversion math
  (if (> x 96)
    (- x 96) 
    (- x 38)))

(def packs (split-on-newline input))

(->> packs
  (map compartments)
  (map shared-items)
  (map first)
  (map int)
  (map find-priority)
  (reduce +)
  (println "Part 1:"))

(->> packs
  (partition 3)
  (map shared-items)
  (map first)
  (map int)
  (map find-priority)
  (reduce +)
  (println "Part 2:"))
