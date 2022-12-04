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

func dataFromFile(f string) []string {
  INPUT_LIMIT := 10000
    stringData := readFile(f)
    return strings.SplitN(stringData, "\n", INPUT_LIMIT)
}

func sum(singleList []int) int {
    total := 0
    for _, x := range singleList {
        total += int(x)
    }
    return total
}

func compartments (pack string) []string {
  half := len(pack) / 2
  return []string{pack[:half], pack[half:]}
}

func uniqueCharsMap (s string) map[rune]bool {
  uniquesMap := map[rune]bool{}
  for _, r := range s {
    _, present := uniquesMap[r]
    if (!present) {
      uniquesMap[r] = true
    }
  }
  return uniquesMap
}

func uniqueChars (s string) []rune {
  m := uniqueCharsMap(s)
  uniques := make([]rune, 0, len(m))
  for k := range m {
    uniques = append(uniques, k) 
  }
  return uniques
}

func commonRunes (x []rune, y []rune) []rune {
  c := []rune{}
  for _, u := range x {
    for _, v := range y {
      if (u == v) {
        c = append(c, u)
      }
    }
  }
  return c
}

func commonRunes3 (x []rune, y []rune, z[]rune) []rune {
  c := []rune{}
  for _, u := range x {
    for _, v := range y {
      for _, w := range z {
        if (u == v && v == w) {
          c = append(c, u)
        }
      }
    }
  }
  return c
}

func printRunes (r []rune) {
  for _, x := range r {
    fmt.Printf("%c", x)
  }
  fmt.Printf("\n")
}

func priority(a rune) int {
  r := int(a)
  p := 0
  if (r > 90) {
    p = r - 96 
  } else {
    p = r - 38
  }
  return p
}

func part1 (data []string) int {
  priorities := []int{}
  for _, pack := range data {
    if (len(pack) > 0) {                               // guard for blanks
      u1 := uniqueChars(compartments(pack)[0])
      u2 := uniqueChars(compartments(pack)[1])
      commonRune := commonRunes(u1, u2)[0] 
      priority := priority(commonRune)
      priorities = append(priorities, priority)
    }
  }
  return sum(priorities)
}

func part2 (data []string) int {

  // group packs by 3's
  elfGroups := [][]string{}
  for i := 0;  i < len(data); i += 3 {
    nextGroup := []string{data[i+0], data[i+1], data[i+2]}
    elfGroups = append(elfGroups, nextGroup)
    fmt.Println(i)
  }
  
  priorities := []int{}
  for _, group := range elfGroups {
    u1 := uniqueChars(group[0])
    u2 := uniqueChars(group[1])
    u3 := uniqueChars(group[2])
    commonRune := commonRunes3(u1, u2, u3)[0] 
    priority := priority(commonRune)
    priorities = append(priorities, priority)
  }
  
  return sum(priorities)
}

func main() {
    const FILENAME = "day03/day03.txt"
  fmt.Println("AOC Day03 - Go")
    data := dataFromFile(FILENAME)

  fmt.Println("Part 1:", part1(data))
  fmt.Println("Part 2:", part2(data))
}
