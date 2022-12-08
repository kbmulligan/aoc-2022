(ns mulli.aoc
  (:require [clojure.string :as str])
  (:require [clojure.set :as cset]))

(println "AOC - Day05")

(def INPUT-FILENAME "./day05/day05.txt")

(defn split-on-newline [raw]
  (str/split raw #"\n"))

(defn get-index [x])

(def input (split-on-newline (slurp INPUT-FILENAME)))


(defn print-each [items]
  (doseq [i items]
    (println i)))

(defn parse-move [m]
  (let [nums (re-seq #"\d{1,2}" m)]
    ;(Integer/parseInt (nth nums 0))))
    (cons (Integer/parseInt (nth nums 0)) (rest nums))))

(defn end-layout [final-stacks-map]
  (str/join (map first (map #(get final-stacks-map %) (sort (keys final-stacks-map))))))

;; HELPERS ABOVE

(println input)
; (println (partition-by empty? input))
(def cargo (first (partition-by empty? input)))
(def moves-words (last (partition-by empty? input)))
;(println moves-words)
(println "Number of moves: " (count moves-words))

(print-each cargo)

(def labels-spaced (last cargo))
(def boxes (reverse (pop (reverse cargo))))

; requires boxes for closure
(defn nth-column [n]
  (filter #(not= (nth " " 0) %) (map #(nth % n) boxes)))

; (println labels-spaced)
(def keep-indexes (map #(str/index-of labels-spaced %) labels-spaced))
;(println keep-indexes)

(def labels (re-seq #"\d{1,2}" labels-spaced))

;(println (filter #(> % 0) keep-indexes))
(def keep-these (filter #(> % 0) keep-indexes))

; (println "Boxes" boxes)
; (println "Mapping over boxes:" (map #(nth % 1) boxes))
; (println "Mapping over cargo:" (nth-column 1))

(def stacks (map nth-column keep-these))
(println "Stacks (initial): " stacks)

; (println (interleave labels stacks))
; this is how to create the hash-map of stacks with their labels
(defn make-stack [stack-labels stacks-state]
  (apply assoc {} (interleave stack-labels stacks-state)))

(def stacks-map (make-stack labels stacks))

(println "Stacks map (initial):   " stacks-map)

;(print-each moves-words)
; this is mainly just extracting numbers and changing type of the amount to int vs char
(def moves (map parse-move moves-words))
;(print-each moves)

; cs = current stacks
; m  = move to process
; returns stacks after processing move
(defn next-map [cs m] 
  ;(println cs)
  ;(println m)
  (let [amt (first m) 
        src (second m)
        dst (last m)] 
    (println "...moving" amt "boxes" "from" src "to" dst)
    
    ;(println "Src stack:" (get cs src))
    ;(println "Dst stack:" (get cs dst))
    ;(println "Crate(s): " (take amt (get cs src)))
    ;(println "Src stack:" (drop amt (get cs src)))
    ;(println "Dst stack:" (flatten (cons (reverse (take amt (get cs src))) (get cs dst))))
    ;(print-each cs)
    ;(make-stack labels (vals cs)) 

    ; Part 1
    ;(assoc cs src  (drop amt (get cs src)) 
    ;          dst  (flatten (cons (reverse (take amt (get cs src))) (get cs dst))))
    ; Part 2
    (assoc cs src  (drop amt (get cs src)) 
              dst  (flatten (cons (take amt (get cs src)) (get cs dst))))
    ))

;(def map2 (next-map stacks-map (first moves)))
;(def map3 (next-map map2 (second moves)))
;(def map4 (next-map map3 (second (rest moves))))
;(def map5 (next-map map4 (last moves)))
;(println "Next map:" map2)
;(println "Next map:" map3)
;(println "Next map:" map4)
;(println "Next map:" map5)

;;; NO IDEA ON THIS RECURSION
(defn process-moves [init-state moves-list]
  (if (> (count (keys init-state)) 9) 
    (do 
      (println "Moves remaining: " (count moves-list))
      (print-each init-state))
    ())
    
  (if (empty? moves-list)
    init-state
    (process-moves (next-map init-state (first moves-list)) (rest moves-list))))

(def end-map (process-moves stacks-map moves))

(println "End state:")
(print-each end-map)

(println "Final stack top layer : ") 
; (println (end-layout map5))
(println (end-layout end-map)
