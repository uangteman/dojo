package main

import "fmt"

func main() {

	/* Map Declaration*/
	var m map[string]int = make(map[string]int)

	/* map initilization (set key and value) */
	m["key"] = 1
	
	/* Print Map which key "key" */
	fmt.Println(m["key"]) 
	
	/* Delete Map which key "key" */
	delete(m, "key")

	fmt.Println(m["key"])
}

