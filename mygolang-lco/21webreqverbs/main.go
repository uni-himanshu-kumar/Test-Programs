package main

import (
	"fmt"
	"io"
	"net/http"
	"net/url"
	"strings"
)

func main() {
	fmt.Println("Welcome to web verb video - LCO")
	// PerformGetRequest()
	// PerformPostJsonRequest()
	PerformPostFormRequest()
}

func PerformGetRequest() {
	const url = "http://localhost:8000/get"

	response, err := http.Get(url)
	if err != nil {
		panic(err)
	}
	defer response.Body.Close()

	fmt.Println("Status code:", response.Status)
	fmt.Println("Content length:", response.ContentLength)

	var responseString strings.Builder
	content, _ := io.ReadAll(response.Body)
	bytecount, _ := responseString.Write(content)

	fmt.Println("ByteCount is: ", bytecount)
	fmt.Println(responseString.String())

	// fmt.Println("Content:", content)
	// fmt.Println("Content:", string(content))

}

func PerformPostJsonRequest() {
	const myurl	= "http://localhost:8000/post"

	// fake Json Payload
	requestBody := strings.NewReader(`
		{
			"coursename": "Let's go with golag",
			"price": 0,
			"platform": "LearnCodeOnline.in"
		}
	`)

	response, err := http.Post(myurl, "application/json", requestBody)
	if err != nil {
		panic(err)
	}
	defer response.Body.Close()
	content, _ := io.ReadAll(response.Body)

	fmt.Println(string(content))
}

func PerformPostFormRequest() {
	const myurl	= "http://localhost:8000/postform"

	// formdata
	data := url.Values{}
	data.Add("firstname", "Himanshu")
	data.Add("lastname", "Kumar")
	data.Add("email", "himanshu@go.dev")

	response, err := http.PostForm(myurl, data)
	if err != nil {
		panic(err)
	}
	defer response.Body.Close()
	content, _ := io.ReadAll(response.Body)
	fmt.Println(string(content))
}