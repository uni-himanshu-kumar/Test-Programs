package main

import (
	"fmt"
	"net/url"
)

const myurl string = "https://no-js.club:3000/apple?color=red&size=small"

func main() {
	fmt.Println("Welcome to handling URLs in Go!")
	fmt.Println(myurl)

	result, _ := url.Parse(myurl)

	fmt.Println(result.Scheme)
	fmt.Println(result.Host)
	fmt.Println(result.Path)
	fmt.Println(result.Port())
	fmt.Println(result.RawQuery)

	qparams := result.Query()
	fmt.Println(qparams)

	for _, val := range qparams {
		fmt.Println(val)
	}

	partsOfUrl := &url.URL{
		Scheme: "https",
		Host: "no-js.club",
		Path: "/apple",
		RawQuery: "user=himanshu",
	}

	anotherUrl := partsOfUrl.String()
	fmt.Println(anotherUrl)
}