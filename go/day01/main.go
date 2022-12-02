package main

import (
    "fmt"
    "log"
    "os"
    "sort"
    "strconv"
    "strings"
)

func readFile(filename string) string {
    content, err := os.ReadFile(filename)
    if err != nil {
        log.Fatal("error with file read!")
    }
    return string(content)
}

func makeIntList(strings []string) []int64 {
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

func sum(singleList []int64) int {
    total := 0
    for _, x := range singleList {
        total += int(x)
    }
    return total
}

func topN(s []int, n int) int {
    total := 0
    for i := 0; i < n; i++ {
        total += s[i]
    }
    return total
}

func caloriesPerElf(rawData []string) [][]int64 {
    caloriesData := [][]int64{}
    for _, x := range rawData {
        caloriesData = append(caloriesData, makeIntList(strings.SplitN(x, "\n", 1000)))
    }
    return caloriesData
}

func dataFromFile(f string) [][]int64 {
    stringData := readFile(f)
    calorieStringGroups := strings.SplitN(stringData, "\n\n", 1000)
    elfCalorieLists := caloriesPerElf(calorieStringGroups)
    return elfCalorieLists
}

func sublistSums(inputs [][]int64) []int {
    sums := []int{}
    for _, x := range inputs {
        sums = append(sums, sum(x))
    }
    return sums
}

func sortSlice(s []int) []int {
    sort.Slice(s, func(i, j int) bool {
        return s[i] > s[j]
    })
    return s
}

func main() {
    const FILENAME = "input01.txt"
    elfCalorieLists := dataFromFile(FILENAME)
    calorieSums := sublistSums(elfCalorieLists)
    calorieSums = sortSlice(calorieSums)

    fmt.Println("Top 1:", topN(calorieSums, 1))
    fmt.Println("Top 3:", topN(calorieSums, 3))
}
