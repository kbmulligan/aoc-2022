package main

import (
    "fmt"
    "log"
    "os"
    "strings"
)

func readFile(filename string) string {
    content, err := os.ReadFile(filename)
    if err != nil {
        log.Fatal("error with file read!")
    }
    return string(content)
}

func sum(singleList []int64) int {
    total := 0
    for _, x := range singleList {
        total += int(x)
    }
    return total
}

func dataFromFile(f string) []string {
    INPUT_LIMIT := 10000
    stringData := readFile(f)
    rounds := strings.SplitN(stringData, "\n", INPUT_LIMIT)
    return rounds
}

func main() {
  const FILENAME = "dayday02.txt"
  scoreLookup1 := map[string]int{
    "A X": 4,
    "A Y": 8,
    "A Z": 3,
    "B X": 1,
    "B Y": 5,
    "B Z": 9,
    "C X": 7,
    "C Y": 2,
    "C Z": 6,
  }
  scoreLookup2 := map[string]int{
    "A X": 3,
    "A Y": 4,
    "A Z": 8,
    "B X": 1,
    "B Y": 5,
    "B Z": 9,
    "C X": 2,
    "C Y": 6,
    "C Z": 7,
  }
    rounds := dataFromFile(FILENAME)

  scoreTotal1 := 0
  scoreTotal2 := 0
  for _, r := range rounds {
    scoreTotal1 += scoreLookup1[r]
    scoreTotal2 += scoreLookup2[r]
  }
    fmt.Println("Score 1:", scoreTotal1)
    fmt.Println("Score 2:", scoreTotal2)
}
