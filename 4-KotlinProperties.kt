fun main() {
    val privateSetterExample = PrivateSetterExample()
    println(privateSetterExample.setterVisibility) // Allowed: public getter
    //example.setterVisibility = "new value" // Error: private setter
}

//----------------------------------------------------------------------------------//
                                // Properties

//Getters and setters
class PrivateSetterExample {
    var setterVisibility: String = "abc"
        private set
/*
----------------Study deeply-------------------
    var setterWithAnnotation: Any? = null
        @Inject set // annotate the setter with Inject
*/
}

//Backing fields

//In Kotlin, a field is only used as a part of a property to hold its value in memory. Fields cannot be declared directly. However, when a property needs a backing field, Kotlin provides it automatically. This backing field can be referenced in the accessors using the field identifier:
//var counter = 0 // the initializer assigns the backing field directly
//    set(value) {
//        if (value >= 0)
//            field = value
//            // counter = value // ERROR StackOverflow: Using actual name 'counter' would make setter recursive
//    }
//The field identifier can only be used in the accessors of the property.
//A backing field will be generated for a property if it uses the default implementation of at least one of the accessors, or if a custom accessor references it through the field identifier.
//For example, there would be no backing field in the following case:
//val isEmpty: Boolean
//    get() = this.size == 0


//Compile-time constants

//If the value of a read-only property is known at compile time,
// mark it as a compile time constant using the const modifier.

const val SUBSYSTEM_DEPRECATED: String = "This subsystem is deprecated"

// Late-initialized properties and variables

//public class MyTest {
//    lateinit var subject: TestSubject
//
//    @SetUp fun setup() {
//        subject = TestSubject()
//    }
//
//    @Test fun test() {
//        subject.method()  // dereference directly
//    }
//}

// Checking whether a lateinit var is initialized

//if (foo::bar.isInitialized) {
//    println(foo.bar)
//}