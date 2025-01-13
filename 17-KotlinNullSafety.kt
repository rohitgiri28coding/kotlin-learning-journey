fun main()
{
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
//----------------------------------------------------------------------------------//
                            //Null Safety

//Null safety is a Kotlin feature designed to significantly reduce the risk of null references, also known as The Billion-Dollar Mistake.

//The only possible causes of an NullPointerException (NPE) in Kotlin are:
// 1) An explicit call to throw NullPointerException().
// 2) Usage of the not-null assertion operator !!.
// 3) Data inconsistency during initialization, such as when:
//       i) An uninitialized this available in a constructor is used somewhere
//          else (a "leaking this ").
//      ii) A superclass constructor calling an open member whose implementation
//          in the derived class uses an uninitialized state.


// Check for null with the if conditional

private fun eg1() {
    // Assigns null to a nullable variable
    val b: String? = null
    // Checks for nullability first and then accesses length
    val l = if (b != null) b.length else -1
    println(l)
    // -1
}


//Safe call operator

private fun eg2(){
    // Assigns a nullable string to a variable
    val a: String? = "Kotlin"
    // Assigns null to a nullable variable
    val b: String? = null

    // Checks for nullability and returns length or null
    println(a?.length)
    // 6
    println(b?.length)
    // null

//    bob?.department?.head?.name  //Safe calls are useful in chains
//    This chain returns null if any of its properties are null.
}

//Elvis operator

private fun eg3() {
    // Assigns null to a nullable variable
    val b: String? = null
    // Checks for nullability. If not null, returns length. If null, returns a non-null value
    val l = b?.length ?: 0
    println(l)
    // 0
}

//Not-null assertion operator

private fun eg4(){
    // Assigns a nullable string to a variable
    val b: String? = "Kotlin"
    // Treats b as non-null and accesses its length
    val l = b!!.length
    println(l)
    // 6
}

//Nullable receiver

private fun eg5() {
    // Assigns null to a nullable Person object stored in the person variable
    val person: Person4? = null

    // Applies .toString to the nullable person variable and prints a string
    println(person.toString())
    // null
}

// Defines a simple Person class
data class Person4(val name: String)

// In the example above, even though person is null, the .toString() function safely returns the string "null". This can be helpful for debugging and logging.


//The ?. operator allows you to safely handle potential null values while still accessing properties or functions of objects that might be null.

private fun eg6(){
    // Assigns a nullable Person object to a variable
    val person1: Person4? = null
    val person2: Person4? = Person4("Rohit")

    // Prints "null" if person is null; otherwise prints the result of person.toString()
    println(person1?.toString())
    // null
    println(person2?.toString())
    // Person(name=Rohit)
}

//Let function

//This combination is useful for evaluating an expression, check the result for null, and execute code only if it's not null, avoiding manual null checks:

private fun eg7(){
    // Declares a list of nullable strings
    val listWithNulls: List<String?> = listOf("Kotlin", null)

// Iterates over each item in the list
    for (item in listWithNulls) {
        // Checks if the item is null and only prints non-null values
        item?.let { println(it) }
        //Kotlin
    }
}


//Safe casts

private fun eg8(){
    // Declares a variable of type Any, which can hold any type of value
    val a: Any = "Hello, Kotlin!"

// Safe casts to Int using the 'as?' operator
    val aInt: Int? = a as? Int
// Safe casts to String using the 'as?' operator
    val aString: String? = a as? String

    println(aInt)
// null
    println(aString)
// "Hello, Kotlin!"
}


//Collections of a nullable type

private fun eg9(){
    // Declares a list containing some null and non-null integer values
    val nullableList: List<Int?> = listOf(1, 2, null, 4)

// Filters out null values, resulting in a list of non-null integers
    val intList: List<Int> = nullableList.filterNotNull()

    println(intList)
// [1, 2, 4]
}
