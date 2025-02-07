package main

import (
	"fmt"
	"io"
	"os"
)

func main() {
	fmt.Println("Welcome to files in Golang")
	content := "Hello, I am learning Golang and this is a file created from Golang"
	file, err := os.Create("myFile.txt")

	// if err != nil {
	// 	panic(err)
	// } 
	checkNilError(err)


	length, err := io.WriteString(file, content)
	checkNilError(err)

	fmt.Println("Length of the file is: ", length)
	defer file.Close()
	ReadFile("myFile.txt")
}

func ReadFile(filename string) {
	databyte, err := os.ReadFile(filename)
	checkNilError(err)

	fmt.Println("Text data inside the file is: ", databyte)
	fmt.Println("Text data inside the file is: ", string(databyte))
}

func checkNilError(err error) {
	if err != nil {
		panic(err)
	}
}
