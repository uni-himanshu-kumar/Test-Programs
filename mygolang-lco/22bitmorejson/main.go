package main

import (
	"encoding/json"
	"fmt"
)

type course struct {
	Name     string `json:"coursename"`
	Price    int
	Platform string   `json:"website"`
	Password string   `json:"-"`
	Tags     []string `json:"tags,omitempty"` // No space after comma
}

func main() {
	fmt.Println("Welcome to JSON in Go!")
	// EncodeJson()
	DecodeJson()
}

func EncodeJson() {
	lcoCourses := []course{
		{"ReactJS Bootcamp", 299, "LearnCodeOnline.in", "abc@123", []string{"web-dev", "js"}},
		{"Mern Bootcamp", 199, "LearnCodeOnline.in", "bcd@123", []string{"full-stack", "js"}},
		{"Angular Bootcamp", 299, "LearnCodeOnline.in", "him@123", nil},
	}
	// finalJson, err := json.Marshal(lcoCourses)
	finalJson, err := json.MarshalIndent(lcoCourses, "", "\t")
	if err != nil {
		panic(err)
	}
	fmt.Printf("%s\n", finalJson)
}

func DecodeJson() {
	JsonDataFromWeb := []byte(`
		{
			"coursename": "ReactJS Bootcamp",
			"Price": 299,
			"website": "LearnCodeOnline.in",
			"tags": ["web-dev","js"]
        }
	`)

	var lcoCourse course
	checkValid := json.Valid(JsonDataFromWeb)
	if checkValid {
		fmt.Println("JSON was Valid")
		json.Unmarshal(JsonDataFromWeb, &lcoCourse)
		fmt.Printf("%#v\n", lcoCourse)
	} else {
		fmt.Println("JSON was not Valid")
	}

	var myOnlineData map[string]interface{}
	json.Unmarshal(JsonDataFromWeb, &myOnlineData)
	fmt.Printf("%#v\n", myOnlineData)

	for k, v := range myOnlineData {
		fmt.Printf("Key is: %v and Value is: %v and Type is: %T\n", k, v, v)
	}
}
