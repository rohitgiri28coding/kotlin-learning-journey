import kotlin.math.pow

fun main(){
    val xyz = "rohit giri said hello".capitalizeFirstChar()
    println(xyz)
    println("djr".replaceFirstChar { it.uppercase() })
    eg1()
    eg2()
}
//------------------------------------------------------------------------------------//
                            //Extensions

//Kotlin provides the ability to extend a class or an interface with new functionality
// without having to inherit from the class or use design patterns such as Decorator.
// This is done via special declarations called extensions.


//For example, you can write new functions for a class or an interface from a
// third-party library that you can't modify. Such functions can be called in the
// usual way, as if they were methods of the original class. This mechanism is
// called an extension function. There are also extension properties that
// let you define new properties for existing classes.

//Extension function

//To declare an extension function, prefix its name with a receiver type,
// which refers to the type being extended.
// Extension functions can be overloaded

fun String.capitalizeFirstChar(): String{
    var str = ""
    this.split(" ").forEach {value->
        str += "${value.replaceFirstChar {
            it.uppercaseChar()
        }} "

    }
    return str
}
fun String.capitalizeFirstChar(num: Int): String{
    var str = ""
    this.split(" ").forEach {value->
        str += "${value.replaceFirstChar {
            it.uppercaseChar()
        }} "

    }
    return str
}

//Extensions are resolved statically

//Extensions do not actually modify the classes they extend.
// By defining an extension, you are not inserting new members into a class,
// only making new functions callable with the dot-notation on variables of this type.

//Extension functions are dispatched statically.
// So which extension function is called is already known at compile time
// based on the receiver type.

//Nullable receiver in extension function

//These extensions can be called on an object variable even if its value is null.
// If the receiver is null, then this is also null.
// Check this == null inside the function body to avoid compiler errors.
// eg toString() method can be called to any type.


//Extension properties

//Since extensions do not actually insert members into classes, there's no
// efficient way for an extension property to have a backing field.
// This is why initializers are not allowed for extension properties.
// Their behavior can only be defined by explicitly providing getters/setters.

val <T> List<T>.lastIndex: Int
    get() = size - 1



//Declaring extensions as members

//Normally, extensions are declared outside a class, but Kotlin also allows
// declaring extensions as members inside a class or object. When an extension is
// declared as a member, its dispatch receiver is the class it is defined in, and
// its extension receiver is the type being extended.

class Calculator(private val multiplier: Int) {
    private fun Int.powerAndMultiply(exp: Int): Int {
        return this.toDouble().pow(exp).toInt() * multiplier
    }

    fun demonstrate() {
        val result = 2.powerAndMultiply(3) // 2^3 * multiplier
        println("Result: $result")
    }
}

private fun eg1() {
    val calculator = Calculator(10)
    calculator.demonstrate() // Output: Result: 80 (2^3 * 10)
}

//Companion object extensions
//If a class has a companion object defined, you can also define
// extension functions and properties for the companion object.
// Just like regular members of the companion object, they can be called
// using only the class name as the qualifier:
//
class MyClass {
    companion object {}  // will be called "Companion"
}

fun MyClass.Companion.printCompanion() { println("companion") }

private fun eg2() {
    MyClass.printCompanion()
}
