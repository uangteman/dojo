package main

import "fmt"

type Point struct {
	x int
	y int
}

func main() {

	/* Structure Declaration */
	var p Point 

	/* Structure Initilization */
	p.x = 1
	p.y = 2

	fmt.Println("Point = ",p)
	
	/* Change point.x value */
	p.x = 3
	fmt.Println("x value = ", p.x)	

}
