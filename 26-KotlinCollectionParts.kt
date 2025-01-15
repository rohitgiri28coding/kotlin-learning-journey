fun main(){
    eg1()
    eg2()
    eg3()
    eg4()
    eg5()
    eg6()
    eg7()
    eg8()
}
// -------------------------------------------------------------------------------//
//                  Collection Parts in Kotlin

//windowed Function
//Key Features
//
//Creates a sequence or list of windows from the original collection.
//Each window is a sub-list of the specified size.
//Supports custom step sizes and allows for partial windows.
//Parameters
//
//size: Size of each window.
//step (optional): Distance between the first elements of consecutive windows (default: 1).
//partialWindows (optional): Whether to include smaller windows at the end of the collection (default: false).
//Return Type
//
//Returns a List if called on a collection or array.
//Returns a Sequence if called on a sequence.
private val numbers = listOf(1, 2, 3, 4, 5)

//Divide a collection into windows of size 3
private fun eg1(){
    val result = numbers.windowed(size = 3)
    println(result)
// Output: [[1, 2, 3], [2, 3, 4], [3, 4, 5]]
}

//Use a step size of 2.
private fun eg2(){
    val result = numbers.windowed(size = 3, step = 2)
    println(result)
// Output: [[1, 2, 3], [3, 4, 5]]
}

//Include incomplete windows at the end.
private fun eg3(){
    val result = numbers.windowed(size = 3, step = 2, partialWindows = true)
    println(result)
// Output: [[1, 2, 3], [3, 4, 5], [5]]
}

//Transform each window during creation.
private fun eg4(){
    val sums = numbers.windowed(size = 3, step = 1) { it.sum() }
    println(sums)
// Output: [6, 9, 12]
}

//Operate on a sequence to avoid materializing the entire collection.
private fun eg5(){
    val sequence = sequenceOf(1, 2, 3, 4, 5)
    val result = sequence.windowed(size = 3)
    println(result.toList())
// Output: [[1, 2, 3], [2, 3, 4], [3, 4, 5]]
}


//Chunking

//chunked(size: Int)
//
//Splits the collection into chunks of the specified size and returns a list of lists.
//If the collection size is not a multiple of the chunk size, the last chunk will contain the remaining elements.

//chunked(size: Int, transform: (List<T>) -> R)
//
//Applies a transformation to each chunk and returns the results as a list

private fun eg6(){
    val numbers = (1..10).toList()
    println(numbers.chunked(3))
// Output: [[1, 2, 3], [4, 5, 6], [7, 8, 9], [10]]
    println(numbers.chunked(3) { it.sum() })
// Output: [6, 15, 24, 10]
}

//Splitting at Index

//take(n: Int)
//
//Returns the first n elements.

//takeLast(n: Int)
//
//Returns the last n elements.

//drop(n: Int)
//
//Returns the collection without the first n elements.

//dropLast(n: Int)
//
//Returns the collection without the last n elements.

private fun eg7()
{
    println(numbers.take(3))
// Output: [1, 2, 3]
    println(numbers.takeLast(3))
// Output: [8, 9, 10]
    println(numbers.drop(3))
// Output: [4, 5, 6, 7, 8, 9, 10]
    println(numbers.dropLast(3))
// Output: [1, 2, 3, 4, 5, 6, 7]
}

//slice(indices: IntRange)
//
//Extracts elements within the specified range.
//slice(indices: Iterable<Int>)
//
//Extracts elements at the specified indices.

private fun eg8(){
    val numbers = listOf(1,2,3,4,5,6,7,8)
    println(numbers.slice(2..5))
// Output: [3, 4, 5, 6]
    println(numbers.slice(listOf(1, 3, 5)))
// Output: [2, 4, 6]
}