fun main(){
    Eg1()

    Eg2()

    Eg3()
}

//----------------------------------------------------------------------------------//

                            //Interface
//A class or object can implement one or more interfaces

interface Naming{
    val name: String
    var petName: String
}
interface Animal: Naming{
    fun eat()
    fun sleep()
}
interface Human: Animal{
    val firstName: String
    val lastName: String

    override val name: String
        get() = "$firstName $lastName"

    override fun eat() {
        println("Human eating")
    }

    override fun sleep() {
        println("")
    }

}
data class Employee(
    // implementing 'name' is not required
    override val firstName: String,
    override val lastName: String,
    override var petName: String,
    val position: String,
) : Human

// Resolving overriding conflicts
interface A {
    fun foo() { print("A") }
    fun bar()
}

interface B {
    fun foo() { print("B") }
    fun bar() { print("bar") }
}

class C : A {
    override fun bar() { print("bar") }
}

class D : A, B {
    override fun foo() {
        super<A>.foo()
        super<B>.foo()
    }

    override fun bar() {
        super<B>.bar()
    }
}

//Functional (SAM) interfaces

//An interface with only one abstract member function
// is called a functional interface, or a Single Abstract Method (SAM) interface.
// The functional interface can have several non-abstract member functions but
// only one abstract member function.

fun interface IntPredicate {
    fun accept(i: Int): Boolean
}

//Without SAM

//// Creating an instance of a class
//val isEven = object : IntPredicate {
//   override fun accept(i: Int): Boolean {
//       return i % 2 == 0
//   }
//}

// Using SAM
// Creating an instance using lambda
val isEven = IntPredicate { it % 2 == 0 }

private fun Eg1(){
    println(isEven)
}

//functional interfaces vs type aliases

fun interface StringProcessor {
    fun process(input: String): String
}

fun applyProcessor(processor: StringProcessor, input: String): String {
    return processor.process(input)
}
private fun Eg2(){
    // Usage
    val result = applyProcessor({ it.uppercase() }, "hello")
    println(result)  // Output: HELLO

}
typealias StringTransformer = (String) -> String

fun applyTransformer(transformer: StringTransformer, input: String): String {
    return transformer(input)
}



// Migration from an interface with constructor function to a functional interface

fun interface Printer {
    fun print()
}

// Legacy Constructor Function (hidden)
@Deprecated(
    message = "Use functional interface Printer instead",
    level = DeprecationLevel.HIDDEN
)
fun Printer(block: () -> Unit): Printer = object : Printer {
    override fun print() = block()
}

private fun Eg3(){
    // Usage
    val printer = Printer { println("Printing with functional interface") }
    printer.print()

    //Callable references (::Printer) will now work with the functional interface.

    //documentsStorage.addPrinter(::Printer)

    Printer { println("Printing with interface with constructor function") }
}
