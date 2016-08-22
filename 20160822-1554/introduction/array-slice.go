package main

import "fmt"

func main() {
	
	/* array declaration */
	var array [12]int

	/* Initilize array */
	for i := 0 ; i < 12 ; i++ {
		array[i] = i
	}

	/* Print array */
	for i := 0 ; i < 12 ; i++ {
                fmt.Println(array[i])
        }
	
	/* slice declaration */
	var slice []int

	/* Allocation Slice with capacity 12 */
	slice = make([]int, 12)
	
	/* Initilize slice */
        for i := 0 ; i < 12 ; i++ {
                slice[i] = i
        }

	/* slice */
	slice1 := []int{100, 101, 102}

	/* copy slice properties (only available for slice and slice) */
	copy(slice, slice1)
	fmt.Println(slice)	

	/* append */
	slice3 := append(slice, 103, 104)
	fmt.Println(slice3)	
	

	/* print slice or array */
	fmt.Println(array[0:3])
	fmt.Println(slice[0:3])

	

  
}
