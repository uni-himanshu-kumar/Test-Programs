package main

import (
	"fmt"
	"time"
)

func main() {
	fmt.Println("Hello, World! =>")

	presentTime := time.Now()
	fmt.Println(presentTime)

	fmt.Println(presentTime.Format("02-01-2006 Monday"))

	createdDate := time.Date(2020, time.August, 11, 23, 23, 0, 0, time.UTC)

	fmt.Println(createdDate)

	fmt.Println(createdDate.Format("02-01-2006 Monday"))
}