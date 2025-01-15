fun main(){
    eg1()
    eg2()
    eg3()
    eg4()
}
//------------------------------------------------------------------------------------//
//                       Sequences

//Sequences (Sequence<T>) in Kotlin are containers that produce elements on-the-fly
// during iteration, rather than storing them in memory like collections.
//Lazy Evaluation: Operations on sequences are executed lazily, meaning computations
// occur only when the final result is requested. This contrasts with
// collections (Iterable), where each processing step is executed eagerly,
// potentially creating intermediate collections.

//Creating Sequences
// 1) From Elements:

//Use sequenceOf() to create a sequence from a predefined set of elements.
private fun eg1(){
    val numbersSequence = sequenceOf("four", "three", "two", "one")
    println(numbersSequence)
}

// 2) From an Iterable:

//Convert existing collections like lists or sets into sequences using asSequence().
private fun eg2(){
    val numbers = listOf("one", "two", "three", "four")
    val numbersSequence = numbers.asSequence()
    println(numbersSequence)
}

// 3) From a Function:

//Generate sequences based on a function with generateSequence().
// The sequence continues until the function returns null.
private fun eg3(){
    val oddNumbers = generateSequence(1) { it + 2 }
    println(oddNumbers)
}

// 4) From Chunks:

//Use the sequence() function along with yield() and yieldAll() to produce elements one by one or in chunks.

private fun eg4(){
    val oddNumbers = sequence {
        yield(1)
        yieldAll(listOf(3, 5))
        yieldAll(generateSequence(7) { it + 2 })
    }
    println(oddNumbers)
}

//Sequence Operations

//Stateless Operations:
//Process each element independently without maintaining state. Examples include map() and filter().

//Stateful Operations:
//Require significant state, often proportional to the number of elements. Examples include distinct() and sorted().

//Intermediate vs. Terminal Operations:
//Intermediate operations return another sequence and are lazy (map, filter).
//Terminal operations produce a result or side-effect and trigger the processing (toList(), sum()).

//Performance Considerations

//Advantages:
//Avoids creating intermediate collections, enhancing performance for complex transformations.

//Overhead:
//The lazy nature introduces overhead, which may be significant for small collections or simple computations.

