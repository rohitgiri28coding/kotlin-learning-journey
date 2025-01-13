//----------------------------------------------------------------------------------//
//                            Delegation

//Overriding a member of an interface implemented by delegation

private interface Base1 {
    fun printMessage()
    fun printMessageLine()
}

private class BaseImpl(val x: Int) : Base1 {
    override fun printMessage() { print(x) }
    override fun printMessageLine() { println(x) }
}

private class Derived1(b: Base1) : Base1 by b {
    override fun printMessage() { println("abc") }
}

private fun eg1() {
    val base = BaseImpl(10)
    Derived1(base).printMessage()       //Derived overrides printMessage() in its own implementation.
    Derived1(base).printMessageLine()   //printMessageLine() is not overridden in Derived, so it delegates to BaseImpl.
}

// Delegate properties --- to be studied in the future