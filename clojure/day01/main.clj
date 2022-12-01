(ns nitor.aoc)

(def TOP-N 3)
(def INPUT-FILENAME "input1.txt")
(def input (slurp INPUT-FILENAME))

(defn split-on-newline [x]
  (clojure.string/split x #"\n"))

(defn split-on-double-newline [x]
  (clojure.string/split x #"\n\n"))

(defn convert-all-int [x]
  (map #(Integer/parseInt %) x))

(defn parse-groups [g]
  (->> g 
    (map #(split-on-newline %))
    (map #(convert-all-int %))))

(defn calorie-total [c]
  (->> c
    (map #(reduce + %))
    (sort)
    (take-last TOP-N)
    (reduce +)))

(def calorie-groups (parse-groups (split-on-double-newline input)))
(println (calorie-total calorie-groups))

; THIS IS FROM PART 1 
; (println (apply max (map #(reduce + %) calorie-groups)))

; FIRST CALCULATION METHOD WITHOUT PIPING
; (println (reduce + (take-last TOP-N (sort (map #(reduce + %) calorie-groups)))))

; FIRST INPUT PARSING METHOD WITHOUT PIPING
; (def groups (map #(split-on-newline %) (clojure.string/split input #"\n\n")))
; (def groups (map #(split-on-newline %) (split-on-double-newline input)))
; (def calorie-groups (map #(convert-all-int %) groups))
