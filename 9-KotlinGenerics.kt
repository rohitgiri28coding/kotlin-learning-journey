//------------------------------------------------------------------------------------//
                        // Generics

//Classes in Kotlin can have type parameters, just like in Java:

class Box<T>(t: T) {
    var value = t
}

//Generic functions

fun <T> singletonList(item: T): List<T> {
    return listOf(item)
}

fun <T> T.basicToString(): String { // extension function
    return ""
}

// study deeply in future