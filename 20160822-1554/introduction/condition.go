package main

import "fmt"

func main() {
	
	x := 8
	
	/* condition 1 */
	if x > 0 && x < 11 {
		fmt.Println("Angka Antara 1 dan 10")
	} else if x > 10 && x < 100 {
		fmt.Println("Angka lebih besar dari 10 dan kurang dari 100")
	} else {
		fmt.Println("Angka selain kurang dari 1 dan lebih besar atau sama dengan 100")
	}  

	var y int	
	fmt.Println("Masukkan nilai : ")
	fmt.Scanln(&y)	

	
	/* condition 2 */
	switch y {
		case 1 : fmt.Println("Sama dengan 1")
		case 2 : fmt.Println("Sama dengan 2")
		default : fmt.Println("Bukan 1 dan 2")
	}
	
}
