package main

import "fmt"

/* void function */
func fungsi1() {
	fmt.Println("fungsi 1")
}

/* return function */
func fungsi2(x int) int {
	return x + 1
}

/* return function more than one value */
func fungsi3(x, y, z int) (int, int, int) {
	return x + 3, y + 3, z + 3 
}

/* function more than one argument */
func fungsi4(arg ...string) {
	fmt.Println(arg[0], arg[1], arg[2])
}

/* return local variable function */
func fungsi5() (x int) {
	x = 12
	return
}

func main() {
	fungsi1()
	fmt.Println( fungsi2(2) )
	fmt.Println( fungsi3(1,2,3) )
	fungsi4( "lebih", "dari", "satu" )
	fmt.Println( fungsi5() )
}
