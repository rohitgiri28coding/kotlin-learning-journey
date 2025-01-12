fun main(){
    eg1()
}
//------------------------------------------------------------------------------------//
                            // Sealed classes and interfaces

//Sealed classes and interfaces provide controlled inheritance of your class hierarchies.
// All direct subclasses of a sealed class are known at compile time.
//The same logic applies to sealed interfaces and their implementations: once a module
// with a sealed interface is compiled, no new implementations can be created.

//When you combine sealed classes and interfaces with the when expression,
// you can cover the behavior of all possible subclasses and ensure that no new
// subclasses are created to affect your code adversely.

//Sealed classes are best used for scenarios when:
/*
 1) Limited class inheritance is desired: You have a predefined, finite set of
 subclasses that extend a class, all of which are known at compile time.

 2) Type-safe design is required: Safety and pattern matching are crucial in your
 project. Particularly for state management or handling complex conditional logic.

 3) Working with closed APIs: You want robust and maintainable public APIs for
 libraries that ensure that third-party clients use the APIs as intended.
 */

//Constructors
//A sealed class itself is always an abstract class, and as a result, can't be
// instantiated directly. However, it may contain or inherit constructors.
// These constructors aren't for creating instances of the sealed class itself
// but for its subclasses.

sealed class Error(val message: String) {
    class NetworkError : Error("Network failure")
    class DatabaseError : Error("Database cannot be reached")
    class UnknownError : Error("An unknown error has occurred")
}
private fun eg1() {
    val errors = listOf(Error.NetworkError(), Error.DatabaseError(), Error.UnknownError())
    errors.forEach { println(it.message) }
    // Network failure
    // Database cannot be reached
    // An unknown error has occurred
}

//You can use enum classes within your sealed classes to use enum constants
// to represent states and provide additional detail.

//Each enum constant exists only as a single instance,
// while subclasses of a sealed class may have multiple instances.

enum class ErrorSeverity { MINOR, MAJOR, CRITICAL }

sealed class Error1(val severity: ErrorSeverity) {
    class FileReadError(val file: String): Error1(ErrorSeverity.MAJOR)
    class DatabaseError(val source: String): Error1(ErrorSeverity.CRITICAL)
    data object RuntimeError : Error1(ErrorSeverity.CRITICAL)
    // Additional error types can be added here
}

//Constructors visibility

sealed class IOError {
    // A sealed class constructor has protected visibility by default. It's visible inside this class and its subclasses
    constructor() { /*...*/ }

    // Private constructor, visible inside this class only.
    // Using a private constructor in a sealed class allows for even stricter control over instantiation, enabling specific initialization procedures within the class.
    private constructor(description: String): this() { /*...*/ }

    // This will raise an error because public and internal constructors are not allowed in sealed classes
    // public constructor(code: Int): this() {}
}

//Inheritance

//enum classes can't extend a sealed class, or any other class.
// However, they can implement sealed interfaces:

sealed interface Error2

// enum class extending the sealed interface Error
enum class ErrorType : Error2 {
    FILE_ERROR, DATABASE_ERROR
}

//Use sealed classes with when expression

fun log(e: Error) = when(e) {
    is Error.NetworkError-> println("Error while reading file")
    is Error.DatabaseError -> println("Error while reading from database")
    is Error.UnknownError -> println("Unknown Error occurred")
    // No `else` clause is required because all the cases are covered
}

// Note: In multiplatform projects, if you have a sealed class with a when expression
// as an expected declaration in your common code, you still need an else branch.
// This is because subclasses of actual platform implementations may extend
// sealed classes that aren't known in the common code.


//Use case scenarios

// 1) State management in UI applications

sealed class UIState {
    data object Loading : UIState()
    data class Success(val data: String) : UIState()
    data class Error(val exception: Exception) : UIState()
}

fun updateUI(state: UIState) {
    when (state) {
        is UIState.Loading -> TODO()
        is UIState.Success -> TODO()
        is UIState.Error -> TODO()
    }
}

// 2) Payment method handling

sealed class Payment {
    data class CreditCard(val number: String, val expiryDate: String) : Payment()
    data class PayPal(val email: String) : Payment()
    data object Cash : Payment()
}

fun processPayment(payment: Payment) {
    when (payment) {
        is Payment.CreditCard -> TODO()/*processCreditCardPayment(payment.number, payment.expiryDate)*/
        is Payment.PayPal -> TODO()/*processPayPalPayment(payment.email)*/
        is Payment.Cash -> TODO()/*processCashPayment()*/
    }
}

// 3) API request-response handling

//// Import necessary modules
//import io.ktor.server.application.*
//import io.ktor.server.resources.*
//
//import kotlinx.serialization.*
//
//// Define the sealed interface for API requests using Ktor resources
//@Resource("api")
//sealed interface ApiRequest
//
//@Serializable
//@Resource("login")
//data class LoginRequest(val username: String, val password: String) : ApiRequest
//
//
//@Serializable
//@Resource("logout")
//object LogoutRequest : ApiRequest
//
//// Define the ApiResponse sealed class with detailed response types
//sealed class ApiResponse {
//    data class UserSuccess(val user: UserData) : ApiResponse()
//    data object UserNotFound : ApiResponse()
//    data class Error(val message: String) : ApiResponse()
//}
//
//// User data class to be used in the success response
//data class UserData(val userId: String, val name: String, val email: String)
//
//// Function to validate user credentials (for demonstration purposes)
//fun isValidUser(username: String, password: String): Boolean {
//    // Some validation logic (this is just a placeholder)
//    return username == "validUser" && password == "validPass"
//}
//
//// Function to handle API requests with detailed responses
//fun handleRequest(request: ApiRequest): ApiResponse {
//    return when (request) {
//        is LoginRequest -> {
//            if (isValidUser(request.username, request.password)) {
//                ApiResponse.UserSuccess(UserData("userId", "userName", "userEmail"))
//            } else {
//                ApiResponse.Error("Invalid username or password")
//            }
//        }
//        is LogoutRequest -> {
//            // Assuming logout operation always succeeds for this example
//            ApiResponse.UserSuccess(UserData("userId", "userName", "userEmail")) // For demonstration
//        }
//    }
//}
//
//// Function to simulate a getUserById call
//fun getUserById(userId: String): ApiResponse {
//    return if (userId == "validUserId") {
//        ApiResponse.UserSuccess(UserData("validUserId", "John Doe", "john@example.com"))
//    } else {
//        ApiResponse.UserNotFound
//    }
//    // Error handling would also result in an Error response.
//}
//
//// Main function to demonstrate the usage
//private fun eg2() {
//    val loginResponse = handleRequest(LoginRequest("user", "pass"))
//    println(loginResponse)
//
//    val logoutResponse = handleRequest(LogoutRequest)
//    println(logoutResponse)
//
//    val userResponse = getUserById("validUserId")
//    println(userResponse)
//
//    val userNotFoundResponse = getUserById("invalidId")
//    println(userNotFoundResponse)
//}
