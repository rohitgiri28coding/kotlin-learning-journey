fun main(){
    eg1()
    eg2()
    eg3()
    eg4()
    eg5()
    eg6()
    eg7()
}
//------------------------------------------------------------------------------------//
                        //Object Declaration and expression

//In Kotlin, objects allow you to define a class and create an instance of it in a
// single step. This is useful when you need either a reusable singleton instance
// or a one-time object. To handle these scenarios, Kotlin provides two key approaches:
// object declarations for creating singletons and object expressions for creating
// anonymous, one-time objects.

//A singleton ensures that a class has only one instance and provides a global
// point of access to it.

// Best use cases
// 1) Using singletons for shared resources
// 2) Creating factory methods
// 3) Modifying existing class behavior temporarily
// 4) Type-safe design is required

//Object declarations

/*
// Declares a Singleton object to manage data providers
object DataProviderManager {
    private val providers = mutableListOf<DataProvider>()

    // Registers a new data provider
    fun registerDataProvider(provider: DataProvider) {
        providers.add(provider)
    }

    // Retrieves all registered data providers
    val allDataProviders: Collection<DataProvider>
        get() = providers
}
This allows you to define a class and create an instance of it in a single step.
The initialization of an object declaration is thread-safe and done on first access.
To refer to the object, use its name directly:
DataProviderManager.registerDataProvider(exampleProvider)
*/

//Like variable declarations, object declarations are not expressions, so they cannot be used
// on the right-hand side of an assignment statement:

//// Syntax error: An object expression cannot bind a name.
//val myObject = object MySingleton {
//val name = "Singleton"
//}

//Object declarations cannot be local, which means they cannot be nested directly
// inside a function. However, they can be nested within other object declarations
// or non-inner classes.

//Data objects

//When printing a plain object declaration in Kotlin, the string representation contains both
// its name and the hash of the object:

object MyObject

private fun eg1() {
    println(MyObject)
    // MyObject@hashcode
}

// with data objects, it returns the actual name of the object when calling toString()
data object MyDataObject {
    const val NUMBER: Int = 3
}

private fun eg2() {
    println(MyDataObject)
    println(MyDataObject.NUMBER)
    // MyDataObject
}

//Differences between data objects and data classes

// 1) No copy() function for data objects
// 2) No componentN() function.

//Use data objects with sealed hierarchies

//Data object declarations are particularly useful for sealed hierarchies
// like sealed classes or sealed interfaces. They allow you to maintain symmetry
// with any data classes you may have defined alongside the object.

//Companion objects

//Companion objects allow you to define class-level functions and properties.
// This makes it easy to create factory methods, hold constants, and access
// shared utilities.

private class MyClass1 {
    companion object Factory {
        fun create(): MyClass1 = MyClass1()
    }
}

class User1(val name: String) {
    // Defines a companion object that acts as a factory for creating User instances
    companion object Factory {
        fun create(name: String): User1 = User1(name)
    }
}

private fun eg3(){
    // Calls the companion object's factory method using the class name as the qualifier.
    // Creates a new User instance
    val userInstance = User1.create("Rohit Giri")
    println(userInstance.name)
}


//Class members can access private members of their corresponding companion object:

class User2(val name: String) {
    companion object {
        private const val defaultGreeting = "Hello"
    }

    fun sayHi() {
        println(defaultGreeting)
    }
}
private fun eg4() {
    User2("Rohit").sayHi()
    // Hello
}

// members of companion objects ->  are actually instance members(they belong to the object itself.)
//This allows companion objects to implement interfaces:
//

interface Factory<T> {
    fun create(name: String): T
}

class User3(val name: String) {
    // Defines a companion object that implements the Factory interface
    companion object : Factory<User3> {
        override fun create(name: String): User3 = User3(name)
    }
}

private fun eg5() {
    // Uses the companion object as a Factory
    val userFactory: Factory<User3> = User3
    val newUser = userFactory.create("Example User")
    println(newUser.name)
    // Example User
}

//Object expressions

//Object expressions declare a class and create an instance of that class,
// but without naming either of them. These classes are useful for one-time use.
// They can either be created from scratch, inherit from existing classes, or
// implement interfaces. Instances of these classes are also called anonymous
// objects because they are defined by an expression, not a name.

//Create anonymous objects from scratch

val helloWorld = object {
    val hello = "Hello"
    val world = "World"
    // Object expressions extend the Any class, which already has a toString() function,
    // so it must be overridden
    override fun toString() = "$hello $world"
}

private fun eg6() {
    print(helloWorld)
    // Hello World
}

//Use anonymous objects as return and value types

class UserPreferences {
    private fun getPreferences() = object {
        val theme: String = "Dark"
        val fontSize: Int = 14
    }

    fun printPreferences() {
        val preferences = getPreferences()
        println("Theme: ${preferences.theme}, Font Size: ${preferences.fontSize}")
    }
}
private fun eg7(){
    val obj = UserPreferences()
    obj.printPreferences()
}

//Anonymous objects' accessibility depends on the visibility and declared type of the function or property returning them.
//Members added in the anonymous object are not accessible unless they are declared in the visible type.
//Overridden members are accessible if they are part of the type explicitly declared or inferred for the function/property.

//Behavior difference between object declarations and expressions

// 1) Object expressions are executed (and initialized) immediately, where they are used.
// 2) Object declarations are initialized lazily, when accessed for the first time.
// 3) A companion object is initialized when the corresponding class is loaded (resolved) that matches the semantics of a Java static initializer.
