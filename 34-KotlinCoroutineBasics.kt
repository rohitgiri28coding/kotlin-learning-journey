import kotlinx.coroutines.*

fun main(){
    eg1()
}
//----------------------------------------------------------------------------------//
//                      Coroutine

//A coroutine is a lightweight, suspendable computation that allows writing
// asynchronous code in a sequential and readable manner. Unlike threads,
// coroutines are not bound to any specific thread and can suspend execution in one
// thread and resume in another. This flexibility enables efficient resource
// utilization and simplifies asynchronous programming.

//Key Features:
//
//Suspending Functions: Functions marked with the suspend keyword can pause their
// execution without blocking the underlying thread, allowing other operations to
// run concurrently.
//Coroutine Builders: Functions like launch and async are used to start coroutines.
// For example, launch starts a new coroutine that doesn't return a result, while
// async starts a coroutine that returns a result encapsulated in a Deferred object.

private fun eg1() = runBlocking {
    launch {
        delay(1000L)
        println("World!")
    }
    println("Hello")
}

//Structured Concurrency: Coroutines follow structured concurrency principles,
// ensuring that they are launched within a specific scope (CoroutineScope). This
// structure helps manage the lifecycle of coroutines, preventing leaks and ensuring
// that an outer scope waits for all its child coroutines to complete.

//Job and Cancellation
//Each coroutine has a Job that controls its lifecycle.
//Coroutines can be canceled using cancel() or cooperative cancellation checks (isActive).

//Scope Builders
//runBlocking:
//Blocks the current thread until the coroutine body and its children complete.
//Used mainly in main functions or test environments to bridge synchronous and asynchronous code.
//coroutineScope:
//A suspending function that creates a new coroutine scope.
//Suspends (does not block) until all its child coroutines complete.
//Releases the underlying thread while suspended, allowing other work to be performed.

//Example 1: Using coroutineScope
//
//fun main() = runBlocking {
//    doWorld()
//}
//
//suspend fun doWorld() = coroutineScope {
//    launch {
//        delay(1000L)
//        println("World!")
//    }
//    println("Hello")
//}

// O/P:
//Hello
//World!

//The launch block runs concurrently, delaying for 1 second before printing "World!".
//"Hello" prints immediately as it is outside any delay.


//Concurrency with coroutineScope
//
//fun main() = runBlocking {
//    doWorld()
//    println("Done")
//}
//
//suspend fun doWorld() = coroutineScope {
//    launch {
//        delay(2000L)
//        println("World 2")
//    }
//    launch {
//        delay(1000L)
//        println("World 1")
//    }
//    println("Hello")
//}
//Output:
//
//Hello
//World 1
//World 2
//Done

//Two launch blocks execute concurrently.
//"World 1" completes after 1 second, followed by "World 2" after 2 seconds.
//The doWorld function suspends until both coroutines finish, allowing "Done" to
// print afterward.


//Using Explicit Job
//fun main() = runBlocking {
//    val job = launch {
//        delay(1000L)
//        println("World!")
//    }
//    println("Hello")
//    job.join() // Wait for the coroutine to complete
//    println("Done")
//}

//The job.join() ensures that the main coroutine waits for the launched coroutine
// to finish before printing "Done".


