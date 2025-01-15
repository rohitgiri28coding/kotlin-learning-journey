fun main(){
    eg1()
    eg2()
}
// -------------------------------------------------------------------------//
//              Plus and Minus Operator

// Kotlin provides intuitive operators (+ and -) for combining and subtracting elements
// from collections. These operations work seamlessly on both read-only and mutable
// collections while leaving the original collections unchanged for read-only types.

// Adding Elements (+)
private val numbers = listOf("one", "two", "three")

private fun eg1(){
//    Adding Single Elements
    println(numbers + "four")
// Output: [one, two, three, four]

//    Adding Multiple Elements
    println(numbers + listOf("four", "five"))
// Output: [one, two, three, four, five]

//    Adding an Array
    val array = arrayOf("six", "seven")
    println(numbers + array)
// Output: [one, two, three, six, seven]
}

// Removing Elements (-)

private fun eg2(){
    println(numbers - "two")
// Output: [one, three]
    println(numbers - listOf("one", "three"))
// Output: [two]
    val array = arrayOf("one", "two")
    println(numbers - array)
// Output: [three]
    val duplicates = listOf("one", "two", "two", "three")
    println(duplicates - "two")
// Output: [one, three]
}

//Mutable Collections

//The + and - operations return new collections for read-only types.
//For mutable collections, use methods like add, addAll, remove, and removeAll to modify the collection directly.