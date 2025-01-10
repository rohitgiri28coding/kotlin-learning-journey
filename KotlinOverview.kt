fun main(){

// -------------------------------------------------------------------------//
                        // Print

    println("Hello Rohit Giri!")

// -------------------------------------------------------------------------//
                        //Variables

    //Read-only variables with val

    val name = "Rohit Giri"
    println(name)

    //Mutable variables with var

    var age = 20
    println("Age: $age")

    age = 21
    println("Age: $age")

// -------------------------------------------------------------------------//
                        //String templates

    //Template expressions always start with a dollar sign $.
    //To evaluate a piece of code in a template expression, place the code within curly braces {} after the dollar sign $.

    println("My name is $name and I am ${age-1} years old.")


    // Type inference

    var customers = 10

// -------------------------------------------------------------------------//

                    // Arithmetic operations

    // Some customers leave the queue
    customers = 8

    customers = customers + 3 // Example of addition: 11

    // +=, -=, *=, /=, and %= are augmented assignment operators.

    customers += 7            // Example of addition: 18
    customers -= 3            // Example of subtraction: 15
    customers *= 2            // Example of multiplication: 30
    customers /= 3            // Example of division: 10

    println(customers) // 10

// -------------------------------------------------------------------------//
                    // Data types
    // Integers: Byte, Short, Int, Long
    val year: Int = 2020

    //Unsigned integers: UByte, UShort, UInt, ULong
    val score: UInt = 3500u

    //Floating-point numbers: Float, Double
    val temp = 456.34f

    //Booleans: Boolean
    val isEnabled: Boolean = true

    //Characters: char
    val letter: Char = 'A'

    //Strings: String
    val message: String = "Hello Kotlin!"

// -------------------------------------------------------------------------//
                        //Collections

    //List
    //Lists store items in the order that they are added,
    // and allow for duplicate items.

    // Read only list

    val readOnlyShapes = listOf("triangle", "square", "circle")
    println(readOnlyShapes)
    // [triangle, square, circle]

    // Mutable list with explicit type declaration

    val shapes: MutableList<String> = mutableListOf("triangle", "square", "circle")
    println(shapes)
    // [triangle, square, circle]

    //To prevent unwanted modifications, you can create a read-only view of a mutable list by assigning it to a List:
    // This is called casting.

    val shapesLocked: List<String> = shapes

    /* .first() and .last() functions are examples of extension functions.
        To call an extension function on an object,
            write the function name after the object appended with a period .*/
    println("The first item in the list is: ${readOnlyShapes.first()}")

    println("This list has ${readOnlyShapes.count()} items")

    println("circle" in readOnlyShapes)


    shapes.add("pentagon")
    shapes.removeLast()


    // Set
    // Whereas lists are ordered and allow duplicate items,
    //      sets are unordered and only store unique items.

    // Read-only set
    val readOnlyFruit = setOf("apple", "banana", "cherry", "cherry")

    // Mutable set with explicit type declaration
    val fruit: MutableSet<String> = mutableSetOf("apple", "banana", "cherry", "cherry")

    println(readOnlyFruit)
    // [apple, banana, cherry]

    // As sets are unordered, you can't access an item at a particular index.

    //Map
    //Maps store items as key-value pairs.
    // You access the value by referencing the key.

    //Every key in a map must be unique so that Kotlin can understand which value you want to get.
    //You can have duplicate values in a map.

    // Read-only map

    val readOnlyJuiceMenu = mapOf("apple" to 100, "kiwi" to 190, "orange" to 100)
    println(readOnlyJuiceMenu)
    // {apple=100, kiwi=190, orange=100}

    // Mutable map with explicit type declaration

    val juiceMenu: MutableMap<String, Int> = mutableMapOf("apple" to 100, "kiwi" to 190, "orange" to 100)
    println(juiceMenu)
    // {apple=100, kiwi=190, orange=100}

    //keys and values are examples of properties of an object.
    //To access the property of an object, write the property name after the object appended with a period .

// -------------------------------------------------------------------------//

                    // Conditional expression

    //Kotlin provides if and when for checking conditional expressions.

    //If you have to choose between if and when, we recommend using when because it:
    //Makes your code easier to read.
    //
    //Makes it easier to add another branch.
    //
    //Leads to fewer mistakes in your code

    var d: Int
    val check = true

    if (check) {
        d = 1
    } else {
        d = 2
    }

    println(d)
    // 1

    d = if (check) 1 else 2

    println(d)
    // 1


    val obj = "Hello"

    // WHEN

    val res = when (obj) {
        // Checks whether obj equals to "1"
        "1" -> println("One")
        // Checks whether obj equals to "Hello"
        "Hello" -> println("Greeting")
        // Default statement
        else -> println("Unknown")
    }
    // Greeting

    //Note that all branch conditions are checked sequentially until one of them is satisfied.
    // So only the first suitable branch is executed.

    //An expression returns a value that can be used later in your code.
    println(res)

// -------------------------------------------------------------------------//
                            // LOOPS
    //The two most common loop structures in programming are for and while.
    // Use for to iterate over a range of values and perform an action.
    // Use while to continue an action until a particular condition is satisfied.

    // for loop
    val cakes = listOf("carrot", "cheese", "chocolate")

    for (cake in cakes) {
        println("Yummy, it's a $cake cake!")
    }

    // while loop
    //while can be used in two ways:
    //
    //To execute a code block while a conditional expression is true. (while)
    //
    //To execute the code block first and then check the conditional expression. (do-while)

    var cakesEaten = 0
    while (cakesEaten < 3) {
        println("Eat a cake")
        cakesEaten++
    }

}