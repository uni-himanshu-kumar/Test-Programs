package main

import "fmt"

func main() {
	fmt.Println("Welcome to arrays in Golang!")

	var fruitList [4]string

	fruitList[0] = "Apple"
	fruitList[1] = "Banana"
	fruitList[3] = "Peach"

	fmt.Println("Fruit list is: ", fruitList)
	fmt.Println("Fruit list is: ", len(fruitList))

	var vegList = [5]string{"Potato", "Beans", "Tomato"}
	fmt.Println("Veg list is: ", vegList)
	fmt.Println("Veg list is: ", len(vegList))
}