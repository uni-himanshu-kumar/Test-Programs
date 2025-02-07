package main

import "fmt"

func main() {
	fmt.Println("Structs in golang")
	himanshu := User{"Himanshu", "himanshu@go.dev", true, 12}
	fmt.Println(himanshu)
	fmt.Printf("himanshu details are: %+v\n", himanshu)
	fmt.Printf("name is %v and email is %v\n", himanshu.Name, himanshu.Email)
}

type User struct {
	Name string // Capitalized field names are exported
	Email string
	Status bool
	Age int
}