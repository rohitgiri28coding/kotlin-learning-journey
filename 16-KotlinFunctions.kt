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

//----------------------------------------------------------------------------------//
//                  Functions


//Infix Notation

//They must be member functions or extension functions.
//They must have a single parameter.
//The parameter must not accept variable number of arguments and must have no default value.

//Infix function calls have lower precedence than arithmetic operators, type casts, and the rangeTo operator.
//On the other hand, an infix function call's precedence is higher than that of the boolean operators && and ||, is - and in-checks, and some other operators

infix fun Int.shl(x: Int): Int { return 20 }

private fun eg1() {
// calling the function using the infix notation
    1 shl 2

// is the same as
    1.shl(2)
}


//inline functions

//The inline modifier affects both the function itself and the lambdas passed to it:
// all of those will be inlined into the call site.
//Inlining may cause the generated code to grow. However, if you do it in a reasonable
// way (avoiding inlining large functions), it will pay off in performance,
// especially at "megamorphic" call-sites inside loops.

inline fun inlineTest(xyz: ()-> Unit){
    println("Inside inline")
    xyz.invoke()
}
private fun eg2(){
    inlineTest {
        println("Lambda fn")
    }
}
//the above code will be compiled as:
/*
fun inlineTest(xyz: ()-> Unit){
    println("Inside inline")
    println("Lambda fn")
}
*/

//noinline
//If you don't want all of the lambdas passed to an inline function to be inlined,
// mark some of your function parameters with the noinline modifier:
inline fun foo(inlined: () -> Unit, noinline notInlined: () -> Unit) {  }

//Non-Local Jumps and Returns

//Inside a lambda, a bare return is forbidden because it cannot exit the enclosing function.

//fun foo() {
//    ordinaryFunction {
//        return // ERROR: Cannot make `foo` return here
//    }
//}

//When a function is marked as inline, its lambda body is inlined at the call site.
//In this case, a return inside the lambda can exit the enclosing function directly.

//fun foo() {
//    inlined {
//        return // OK: Lambda is inlined
//    }
//}

//crossinline

//If an inline function's lambda may be executed in a different context (e.g., from a local object or a nested function), non-local returns are not allowed.
//To enforce this, mark the lambda parameter with the crossinline modifier.

inline fun f(crossinline body: () -> Unit) {
    val runnable = object : Runnable {
        override fun run() = body() // Cannot use non-local return here
    }
    // ...
}

// Reified type parameters

// Reified type parameters in Kotlin allow you to access type information at runtime
// for generic functions, making it easier to work with types without resorting to
// explicit reflection.

inline fun <reified T> isOfType(obj: Any): Boolean {
    return obj is T
}

private fun eg3() {
    val number = 42
    val text = "Hello"

    println(isOfType<Int>(number)) // true
    println(isOfType<String>(number)) // false
    println(isOfType<String>(text)) // true
}
//The type T is reified, so the type information is available at runtime.

//Lambda Expressions

//A lambda is a function without a name that is used as an expression.
// It is written inside curly braces {} and has the following syntax:

//val sum = { x: Int, y: Int -> x + y }
//println(sum(3, 4)) // Output: 7


// Higher-order functions

//A higher-order function is a function that takes functions as parameters, or
// returns a function.


private fun operateOnNumbers(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    return operation(a, b)
}

private fun eg4() {
    val result = operateOnNumbers(3, 4) { x, y -> x + y }
    println(result) // Output: 7
}


//Function As Types

//Kotlin allows functions to be represented as types. For example:
//
//(Int, Int) -> Int represents a function that takes two Int arguments and returns an Int.

//val multiply: (Int, Int) -> Int = { x, y -> x * y }
//println(multiply(3, 4)) // Output: 12

//Lambda with No Parameters

//val sayHello = { "Hello, World!" }
//println(sayHello()) // Output: Hello, World!

//Anonymous Functions

//An anonymous function is a function without a name, similar to a lambda,
// but allows explicit return types and multiple return points.

private fun eg5() {
    val sum = fun(a: Int, b: Int): Int {
        return a + b
    }

    println(sum(3, 4)) // Output: 7
}

//Using Lambdas in Collections

private fun eg6(){
    val numbers = listOf(1, 2, 3, 4, 5)

// Using map to double each number
    val doubled = numbers.map { it * 2 }
    println(doubled) // Output: [2, 4, 6, 8, 10]

// Using filter to find even numbers
    val evens = numbers.filter { it % 2 == 0 }
    println(evens) // Output: [2, 4]

}

//Member References

//Instead of using a lambda, you can use a function reference (shortcut to call an existing function).

fun isEven(num: Int) = num % 2 == 0

private fun eg7() {
    val numbers = listOf(1, 2, 3, 4)
    val evens = numbers.filter(::isEven)
    println(evens) // Output: [2, 4]
}

//Callable References

//Function References:
//Syntax: ::functionName
//Refers to a top-level, member, or extension function.

//fun isEven(number: Int) = number % 2 == 0
//val ref = ::isEven
//println(ref(4)) // Output: true

// Receiver

//A receiver is the context object on which a function or property is invoked.
// It’s commonly used in extension functions and lambdas with receivers.

//An extension function adds functionality to an existing class without modifying its code.
//Syntax: <receiver type>.functionName.

private fun String.lastChar(): Char = this[this.length - 1]

private fun eg8() {
    println("Kotlin".lastChar()) // Output: n
}

