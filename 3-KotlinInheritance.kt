fun main(){
    val derived1 = DerivedClass("Rohit", 21)
    val derived2 = DerivedClass("Rohan")


    val derived3 = DerivedClass1("Rohit", 21)
    val derived4 = DerivedClass1("Rohan")
}
//----------------------------------------------------------------------------------//
                        //Inheritance

//All classes in Kotlin have a common superclass, Any,
// which is the default superclass for a class with no supertypes declared

//class PrivateSetterExample // Implicitly inherits from Any

//By default, Kotlin classes are final – they can't be inherited.
// To make a class inheritable, mark it with the open keyword

//open class Base // Class is open for inheritance


open class Base(p: Int)

class Derived(p: Int) : Base(p)


//If the derived class has a primary constructor,
// the base class can (and must) be initialized in that primary constructor
// according to its parameters.


open class BaseClass(name: String) {
    init {
        println("BaseClass initialized with name: $name")
    }
}

// Using delegation
class DerivedClass(name: String, age: Int) : BaseClass(name) {
    init {
        println("DerivedClass initialized with age: $age")
    }

    constructor(name: String) : this(name, 0) // Delegation to another constructor
}

// Using super keyword

//If the derived class has no primary constructor, then each secondary constructor
// has to initialize the base type using the super keyword or it has to delegate
// to another constructor which does.


class DerivedClass1: BaseClass{

    constructor(name: String, age: Int): super(name)

    constructor(name: String) : super(name)

}

// Overriding methods

// Kotlin requires explicit modifiers for overridable members and overrides:

open class Shape {
    open fun draw() { /*...*/ }
    fun fill() { /*...*/ }
}

class Circle() : Shape() {
    override fun draw() { /*...*/ }
}

// A member marked override is itself open, so it may be overridden in subclasses.
// If you want to prohibit re-overriding, use final:

open class Rhombus() : Shape() {
    override fun draw() { /*...*/ }
    val borderColor: String get() = "black"
}

// Overriding properties

//open class Shape {
//    open val vertexCount: Int = 0
//}
//
//class Rectangle : Shape() {
//    override val vertexCount = 4
//}


//when you override a val property with a var property in a derived class,
// you're essentially adding a setter method where previously there was only a
// getter method. This is allowed because val properties declare only a getter method,
// while var properties declare both getter and setter methods. Therefore, overriding
// a val with a var is considered widening the interface, which is allowed in
// Kotlin's inheritance model. This enables you to provide more flexibility in the
// derived class while still adhering to the contract defined by the base class.


// Calling the superclass implementation

class FilledRhombus: Rhombus() {
    override fun draw() {
        val filler = Filler()
        filler.drawAndFill()
    }

    inner class Filler {
        fun fill() { println("Filling") }
        fun drawAndFill() {
            super@FilledRhombus.draw() // Calls Rectangle's implementation of draw()
            fill()
            println("Drawn a filled rectangle with color ${super@FilledRhombus.borderColor}") // Uses Rectangle's implementation of borderColor's get()
        }
    }
}

// Overriding rules


//open class Rectangle {
//    open fun draw() { /* ... */ }
//}
//
//interface Polygon {
//    fun draw() { /* ... */ } // interface members are 'open' by default
//}
//
//class Square() : Rectangle(), Polygon {
//    // The compiler requires draw() to be overridden:
//    override fun draw() {
//        super<Rectangle>.draw() // call to Rectangle.draw()
//        super<Polygon>.draw() // call to Polygon.draw()
//    }
//}
