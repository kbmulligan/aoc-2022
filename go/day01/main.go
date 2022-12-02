package main

import (
	"fmt"
	"log"
	"os"
	"strconv"
	"strings"
  "sort"
)

func readFile (filename string) (string) {
  content, err := os.ReadFile(filename)
  if err != nil {
    log.Fatal("error with file read!")
  }
  return string(content)
}

func makeIntList (strings []string) ([]int64) {
  items := []int64{}
  for _, x := range strings {
    if x == "" {
      continue
    }
    intX, err := strconv.ParseInt(x, 10, 0)
    if err != nil {
      log.Fatal("Convert to Int error!", err) 
    }
    items = append(items, intX) 
  }
  return items
}

func totalCalories (singleList []int64) (int) {
  total := 0
  for _, x := range singleList {
    total += int(x)
  }
  return total
}

func topX (s []int, n int) (int) {  
  total := 0
  for i := 0; i < n; i++ {
    total = total + s[i]
  }
  return total 
}

func main() {
  FILENAME := "input01.txt"
  stringData := readFile(FILENAME)
  allCalories := strings.SplitN(stringData, "\n\n", 1000)

  elfLists := [][]int64{}
  for _, x := range allCalories {
    calorieSet := strings.SplitN(x, "\n", 1000)
    elfLists = append(elfLists, makeIntList(calorieSet))
  }

  calorieSums := []int{}
  for _, x := range elfLists {
    calorieSums = append(calorieSums, totalCalories(x)) 
  }

  sort.Slice(calorieSums, func (i, j int) bool {
      return calorieSums[i] > calorieSums[j]
  })
  
  fmt.Println(topX(calorieSums, 1))
  fmt.Println(topX(calorieSums, 3))
}
