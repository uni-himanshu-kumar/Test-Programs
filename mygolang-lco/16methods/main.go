package main

import "fmt"

func main() {
	fmt.Println("Structs in golang")
	himanshu := User{"Himanshu", "himanshu@go.dev", true, 12}
	fmt.Println(himanshu)
	fmt.Printf("himanshu details are: %+v\n", himanshu)
	fmt.Printf("name is %v and email is %v\n", himanshu.Name, himanshu.Email)
	himanshu.GetStatus()
	himanshu.NewMail()
	fmt.Printf("name is %v and email is %v\n", himanshu.Name, himanshu.Email)
}

type User struct {
	Name string
	Email string
	Status bool
	Age int
}

func (u User) GetStatus() {
	fmt.Println("Is user active: ", u.Status)
}

func (u User) NewMail() {
	u.Email = "test@go.dev"
	fmt.Println("Email of this user is: ", u.Email)
}