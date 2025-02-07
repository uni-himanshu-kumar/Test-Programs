package main

import (
	"bufio"
	"fmt"
	"os"
)

func main() {
	welcome:="Welcome to the user input"
	fmt.Println(welcome)

	reader := bufio.NewReader(os.Stdin)
	fmt.Println("Enter the rating for our Pizza: ")

	input, _ := reader.ReadString('\n')
	fmt.Print("Thanks for rating, ", input)
	fmt.Printf("Type of rating is %T", input)
}