//----------------------------------------------------------------------------------//
                            //Classes

// constructor: A class in Kotlin has a primary constructor and possibly one or more secondary constructors.

// primary constructor
class Person constructor(firstName: String) { /*...*/ }

class Person1(firstName: String) { /*...*/ }


//Secondary constructors
class Person2(val name: String) {
    val children: MutableList<Person2> = mutableListOf()
    constructor(name: String, parent: Person2) : this(name) {
        parent.children.add(this)
    }
}

//abstract class
abstract class Polygon {
    abstract fun draw()
}

class RectangleShape : Polygon() {
    override fun draw() {
        // draw the rectangle
    }
}

//You can override a non-abstract open member with an abstract one.

open class Polygon1 {
    open fun draw() {
        // some default polygon drawing method
    }
}

abstract class WildShape : Polygon1() {
    // Classes that inherit WildShape need to provide their own
    // draw method instead of using the default on Polygon
    abstract override fun draw()
}

