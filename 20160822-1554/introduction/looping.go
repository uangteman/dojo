package main

import "fmt"

func main() {

	fmt.Println("Loop 1")
		
	/* Loop 1 */
	for i := 1 ; i < 10 ; i++ {
		fmt.Println(i)
	}

	fmt.Println("Loop 2")

	/* Loop 2 */
	i := 0
	for i <= 12 {
		fmt.Println(i)
		i++
	}
}
