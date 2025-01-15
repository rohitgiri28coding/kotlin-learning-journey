fun main(){
    eg1()
    eg2()
    eg3()
    eg4()
    eg5()
    eg6()
}
// -------------------------------------------------------------------------//
//                  Grouping

//Kotlin provides a powerful way to group elements of a collection based on a
// specified criterion using the groupBy and groupingBy functions. These methods
// simplify the creation of group-based data structures and enhance data organization
// and analysis.

//groupBy Function

private val words = listOf("one", "two", "three", "four")

//Basic Grouping
//Group strings by their length.

private fun eg1(){
    val byLength = words.groupBy { it.length }
    println(byLength)
// Output: {3=[one, two], 5=[three], 4=[four]}
}

private val numbers = listOf(1, 2, 3, 4, 5, 6)

//Custom Grouping
//Group numbers by whether they are even or odd.

private fun eg2(){
    val byParity = numbers.groupBy { if (it % 2 == 0) "even" else "odd" }
    println(byParity)
// Output: {odd=[1, 3, 5], even=[2, 4, 6]}
}

//Changing Value Type
//Transform grouped elements into a different type.

private fun eg3(){
    val byLengthTransformed = words.groupBy(
        keySelector = { it.length },
        valueTransform = { it.uppercase() }
    )
    println(byLengthTransformed)
// Output: {3=[ONE, TWO], 5=[THREE], 4=[FOUR]}
}

//groupingBy Function

//Counting Elements
//Count occurrences of each character in a string.

private fun eg4(){
    val chars = "abbccc"
    val charCount = chars.groupingBy { it }.eachCount()
    println(charCount)
// Output: {a=1, b=2, c=3}
}

//Custom Aggregations
//Sum numbers grouped by their parity.

private fun eg5(){
    val numbers = listOf(1, 2, 3, 4, 5, 6)
    val paritySums = numbers.groupingBy { if (it % 2 == 0) "even" else "odd" }
        .fold(0) { acc, elem -> acc + elem }
    println(paritySums)
// Output: {odd=9, even=12}
}

//Advanced Grouping with Reduce
//Retain the largest number in each parity group.

private fun eg6(){
    val largestByParity = numbers.groupingBy { if (it % 2 == 0) "even" else "odd" }
        .reduce { _, acc, elem -> maxOf(acc, elem) }
    println(largestByParity)
// Output: {odd=5, even=6}
}

