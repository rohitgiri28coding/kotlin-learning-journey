//------------------------------------------------------------------------------------//
                        // Enums

//The most basic use case for enum classes is the implementation of type-safe enums:

enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

//Each enum constant is an object. Enum constants are separated by commas.
//Since each enum is an instance of the enum class, it can be initialized as:

enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

//Anonymous classes

//Enum constants can declare their own anonymous classes with their
// corresponding methods, as well as with overriding base methods.

enum class ProtocolState {
    WAITING {
        override fun signal() = TALKING
    },

    TALKING {
        override fun signal() = WAITING
    };

    abstract fun signal(): ProtocolState
}

//Working with enum constants

enum class RGB { RED, GREEN, BLUE }

fun main() {
    for (color in RGB.entries) println(color.toString()) // prints RED, GREEN, BLUE
    println("The first color is: ${RGB.valueOf("RED")}") // prints "The first color is: RED"
}
//The valueOf() method throws an IllegalArgumentException if the specified name
// does not match any of the enum constants defined in the class.

// From Kotlin 2.0.0The enumEntries<T>() function returns a list of all
// enum entries for the given enum type T.
