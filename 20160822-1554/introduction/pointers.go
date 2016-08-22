package main

import "fmt"

/* Try to change value (Value is not changed) */
func ChangedValue(x int){
	x = 20
}

/* Use Pointer to change value*/
func ChangeValue(x *int){
	*x = 20
}

func main() {

	var p *int
	x := 12
	p = &x
	
	
	ChangedValue(x)

   	fmt.Println(x) // x = 12
	
	ChangeValue(&x)

	fmt.Println(x) // x = 20

	fmt.Println(*p) // *p = 20		 

}
