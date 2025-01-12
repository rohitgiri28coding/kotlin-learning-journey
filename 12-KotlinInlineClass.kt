fun main(){
    eg1()
    eg2()
}

//------------------------------------------------------------------------------------//
                            //Inline Class

// wrap value in a class -> make more domain specific -> leads to runtime overhead (additional heap allocations)
// if wrap type is primitive -> performance hit is significant (because they are heavily optimized by the runtime, while their wrappers don't get any special treatment.

//To solve such issues, Kotlin introduces a special kind of class called an inline class.

@JvmInline
value class Password(private val s: String)

// No actual instantiation of class 'Password' happens
// At runtime 'securePassword' contains just 'String'
val securePassword = Password("Don't try this in production")

//Members

//Inline classes support some functionality of regular classes.
// In particular, they are allowed to declare properties and functions,
// have an init block and secondary constructors:

@JvmInline
value class Person3(private val fullName: String) {
    init {
        require(fullName.isNotEmpty()) {
            "Full name shouldn't be empty"
        }
    }

    constructor(firstName: String, lastName: String) : this("$firstName $lastName") {
        require(lastName.isNotBlank()) {
            "Last name shouldn't be empty"
        }
    }

    val length: Int
        get() = fullName.length

    fun greet() {
        println("Hello, $fullName")
    }
}

private fun eg1() {
    val name1 = Person3("Rohit", "Giri")
    val name2 = Person3("Krishna")
    name1.greet() // the `greet()` function is called as a static method
    println(name2.length) // property getter is called as a static method
}


//Inline class properties cannot have backing fields. They can only have
// simple computable properties (no lateinit /delegated properties).

//Inheritance

//Inline classes are allowed to inherit from interfaces:

interface Printable {
    fun prettyPrint(): String
}

@JvmInline
value class Name(private val s: String) : Printable {
    override fun prettyPrint(): String = "Let's $s!"
}

private fun eg2() {
    val name = Name("Kotlin")
    println(name.prettyPrint()) // Still called as a static method
}

// inline classes are always final

// representation, vs type aliases, & delegation