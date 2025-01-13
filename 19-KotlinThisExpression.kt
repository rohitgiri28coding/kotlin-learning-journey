fun main(){
    eg1()
}
//----------------------------------------------------------------------------------//
//                  This Expression


// To denote the current receiver, you use this expressions:

// 1) In a member of a class, this refers to the current object of that class.
// 2) In an extension function or a function literal with receiver this denotes the receiver parameter that is passed on the left-hand side of a dot.


// If this has no qualifiers, it refers to the innermost enclosing scope.
// To refer to this in other scopes, label qualifiers are used:

//                  Qualified this

// To access this from an outer scope (a class, extension function, or labeled function
// literal with receiver) you write this@label, where @label is a label on the scope
// this is meant to be from:

class A2 { // implicit label @A2
    inner class B { // implicit label @B
        fun Int.foo() { // implicit label @foo
            val a = this@A2 // A2's this
            val b = this@B // B's this

            val c = this // foo()'s receiver, an Int
            val c1 = this@foo // foo()'s receiver, an Int

            val funLit = lambda@ fun String.() {
                val d = this // funLit's receiver, a String
            }

            val funLit2 = { s: String ->
                // foo()'s receiver, since enclosing lambda expression
                // doesn't have any receiver
                val d1 = this
            }
        }
    }
}


//Implicit this

//When you call a member function on this, you can skip the this. part.
// If you have a non-member function with the same name, use this with
// caution because in some cases it can be called instead:
//
private fun eg1() {
    fun printLine() { println("Local function") }

    class A {
        fun printLine() { println("Member function") }

        fun invokePrintLine(omitThis: Boolean = false)  {
            if (omitThis) printLine()
            else this.printLine()
        }
    }

    A().invokePrintLine() // Member function
    A().invokePrintLine(omitThis = true) // Local function
}