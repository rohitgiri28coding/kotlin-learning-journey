fun main(){
    eg1()
    eg2()
    eg3()
    eg4()
    eg5()
    eg6()
    eg7()
    eg8()
    eg9()
    eg10()
    eg11()
    eg12()
    eg13()
}
//----------------------------------------------------------------------------------//
//                          List-Specific Operations:
//
//Lists in Kotlin are ordered collections that allow duplicate elements. They support
// various operations to retrieve, find, and manipulate elements based on their positions.
//
//Retrieving Elements by Index:
//Use the get() function or the shorthand [index] syntax to access elements at a
// specific index.
//Functions like getOrElse() and getOrNull() help handle cases where the index might
// be out of bounds, providing default values or null instead of throwing exceptions.
private fun eg1() {
    val numbers = listOf(1, 2, 3, 4)
    println(numbers[0]) // Outputs: 1
    println(numbers.getOrNull(5)) // Outputs: null
    println(numbers.getOrElse(5) { it }) // Outputs: 5
}
//Retrieving List Parts:
//The subList() function returns a view of a specified range within the list.
// Changes in the sublist reflect in the original list and vice versa.
private fun eg2() {
    val numbers = (0..13).toList()
    println(numbers.subList(3, 6)) // Outputs: [3, 4, 5]
}
//Finding Element Positions:
//Functions like indexOf() and lastIndexOf() return the first and last positions of a
// specified element, respectively. If the element is not found, they return -1.
//For elements matching a predicate, indexOfFirst() and indexOfLast() can be used.
private fun eg3() {
    val numbers = listOf(1, 2, 3, 4, 2, 5)
    println(numbers.indexOf(2)) // Outputs: 1
    println(numbers.lastIndexOf(2)) // Outputs: 4
}
//Binary Search in Sorted Lists:
//The binarySearch() function performs a binary search on a sorted list, returning
// the index of the element or a negative value indicating the insertion point if the
// element is not found.
private fun eg4() {
    val numbers = mutableListOf("one", "two", "three", "four")
    numbers.sort()
    println(numbers.binarySearch("two")) // Outputs: 3
}


//                          Set-Specific Operations
//
//Sets are collections of unique elements. Kotlin provides functions to perform
// standard set operations like union, intersection, and subtraction.
//
//Union:
//The union() function returns a set containing all distinct elements from both
// collections. The order of elements in the result depends on the order of the
// operands.
private fun eg5() {
    val setA = setOf("one", "two", "three")
    val setB = setOf("four", "five")
    println(setA union setB) // Outputs: [one, two, three, four, five]
}
//Intersection:
//The intersect() function returns a set containing elements that are present in both
// collections.
private fun eg6() {
    val setA = setOf("one", "two", "three")
    val setB = setOf("two", "three", "four")
    println(setA intersect setB) // Outputs: [two, three]
}
//Subtraction:
//The subtract() function returns a set containing elements from the first collection
// that are not present in the second collection.
private fun eg7() {
    val setA = setOf("one", "two", "three")
    val setB = setOf("three", "four")
    println(setA subtract setB) // Outputs: [one, two]
}


//                       Map-Specific Operations
//
//Maps are collections of key-value pairs, where each key is unique. Kotlin provides
// various operations to retrieve, filter, and manipulate entries in maps.
//
//Retrieving Values by Key:
//Use the get() function or the shorthand [key] syntax to access values associated
// with a specific key. If the key is not found, get() returns null.
//Functions like getOrElse() and getOrDefault() provide default values when the key
// is not present.
private fun eg8() {
    val numbersMap = mapOf("one" to 1, "two" to 2, "three" to 3)
    println(numbersMap["one"]) // Outputs: 1
    println(numbersMap.getOrDefault("four", 10)) // Outputs: 10
}

//Filtering Maps:
//The filter() function allows filtering entries based on both key and value.
//filterKeys() and filterValues() functions enable filtering based solely on keys or
// values, respectively.
private fun eg9() {
    val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
    val filteredKeysMap = numbersMap.filterKeys { it.endsWith("1") }
    val filteredValuesMap = numbersMap.filterValues { it < 10 }
    println(filteredKeysMap) // Outputs: {key1=1, key11=11}
    println(filteredValuesMap) // Outputs: {key1=1, key2=2, key3=3}
}
//Plus and Minus Operators:
//
//Plus (+) Operator: The plus operator allows you to combine two maps or add a single
// key-value pair to an existing map. If keys overlap, the values from the right-hand
// operand will replace those in the left-hand operand.
private fun eg10() {
    val mapA = mapOf("key1" to 1, "key2" to 2)
    val mapB = mapOf("key2" to 20, "key3" to 3)
    val combinedMap = mapA + mapB
    println(combinedMap) // Outputs: {key1=1, key2=20, key3=3}

    val singleAdd = mapA + ("key4" to 4)
    println(singleAdd) // Outputs: {key1=1, key2=2, key4=4}
}
//Minus (-) Operator: The minus operator is used to remove entries from a map based
// on keys. You can pass a single key or a collection of keys.
private fun eg11() {
    val map = mapOf("key1" to 1, "key2" to 2, "key3" to 3)
    val reducedMap = map - "key2"
    println(reducedMap) // Outputs: {key1=1, key3=3}

    val multipleRemoval = map - listOf("key1", "key3")
    println(multipleRemoval) // Outputs: {key2=2}
}
//Iterating Over Maps:
//
//Maps in Kotlin can be iterated over using loops or functional programming constructs:
//
//Using Loops: You can iterate over entries, keys, or values.
private fun eg12() {
    val map = mapOf("one" to 1, "two" to 2, "three" to 3)
    for ((key, value) in map) {
        println("$key -> $value")
    }
}
//Using forEach: The forEach function allows you to operate on each entry with a lambda.
private fun eg13() {
    val map = mapOf("one" to 1, "two" to 2, "three" to 3)
    map.forEach { (key, value) -> println("$key -> $value") }
}