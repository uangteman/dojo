How to using Go
======

## Install Manual
#### Download Package
* [Download](https://golang.org/doc/install)

#### Extract The Files
* tar -C YOUR_PATH -xzf go1.7.darwin-amd64.tar.gz

#### Add this script to your terminal
* export GOROOT=YOUR_GO_PACKAGE_INSTALLER
* export PATH=$PATH:$GOROOT/bin
* Skip this step if you are using package installer

#### Setup Project
* export GOPATH=YOUR_PROJECT_FOLDER
* export GOBIN=$GOPATH/bin

## Build and/or Install Project
#### Project Structure
* Create 'src', 'pkg', and 'bin' folder in your project folder

#### Run your Project
* Add all your go source code to 'src' folder
* Go to bin folder. Then, run 'go install YOUR_GO_MAIN_FILE
* Your executable file should now ready to execute (available on bin folder)

## Import your own package
#### create your own import folder in 'src'
* for example 'src/com/uangteman/util. Add your source file here
* Make sure you add your own package (not main) to the file
* import in main program like this. If you want to import from 'src/com/uangteman/util'. The import will be import 'com/uangteman/util'
* use the function in main program like this. util.YOUR_FUNCTION()
