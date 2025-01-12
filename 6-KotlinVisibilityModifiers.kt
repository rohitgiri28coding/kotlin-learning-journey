//------------------------------------------------------------------------------------//
                        // Visibility Modifier

//There are four visibility modifiers in Kotlin: private, protected, internal, and public.
// The default visibility is public.

//private means that the member is visible inside this class only (including all its members).
//protected means that the member has the same visibility as one marked as private, but that it is also visible in subclasses.
//internal means that any client inside this module who sees the declaring class sees its internal members.
//public means that any client who sees the declaring class sees its public members.

//In Kotlin, an outer class does not see private members of its inner classes.

private open class Outer {
    private val a = 1
    protected open val b = 2
    internal open val c = 3
    val d = 4  // public by default

    protected class Nested {
        public val e: Int = 5
    }
}

private class Subclass : Outer() {
    // a is not visible
    // b, c and d are visible
    // Nested and e are visible

    override val b = 5   // 'b' is protected
    override val c = 7   // 'c' is internal
}

private class Unrelated(o: Outer) {
    // o.a, o.b are not visible
    // o.c and o.d are visible (same module)
    // Outer.Nested is not visible, and Nested::e is not visible either
}

//Constructor

//By default, all constructors are public