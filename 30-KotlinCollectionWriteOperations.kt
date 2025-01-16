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
}
//----------------------------------------------------------------------------------//
//                          Write Operations

//In Kotlin, mutable collections allow you to modify their content through various
// write operations. These operations enable adding, removing, and updating elements
// within collections such as lists, sets, and maps.

//Adding Elements:

//add(): Appends a single element to the end of a list or set.
private fun eg1() {
    val numbers = mutableListOf(1, 2, 3, 4)
    numbers.add(5)
    println(numbers) // Outputs: [1, 2, 3, 4, 5]
}
//addAll(): Adds multiple elements from an iterable, sequence, or array to a collection. When used with lists, elements are added in the order they appear in the argument. You can also specify an index to insert elements at a particular position.
private fun eg2() {
    val numbers = mutableListOf(1, 2, 5, 6)
    numbers.addAll(arrayOf(7, 8))
    println(numbers) // Outputs: [1, 2, 5, 6, 7, 8]

    numbers.addAll(2, setOf(3, 4))
    println(numbers) // Outputs: [1, 2, 3, 4, 5, 6, 7, 8]
}
//plusAssign (+=): An in-place operator that appends an element or another collection to a mutable collection.
private fun eg3() {
    val numbers = mutableListOf("one", "two")
    numbers += "three"
    println(numbers) // Outputs: [one, two, three]

    numbers += listOf("four", "five")
    println(numbers) // Outputs: [one, two, three, four, five]
}
//Removing Elements:
//
//remove(): Removes the first occurrence of the specified element from the collection.
private fun eg4() {
    val numbers = mutableListOf(1, 2, 3, 4, 3)
    numbers.remove(3)
    println(numbers) // Outputs: [1, 2, 4, 3]
}
//removeAll(): Removes all elements that are present in the specified collection or match a given predicate.
private fun eg5() {
    val numbers = mutableListOf(1, 2, 3, 4)
    numbers.removeAll { it > 2 }
    println(numbers) // Outputs: [1, 2]
}
//retainAll(): Keeps only the elements that are present in the specified collection or match a given predicate, removing all others.
private fun eg6() {
    val numbers = mutableListOf(1, 2, 3, 4)
    numbers.retainAll { it >= 3 }
    println(numbers) // Outputs: [3, 4]
}
//clear(): Removes all elements from the collection, leaving it empty.
private fun eg7() {
    val numbers = mutableListOf(1, 2, 3, 4)
    numbers.clear()
    println(numbers) // Outputs: []
}
//minusAssign (-=): An in-place operator that removes a single element or all elements in another collection from the mutable collection.
private fun eg8() {
    val numbers = mutableListOf("one", "two", "three", "four")
    numbers -= "three"
    println(numbers) // Outputs: [one, two, four]

    numbers -= listOf("four", "five")
    println(numbers) // Outputs: [one, two]
}
//Updating Elements:
//
//For lists, you can update elements at specific indices:

private fun eg9() {
    val numbers = mutableListOf(1, 2, 3, 4)
    numbers[2] = 5
    println(numbers) // Outputs: [1, 2, 5, 4]
}
//For maps, you can add or update key-value pairs:

private fun eg10() {
    val map = mutableMapOf("a" to 1, "b" to 2)
    map["c"] = 3 // Adds a new key-value pair
    map["a"] = 4 // Updates the value for key "a"
    println(map) // Outputs: {a=4, b=2, c=3}
}
//Note: For sets, updating an element involves removing the old element and adding a new one, as sets do not support direct modification of elements.
//
//These write operations provide flexibility in managing and modifying collections in Kotlin, allowing for dynamic and efficient data manipulation.