fun main(){
    eg1()
    eg2()
    eg3()
    eg4()
    eg5()
    eg6()
}
// -------------------------------------------------------------------------------//
//                  Collection Elements

//The Kotlin standard library provides numerous functions to retrieve or manipulate
// specific elements in collections, making it easier to work with data efficiently
// and intuitively.

//1. Accessing Elements
    //elementAt() and Variants

//elementAt(index: Int): Returns the element at the specified index.
// Throws an exception if the index is out of bounds.

//elementAtOrElse(index: Int, defaultValue: (Int) -> T): Returns the element at the
// specified index or a default value if the index is out of bounds.

//elementAtOrNull(index: Int): Returns the element at the specified index or null if
// the index is out of bounds.

private fun eg1(){
    val list = listOf(1, 2, 3)
    println(list.elementAt(1)) // Output: 2
    println(list.elementAtOrNull(5)) // Output: null
    println(list.elementAtOrElse(5) { it * 2 }) // Output: 10
}

//2. First and Last Elements
//first() and Variants
//
//first(): Returns the first element. Throws an exception if the collection is empty.
//firstOrNull(): Returns the first element or null if the collection is empty.
//first(predicate: (T) -> Boolean): Returns the first element matching the predicate. Throws an exception if no match is found.
//firstOrNull(predicate: (T) -> Boolean): Returns the first element matching the predicate or null if no match is found.

private val numbers = listOf(1, 2, 3)

private fun eg2(){
    println(numbers.first()) // Output: 1
    println(numbers.firstOrNull { it > 1 }) // Output: 2
}

//last() and Variants
//
//last(): Returns the last element. Throws an exception if the collection is empty.
//lastOrNull(): Returns the last element or null if the collection is empty.
//last(predicate: (T) -> Boolean): Returns the last element matching the predicate. Throws an exception if no match is found.
//lastOrNull(predicate: (T) -> Boolean): Returns the last element matching the predicate or null if no match is found.

private fun eg3(){
    println(numbers.last()) // Output: 3
    println(numbers.lastOrNull { it < 3 }) // Output: 2
}

//3. Random Elements
//random() and randomOrNull()
//
//random(): Returns a random element from the collection. Throws an exception if the collection is empty.
//randomOrNull(): Returns a random element or null if the collection is empty.

private fun eg4(){
    println(numbers.random()) // Output: Any random element from the list
}

//4. Retrieving Single Elements
//single() and Variants
//
//single(): Returns the single element from the collection. Throws an exception if the collection is empty or contains more than one element.
//singleOrNull(): Returns the single element or null if the collection is empty or contains more than one element.
//single(predicate: (T) -> Boolean): Returns the single element matching the predicate. Throws an exception if no match is found or more than one match is found.
//singleOrNull(predicate: (T) -> Boolean): Returns the single element matching the predicate or null if no match is found or more than one match is found.

private fun eg5(){
    val singleList = listOf(42)
    println(singleList.single()) // Output: 42
    println(numbers.singleOrNull { it > 5 }) // Output: null
}

//5. Checking Collection Characteristics

//contains()
//Checks whether a specified element exists in the collection.

//containsAll()
//Checks whether all elements in a specified collection exist in the current collection.

private fun eg6(){
    println(numbers.contains(2)) // Output: true
    println(numbers.containsAll(listOf(1, 3))) // Output: true
}


