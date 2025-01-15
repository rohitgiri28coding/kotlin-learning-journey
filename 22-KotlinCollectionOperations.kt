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
    eg14()
    eg15()
    eg16()
}
//------------------------------------------------------------------------------------//
//              Operations on Collection

//Extension and Member Functions
//1. Member Functions: These are essential operations defined within collection interfaces. For instance, Collection includes isEmpty() to check for emptiness, and List provides get() for accessing elements by index.
//Extension Functions: These functions extend the capabilities of collections without modifying their original implementation. They encompass operations like filtering, transformation, and ordering.


//Common Operations

//Common operations applicable to both read-only and mutable collections include:
//
//Transformations: Functions that create new collections based on existing ones, such as map() and flatMap().
//Filtering: Functions like filter() and filterNot() that select elements matching specific criteria.
//Plus and Minus Operators: Operators that combine or subtract collections, e.g., plus() and minus().
//Grouping: Functions that group elements based on a selector function, such as groupBy().
//Retrieving Collection Parts: Functions like slice() and take() that extract specific parts of a collection.
//Retrieving Single Elements: Functions such as first(), last(), and elementAt() for accessing individual elements.
//Ordering: Functions that sort collections, including sorted() and sortedBy().
//Aggregate Operations: Functions like fold(), reduce(), and sum() that compute a single result from a collection.

//These operations return new collections without modifying the original ones.
// To store the results, assign them to variables or pass them to other functions.

private fun eg1(){
    val numbers = listOf("one", "two", "three", "four")
    val longerThan3 = numbers.filter { it.length > 3 }
    println("Numbers longer than 3 chars are $longerThan3")
// Output: Numbers longer than 3 chars are [three, four]
}

//For operations that append results to an existing mutable collection, functions
// with the To suffix, such as filterTo() and associateTo(), allow specifying a destination collection.

private fun eg2(){
    val numbers = listOf("one", "two", "three", "four")
    val filterResults = mutableListOf<String>()
    numbers.filterTo(filterResults) { it.length > 3 }
    println(filterResults)
// Output: [three, four]
}

//Write Operations

//Mutable collections support operations that modify their content, including adding,
// removing, and updating elements. For example, add() inserts an element,
// while remove() deletes a specified element.

private fun eg3(){
    val numbers = mutableListOf("one", "two", "three")
    numbers.add("four")
    println(numbers)
// Output: [one, two, three, four]
}

// Certain operations offer both in-place and out-of-place variants.
// For instance, sort() sorts a mutable collection in place, altering its state,
// whereas sorted() returns a new collection with the elements in sorted order,
// leaving the original unchanged.

private fun eg4(){
    val numbers = mutableListOf("three", "one", "four", "two")
    val sortedNumbers = numbers.sorted()
    println(numbers)        // Output: [three, one, four, two]
    println(sortedNumbers)  // Output: [four, one, three, two]
    numbers.sort()
    println(numbers)        // Output: [four, one, three, two]
}

//                  Collection transformation operations

//1. Mapping Transformations

//map(): Applies a given lambda function to each element in a collection, returning a list of the results.

private val numbers = setOf(1, 2, 3)

private fun eg5()
{
    println(numbers.map { it * 3 }) // Output: [3, 6, 9]
}

//mapIndexed(): Similar to map(), but the lambda function also receives the index of each element.
private fun eg6(){
    println(numbers.mapIndexed { idx, value -> value * idx }) // Output: [0, 2, 6]
}

//mapNotNull(): Applies a transformation and filters out null results.
private fun eg7(){
    println(numbers.mapNotNull { if (it == 2) null else it * 3 }) // Output: [3, 9]
}

//mapKeys() and mapValues(): Transform the keys or values of a map, respectively.
private fun eg8(){
    val numbersMap = mapOf("key1" to 1, "key2" to 2)
    println(numbersMap.mapKeys { it.key.uppercase() }) // Output: {KEY1=1, KEY2=2}
    println(numbersMap.mapValues { it.value * 2 })    // Output: {key1=2, key2=4}
}

//2. Zipping Transformations

//zip(): Combines two collections into a list of pairs. If the collections have different sizes, the result length matches the shorter collection.
private fun eg9(){
    val colors = listOf("red", "brown", "grey")
    val animals = listOf("fox", "bear", "wolf")
    println(colors.zip(animals)) // Output: [(red, fox), (brown, bear), (grey, wolf)]
}

//zip() with a Transformation: Applies a transformation function to each pair of elements from two collections.
private fun eg10(){
    val colors = listOf("red", "brown", "grey")
    val animals = listOf("fox", "bear", "wolf")
    println(colors.zip(animals) { color, animal -> "The ${animal.capitalize()} is $color" })
    // Output: [The Fox is red, The Bear is brown, The Wolf is grey]
}

//unzip(): Splits a list of pairs into two lists, separating the first and second elements.
private fun eg11(){
    val numberPairs = listOf("one" to 1, "two" to 2)
    println(numberPairs.unzip()) // Output: ([one, two], [1, 2])
}

//Association Transformations

//associateWith(): Creates a map where the original collection elements are keys, and the values are produced by a transformation function.
private fun eg12(){
    val numbers = listOf("one", "two", "three")
    println(numbers.associateWith { it.length }) // Output: {one=3, two=3, three=5}
}

//associateBy(): Builds a map where keys are determined by a keySelector function applied to the elements, and values are the elements themselves.
private fun eg13(){
    val numbers = listOf("one", "two", "three", "four")

    println(numbers.associateBy { it.first().uppercaseChar() })
    println(numbers.associateBy(keySelector = { it.first().uppercaseChar() }, valueTransform = { it.length }))
}

//associate(): Allows creating a map by producing key-value pairs from the original collection elements.
private fun eg14(){
    val numbers = listOf("one", "two", "three", "four")
    println(numbers.associate { it.first().uppercaseChar() to it.length }) // Output: {O=3, T=5}
}

// Flattening Transformations

//flatten(): Merges nested collections into a single list.
private fun eg15(){
    val nestedList = listOf(listOf(1, 2), listOf(3, 4))
    println(nestedList.flatten()) // Output: [1, 2, 3, 4]
}

//flatMap(): Applies a transformation function to each element and flattens the resulting collections into a single list.
private fun eg16(){
    val fruits = listOf("apple", "banana")
    println(fruits.flatMap { it.toList() }) // Output: [a, p, p, l, e, b, a, n, a, n, a]
}
