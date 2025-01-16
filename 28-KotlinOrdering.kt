fun main(){
    eg1()
    eg2()
    eg3()
    eg4()
    eg5()
}
//----------------------------------------------------------------------------------//
//                          Ordering
//In Kotlin, the order of elements in collections is significant, especially for types
// like lists where element order determines equality. Kotlin provides several
// mechanisms to define and manipulate the ordering of collection elements.

//Natural Order:

//Types that implement the Comparable interface have a natural order. For instance,
// numeric types follow numerical order, and String and Char types follow lexicographical
// order. To define a natural order for a custom type, implement the Comparable
// interface and override the compareTo() function.

//Here's an example of a Version class implementing Comparable:


private class Version(private val major: Int, private val minor: Int) : Comparable<Version> {
    override fun compareTo(other: Version): Int = when {
        this.major != other.major -> this.major.compareTo(other.major)
        this.minor != other.minor -> this.minor.compareTo(other.minor)
        else -> 0
    }
}

private fun eg1() {
    println(Version(1, 2) > Version(1, 3)) // Outputs: false
    println(Version(2, 0) > Version(1, 5)) // Outputs: true
}

//Custom Order:

//For non-comparable objects or to define an order different from the natural one,
// you can create a Comparator. A Comparator defines a custom order by implementing
// the compare() function, which compares two instances and returns an integer
// indicating their order.

//For example, to sort strings by their length:

private fun eg2() {
    val lengthComparator = Comparator { str1: String, str2: String -> str1.length - str2.length }
    val sortedList = listOf("aaa", "bb", "c").sortedWith(lengthComparator)
    println(sortedList) // Outputs: [c, bb, aaa]
}


//Kotlin's standard library provides the compareBy() function, which simplifies
// creating comparators:

private fun eg3() {
    val sortedList = listOf("aaa", "bb", "c").sortedWith(compareBy { it.length })
    println(sortedList) // Outputs: [c, bb, aaa]
}

//To sort by multiple criteria, you can chain comparators using thenBy().
// For instance, to sort strings by length and then alphabetically:

private fun eg4() {
    val sortedStrings = listOf("aaa", "bb", "c", "b", "a", "aa", "ccc")
        .sortedWith(compareBy<String> { it.length }.thenBy { it })
    println(sortedStrings) // Outputs: [a, b, c, aa, bb, aaa, ccc]
}

//Sorting Functions:

//Kotlin provides several functions to sort collections:

//sorted(): Returns a list of collection elements sorted in ascending order.
//sortedDescending(): Returns a list of elements sorted in descending order.
//sortedBy(): Sorts elements based on a selector function.
//sortedByDescending(): Sorts elements based on a selector function in descending order.
//sortedWith(): Sorts elements using a specified comparator.
//These functions return new lists with the sorted elements, leaving the original collections unmodified.

//Reversing Order:

//To reverse the order of elements, use:
//reversed(): Returns a new list with elements in reverse order.
//asReversed(): Returns a reversed view of the original list. Changes to the original list reflect in the reversed view and vice versa.

//Random Order:
//To shuffle elements randomly, use the shuffled() function:

private fun eg5() {
    val numbers = listOf("one", "two", "three", "four")
    println(numbers.shuffled())
}

//This function returns a new list with elements in random order.

