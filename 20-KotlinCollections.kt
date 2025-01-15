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
}
//------------------------------------------------------------------------------------//
//                          Collections

// collections – groups of a variable number of items (possibly zero) that are
// significant to the problem being solved and are commonly operated on.

//Objects in a collection are called elements or items.

/*
The following collection types are relevant for Kotlin:
 1) List is an ordered collection with access to elements by indices – integer numbers
 that reflect their position. Elements can occur more than once in a list.

 2) Set is a collection of unique elements. It reflects the mathematical
 abstraction of set: a group of objects without repetitions.
 Generally, the order of set elements has no significance.

 3) Map (or dictionary) is a set of key-value pairs. Keys are unique, and each of them
 maps to exactly one value. The values can be duplicates. Maps are useful for storing
 logical connections between objects.
*/

//Note: Arrays are not a type of collection.

//Collection types

// A read-only interface that provides operations for accessing collection elements.
// A mutable interface that extends the corresponding read-only interface with write operations: adding, removing, and updating its elements.


//ArrayDeque
//ArrayDeque<T> is an implementation of a double-ended queue, which allows you to add
// or remove elements both at the beginning or end of the queue.
// As such, ArrayDeque also fills the role of both a Stack and Queue data structure
// in Kotlin. Behind the scenes, ArrayDeque is realized using a resizable array
// that automatically adjusts in size when required:

private fun eg1() {
    val deque = ArrayDeque(listOf(1, 2, 3))

    deque.addFirst(0)
    deque.addLast(4)
    println(deque) // [0, 1, 2, 3, 4]

    println(deque.first()) // 0
    println(deque.last()) // 4

    deque.removeFirst()
    deque.removeLast()
    println(deque) // [1, 2, 3]
}


//Constructing collections

//he most common way to create a collection is with the standard library functions
// listOf<T>(), setOf<T>(), mutableListOf<T>(), mutableSetOf<T>()

//Create with collection builder functions

//Another way of creating a collection is to call a builder function – buildList(), buildSet(), or buildMap().

private fun eg2() {
    val map =
        buildMap { // this is MutableMap<String, Int>, types of key and value are inferred from the `put()` calls below
            put("a", 1)
            put("b", 0)
            put("c", 4)
        }

    println(map) // {a=1, b=0, c=4}
}

//Empty collections

//There are also functions for creating collections without any elements:
// emptyList(), emptySet(), and emptyMap().

private val empty = emptyList<String>()

//Concrete type constructors

//To create a concrete type collection, such as an ArrayList or LinkedList,
// you can use the available constructors for these types.
// Similar constructors are available for implementations of Set and Map.


//private val linkedList = LinkedList<String>(listOf("one", "two", "three"))
//private val presizedSet = HashSet<Int>(32)

//Copy

//Collection copying functions from the standard library create shallow copy collections with
// references to the same elements. Thus, a change made to a collection element
// reflects in all its copies.

private fun eg3(){
    val sourceList = mutableListOf(1, 2, 3)
    val referenceList = sourceList
    referenceList.add(4)
    println("Source size: ${sourceList.size}")
}

//Collection copying functions, such as toList(), toMutableList(), toSet() and others,
// create a snapshot of a collection at a specific moment. Their result is a new
// collection of the same elements. If you add or remove elements from the original
// collection, this won't affect the copies. Copies may be changed independently of
// the source as well.

//Iterators

//Iterators – objects that provide access to the elements sequentially without
// exposing the underlying structure of the collection. Iterators are useful when
// you need to process all the elements of a collection one-by-one.
//Obtained for inheritors of Iterable<T> (e.g., Set, List) via iterator().

//Basic Methods:
//hasNext(): Checks if the next element exists.
//next(): Retrieves the next element and moves the iterator forward.

private val numbers = listOf("one", "two", "three", "four")

private fun eg4(){
    val iterator = numbers.iterator()
    while (iterator.hasNext()) {
        println(iterator.next())
    }
// Output: one, two, three, four
}

//Alternative Iteration Methods
//For Loop: Implicitly uses the iterator.
private fun eg5(){
    for (item in numbers) {
        println(item)
    }
}

//forEach Function: Higher-order function for streamlined iteration.

private fun eg6(){
    numbers.forEach {
        println(it)
    }
}


//List Iterators
//Special Feature: Bidirectional traversal (hasPrevious(), previous()).
//Index Functions:
//nextIndex(): Index of the next element.
//previousIndex(): Index of the previous element.

private fun eg7(){
    val listIterator = numbers.listIterator()

// Forward iteration
    while (listIterator.hasNext()) listIterator.next()

// Backward iteration
    while (listIterator.hasPrevious()) {
        println("Index: ${listIterator.previousIndex()}, value: ${listIterator.previous()}")
    }
// Output:
// Index: 3, value: four
// Index: 2, value: three
// Index: 1, value: two
// Index: 0, value: one

}

//Mutable Iterators
//MutableIterator: Extends Iterator with remove() to delete elements during iteration.

private fun eg8() {
    val numbers = mutableListOf("one", "two", "three", "four")
    val mutableIterator = numbers.iterator()

    mutableIterator.next()
    mutableIterator.remove()
    println(numbers)
// Output: [two, three, four]
}

//MutableListIterator: Supports adding and replacing elements with add() and set().

private fun eg9(){
    val numbers = mutableListOf("one", "four", "four")
    val mutableListIterator = numbers.listIterator()

    mutableListIterator.next()
    mutableListIterator.add("two")
    println(numbers)
// Output: [one, two, four, four]

    mutableListIterator.next()
    mutableListIterator.set("three")
    println(numbers)
// Output: [one, two, three, four]

}


//Ranges

//A sequence of values created using the rangeTo() or rangeUntil() functions from
// the kotlin.ranges package.

//Types:

// 1) Closed-ended Range: Includes both start and end values. Created with .. operator.
//      println(4 in 1..4) // true

// 2) Open-ended Range: Excludes the end value. Created with ..< operator.
//      println(4 in 1..<4) // false

// Use in Loops:
//      for (i in 1..4) print(i) // 1234
//      for (i in 4 downTo 1) print(i) // 4321          // Reverse Order Iteration

//Arbitrary Steps: Use step to define the increment.

//for (i in 0..8 step 2) print(i) // 02468
//for (i in 8 downTo 0 step 2) print(i) // 86420

//Progressions

//Arithmetic sequences derived from ranges of Int, Long, or Char. Progressions have:
//First Element
//Last Element
//Non-zero Step

//Implicit Progressions: Created when iterating a range, with a default step of 1.
//  for (i in 1..10) print(i) // 12345678910

//Custom Step Progressions: Defined explicitly with step.
//  for (i in 1..8 step 2) print(i) // 1357

//Last Element Calculation:
//Positive Step: The largest value ≤ the end such that (last - first) % step == 0.
//Negative Step: The smallest value ≥ the end such that (last - first) % step == 0.

//  for (i in 1..9 step 3) print(i) // 147

//Collections and Progressions
//Progressions implement Iterable<N> (N = Int, Long, Char), enabling compatibility with collection functions like map and filter.

//  println((1..10).filter { it % 2 == 0 }) // [2, 4, 6, 8, 10]
