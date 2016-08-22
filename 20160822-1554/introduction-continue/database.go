package main

import (

"fmt"
"database/sql"

/* From go get github.com/mattn/go-sqlite3 */
"github.com/mattn/go-sqlite3" 
)

func main() {
	db, err := sql.Open("sqlite3", "/Applications/MAMP/db/sqlite/preload.db")

	if(err != nil) {
		fmt.Println(err)
	}
	
	rows, err := db.Query("SELECT * FROM home_status")

	if(err != nil) {
                fmt.Println(err)
        }

	for rows.Next() {


                var home_status_id int
                var home_status_name_en string
                var home_status_name_in string
                err = rows.Scan(&home_status_id, &home_status_name_en, &home_status_name_in)
                fmt.Println(home_status_name_en)
       }
}
