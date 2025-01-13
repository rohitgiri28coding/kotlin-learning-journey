//----------------------------------------------------------------------------------//
//                                  Equality

//In Kotlin, there are two types of equality:

// 1) Structural equality (==) - a check for the equals() function
// 2) Referential equality (===) - a check for two references pointing to the same object

//Structural equality

//Structural equality verifies if two objects have the same content or structure.
// Structural equality is checked by the == operation and its negated counterpart !=.

private fun eg1() {

    val a = "hello"
    val b = "hello"
    val c = null
    val d = null
    val e = d

    println(a == b)
    // true
    println(a == c)
    // false
    println(c == e)
    // true
}

//Note that there's no point in optimizing your code when comparing to
// null explicitly: a == null will be automatically translated to a === null.

// By default, the equals() function implements referential equality.
// However, classes in Kotlin can override the equals() function to provide a
// custom equality logic and, in this way, implement structural equality.


//To provide a custom equals check implementation, override the equals(other: Any?): Boolean function:
private class Point(val x: Int, val y: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Point) return false

        // Compares properties for structural equality
        return this.x == other.x && this.y == other.y
    }
}


//Referential equality

//Referential equality verifies the memory addresses of two objects to determine if they are the same instance.

private fun eg2() {
    val a = "Hello"
    val b = a
    val c = "world"
    val d = "world"

    println(a === b)
    // true
    println(a === c)
    // false
    println(c === d)
    // true

}

//For values represented by primitive types at runtime (for example, Int), the === equality check is equivalent to the == check.

//Floating-point numbers equality

//When the operands of an equality check are statically known to be Float or Double (nullable or not), the check follows the IEEE 754 Standard for Floating-Point Arithmetic.

//In this scenario:
// 1) NaN is equal to itself
// 2) NaN is greater than any other element (including POSITIVE_INFINITY)
// 3) -0.0 is not equal to 0.0

private fun eg3() {
    // Operand statically typed as floating-point number
    println(Double.NaN == Double.NaN)                 // false
    // Operand NOT statically typed as floating-point number
    // So NaN is equal to itself
    println(listOf(Double.NaN) == listOf(Double.NaN)) // true

    // Operand statically typed as floating-point number
    println(0.0 == -0.0)                              // true
    // Operand NOT statically typed as floating-point number
    // So -0.0 is less than 0.0
    println(listOf(0.0) == listOf(-0.0))              // false

    println(listOf(Double.NaN, Double.POSITIVE_INFINITY, 0.0, -0.0).sorted())
    // [-0.0, 0.0, Infinity, NaN]
}