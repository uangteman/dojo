package main

import (

"fmt"
"time"

)

func Printer(ch chan string) {
	for {
		str := <- ch
		fmt.Println(str)
		time.Sleep(time.Second * 1)		
	}
}

func Pinger(ch chan string) {
	for {
		 ch <- "ping"
	}

}

func main() {
	var ch chan string = make(chan string)
	go Pinger(ch)
	go Printer(ch)	
	
	/* press ENTER to stop */
	fmt.Println("Press ENTER to stop")
	
	var s string
	fmt.Scanln(&s)
}
