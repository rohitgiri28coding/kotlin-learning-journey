//------------------------------------------------------------------------------------//
                        // Nested class

private class Outer1 {
    private val bar: Int = 1
    class Nested {
        fun foo() = 2
        fun f(){
//            println(bar)          //Error: Can't access member of its outer class
        }
    }
}

interface OuterInterface {
    class InnerClass
    interface InnerInterface
}

class OuterClass {
    class InnerClass
    interface InnerInterface
}

                        //Inner classes

class Outer2 {
    private val bar: Int = 1
    inner class Inner {
        fun foo() = bar                    //can access member of its outer class
    }
}

