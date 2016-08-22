package main

import "fmt"

func DivideByZero(x int) int {
	return 10 / x
}

func fungsi1() {
	fmt.Println("Fungsi 1")
}

func fungsi2() {
	fmt.Println("Fungsi 2")
}

func main() {

	/* example of defer and recover */
	defer func() {
		if err := recover(); err != nil {
			fmt.Println(err)
		}
	}()	

	DivideByZero(0)
	
	/* Never Executed */
	panic("ERROR")

	/* Never Executed (remove DivideByZero() and panic("ERROR") to enable ) */
	/* defer more than one become stack */
	defer fungsi1()
	defer fungsi2()
	

}
