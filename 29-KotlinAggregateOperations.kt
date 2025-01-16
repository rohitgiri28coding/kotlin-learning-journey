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
}
//----------------------------------------------------------------------------------//
//                      Aggregate Operations

//Kotlin provides a variety of aggregate operations that process collections to produce a single result based on their elements. These operations are essential for tasks like summing numbers, finding minimum or maximum values, and accumulating results through custom logic.
//
//Common Aggregate Functions:
//
//minOrNull() and maxOrNull(): Return the smallest and largest element in a collection, respectively. If the collection is empty, they return null.
private fun eg1() {
    val numbers = listOf(6, 42, 10, 4)
    println(numbers.minOrNull()) // Outputs: 4
    println(numbers.maxOrNull()) // Outputs: 42
}
//average(): Calculates the average of numeric elements in the collection.
private fun eg2() {
    val numbers = listOf(6, 42, 10, 4)
    println(numbers.average()) // Outputs: 15.5
}
//sum(): Computes the sum of numeric elements.
private fun eg3() {
    val numbers = listOf(6, 42, 10, 4)
    println(numbers.sum()) // Outputs: 62
}
//count(): Returns the number of elements in the collection.
private fun eg4() {
    val numbers = listOf(6, 42, 10, 4)
    println(numbers.count()) // Outputs: 4
}
//Functions with Selectors and Comparators:
//
//minByOrNull() and maxByOrNull(): Return the element with the smallest or largest value based on a selector function.
private fun eg5() {
    val numbers = listOf(5, 42, 10, 4)
    val minByRemainder = numbers.minByOrNull { it % 3 }
    println(minByRemainder) // Outputs: 3
}
//minWithOrNull() and maxWithOrNull(): Return the smallest or largest element according to a provided Comparator.
private fun eg6() {
    val strings = listOf("one", "two", "three", "four")
    val longestString = strings.maxWithOrNull(compareBy { it.length })
    println(longestString) // Outputs: "three"
}
//minOfOrNull() and maxOfOrNull(): Return the smallest or largest value produced by a selector function applied to the elements.
private fun eg7() {
    val numbers = listOf(5, 42, 10, 4)
    val maxSquare = numbers.maxOfOrNull { it * it }
    println(maxSquare) // Outputs: 1764
}
//Summation with Selectors:
//
//sumOf(): Calculates the sum of values returned by a selector function applied to each element.
private fun eg8() {
    val numbers = listOf(5, 42, 10, 4)
    println(numbers.sumOf { it * 2 }) // Outputs: 122
}
//Folding and Reducing:
//
//reduce(): Accumulates value starting with the first element and applying an operation sequentially to the current accumulator and each element.
private fun eg9() {
    val numbers = listOf(5, 2, 10, 4)
    val sum = numbers.reduce { acc, element -> acc + element }
    println(sum) // Outputs: 21
}
//fold(): Similar to reduce(), but starts with an initial value provided as an argument.
private fun eg10() {
    val numbers = listOf(5, 2, 10, 4)
    val sumDoubled = numbers.fold(0) { acc, element -> acc + element * 2 }
    println(sumDoubled) // Outputs: 42
}
//reduceRight() and foldRight(): Work like reduce() and fold(), but process elements from right to left.
private fun eg11() {
    val numbers = listOf(5, 2, 10, 4)
    val sumDoubledRight = numbers.foldRight(0) { element, acc -> acc + element * 2 }
    println(sumDoubledRight) // Outputs: 42
}
//Indexed Variants: Functions like reduceIndexed(), foldIndexed(), reduceRightIndexed(), and foldRightIndexed() provide element indices to the operation, allowing more complex accumulation logic.
//These aggregate operations are powerful tools for processing collections in Kotlin, enabling concise and expressive code for common data manipulation tasks.
