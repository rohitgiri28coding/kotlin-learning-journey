import java.util.Scanner

fun main(){
    eg1()
    eg2()
    eg3()
    eg4()
    eg5()
}
//----------------------------------------------------------------------------------//
//                          Standard Input

//In Kotlin, reading input from the standard input (typically the keyboard) is
// straightforward using the readln() function. This function reads an entire line
// entered by the user and returns it as a string.
//
//Basic Usage of readln():
//
//To read a line of input and store it in a variable:

private fun eg1() {
    val userInput = readln()
    println("You entered: $userInput")
}
//If the user inputs Hello, Kotlin!, the output will be:
//You entered: Hello, Kotlin!


//Converting Input to Other Data Types:
//
//By default, readln() returns a string. To work with other data types, you can convert
// the input using functions like .toInt(), .toDouble(), .toBoolean(), etc.

private fun eg2() {
// Reading an integer
    val number = readln().toInt()
    println("You entered the number: $number")

// Reading a double
    val decimal = readln().toDouble()
    println("You entered the decimal: $decimal")

// Reading a boolean
    val boolValue = readln().toBoolean()
    println("You entered the boolean: $boolValue")
}
//Handling Multiple Inputs:
//
//To read multiple values from a single line, you can use the .split() function to
// divide the input based on a delimiter and then convert each part accordingly.

private fun eg3() {
// Reading multiple integers separated by spaces
    val numbers = readln().split(' ').map { it.toInt() }
    println("You entered the numbers: $numbers")

// Reading multiple doubles separated by commas
    val decimals = readln().split(',').map { it.toDouble() }
    println("You entered the decimals: $decimals")
}
//Safe Input Handling:
//
//Directly converting input using functions like .toInt() can throw exceptions if the
// input is not in the expected format. To handle such cases gracefully, Kotlin provides
// nullable conversion functions like .toIntOrNull().

private fun eg4() {
    val number = readln().toIntOrNull()
    if (number != null) {
        println("You entered the number: $number")
    } else {
        println("Invalid number entered.")
    }
}
//Similarly, the readlnOrNull() function reads a line from the input and returns null
// if the end of the input is reached, which helps in safely handling input in certain
// scenarios.

//Alternative: Using Java's Scanner Class:
//
//For more advanced input handling, especially when dealing with different data types
// or multiple inputs, you can use Java's Scanner class, which is interoperable with
// Kotlin.

private fun eg5(){
    val scanner = Scanner(System.`in`)

    // Reading a string
    println("Enter a string:")
    val stringInput = scanner.nextLine()
    println("You entered: $stringInput")

    // Reading an integer
    println("Enter an integer:")
    val intInput = scanner.nextInt()
    println("You entered: $intInput")

    // Always close the scanner
    scanner.close()
}
//While Scanner provides more flexibility, it's generally slower than readln() and
// should be used when its specific functionalities are required
