fun main() {
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
}

// -------------------------------------------------------------------------//
                    //Filter

//(A) Filtering by Predicates

//Predicates are lambda functions returning Boolean. These are applied to each element in the collection.

//1. filter()
//Returns a list of elements matching the predicate.

private val numbers = listOf("one", "two", "three", "four")

private fun eg1(){
    println(numbers.filter { it.length > 3 })
// Output: [three, four]
}

//2. filterNot()
//Returns a list of elements that do not match the predicate.

private fun eg2(){
    println(numbers.filterNot { it.length > 3 })
// Output: [one, two]
}

//3. filterIndexed()
//Includes the index of each element in the predicate.

private fun eg3(){
    println(numbers.filterIndexed { index, _ -> index % 2 == 0 })
// Output: [one, three]
}

//4. filterIsInstance<T>()
//Filters elements of a specific type.

private fun eg4(){
    val mixed = listOf("one", 1, "two", 2.0)
    println(mixed.filterIsInstance<Int>())
// Output: [1]
}

//(B) Partitioning

//Splits the collection into two collections based on a predicate.

//1. partition()
//Returns a Pair of lists: elements matching the predicate and those that don’t.

private fun eg5(){
    val (match, rest) = numbers.partition { it.length > 3 }
    println(match) // [three, four]
    println(rest)  // [one, two]
}

//(C) Filtering by Collection Type

//Some filtering functions are designed specifically for maps.

//1. filterKeys()

//Filters map entries by their keys.

private val map = mapOf(1 to "one", 2 to "two", 3 to "three")

private fun eg6(){
    println(map.filterKeys { it % 2 == 1 })
    // Output: {1=one, 3=three}
}

//2. filterValues()
//Filters map entries by their values.

private fun eg7() {
    println(map.filterValues { it.length > 3 })
    // Output: {3=three}
}

//3. filter() for Maps
//Filters entries by a predicate on key-value pairs.

private fun eg8() {
    println(map.filter { (key, value) -> key % 2 == 0 && value.length > 3 })
    // Output: {}
}

//(D) Destination Filtering

//For mutable collections, Kotlin provides functions to filter elements into a destination collection.

//1. filterTo()
//Filters elements into a specified destination mutable collection.

private val result = mutableListOf<String>()

private fun eg9(){
    numbers.filterTo(result) { it.length > 3 }
    println(result)
    // Output: [three, four]
}

//filterNotTo()
//Filters elements not matching the predicate into a destination collection.

private fun eg10(){
    numbers.filterNotTo(result) { it.length > 3 }
    println(result)
    // Output: [one, two]
}

//Combining Filters
//Multiple filters can be chained for complex conditions.
private fun eg11() {
    println(numbers.filter { it.startsWith("t") }.filter { it.length > 3 })
// Output: [three]
}

//Type-Specific Filtering in Mixed Collections
//Useful for collections with diverse types.
private fun eg12() {
    val mixed = listOf("string", 1, 2.0, 3L)
    println(mixed.filterIsInstance<Number>())
// Output: [1, 2.0, 3]
}

