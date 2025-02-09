import kotlinx.coroutines.*

fun main() {
    eg1()
    eg2()
    eg3()
    eg4()
    eg5()
}
//----------------------------------------------------------------------------------//

//                    Coroutine Context & Dispatchers

/*

Introduction

    It explains how every coroutine in Kotlin runs within a context that defines its
    behavior, including its lifecycle, thread confinement, and additional attributes.
    This context is a collection of elements, with two of the most important being the
    Job (which handles cancellation and the lifecycle) and the CoroutineDispatcher
    (which determines the thread or threads on which the coroutine executes).


1. Coroutine Context

    Definition:
    The coroutine context is an immutable set of elements that describes the environment
    where a coroutine runs.

    Components:
        Job: Manages the lifecycle and cancellation of a coroutine.
        CoroutineDispatcher: Determines which thread or thread pool the coroutine uses.
        Other elements: Such as CoroutineName for debugging, or custom elements.

    Combining Contexts:

    You can combine multiple context elements using the + operator.
    For example:
    launch(Dispatchers.Default + CoroutineName("MyCoroutine")) { /* ... */ }


2. Dispatchers
    Dispatchers are a key part of the context as they specify the thread(s) the coroutine uses.

    Dispatchers.Default:
    Optimized for CPU-intensive work.
    Uses a shared pool of background threads.

    Dispatchers.IO:
    Designed for IO-bound tasks (e.g., network or disk operations).
    Uses a larger pool of threads to better handle blocking IO operations.

    Dispatchers.Main:
    Used for UI applications.
    Ensures that coroutines run on the main (UI) thread.
    (Available in platforms like Android and JavaFX.)

    Dispatchers.Unconfined:
    Starts the coroutine in the current call-frame until the first suspension.
    After suspension, it resumes in whatever thread that is determined by the suspending function.
    Not confined to any specific thread, so its behavior can be less predictable.
    Generally used for simple or testing scenarios.

*/

//Using Dispatchers.Default

private fun eg1() = runBlocking {
    println("Main thread: ${Thread.currentThread().name}")

    // Launch a coroutine with the Default dispatcher
    val job = launch(Dispatchers.Default) {
        println("Coroutine started on thread: ${Thread.currentThread().name}")
        // Simulate some CPU-intensive work
        delay(1000L)
        println("Coroutine finished on thread: ${Thread.currentThread().name}")
    }
    job.join()
}

//Using Dispatchers.IO

private fun eg2() = runBlocking {
    println("Main thread: ${Thread.currentThread().name}")

    // Launch a coroutine with the IO dispatcher
    val job = launch(Dispatchers.IO) {
        println("IO Coroutine started on thread: ${Thread.currentThread().name}")
        // Simulate an IO operation with a delay
        delay(1000L)
        println("IO Coroutine finished on thread: ${Thread.currentThread().name}")
    }
    job.join()
}

//Using Dispatchers.Main
private fun eg3() = runBlocking {
    println("Main thread: ${Thread.currentThread().name}")

    // Launch a coroutine with the Main dispatcher
    val job = launch(Dispatchers.Main) {
        println("Main Coroutine started on thread: ${Thread.currentThread().name}")
        // Simulate some UI work
        delay(1000L)
        println("Main Coroutine finished on thread: ${Thread.currentThread().name}")
    }
    job.join()
}

//Using Dispatchers.Unconfined

private fun eg4() = runBlocking {
    println("Main thread: ${Thread.currentThread().name}")

    // Launch a coroutine with the Unconfined dispatcher
    val job = launch(Dispatchers.Unconfined) {
        println("Unconfined Coroutine starts on thread: ${Thread.currentThread().name}")
        delay(500L) // Suspension point
        // After delay, it might resume on a different thread
        println("Unconfined Coroutine resumes on thread: ${Thread.currentThread().name}")
    }
    job.join()
}

//Using a Custom Coroutine Context

private fun eg5() = runBlocking {
    // Launch a coroutine with a combined context:
    // Uses the Default dispatcher and assigns a name for easier debugging.
    val job = launch(Dispatchers.Default + CoroutineName("CombinedContextCoroutine")) {
        println("Running ${coroutineContext[CoroutineName]?.name} on thread: ${Thread.currentThread().name}")
        delay(1000L)
        println("Completed ${coroutineContext[CoroutineName]?.name} on thread: ${Thread.currentThread().name}")
    }
    job.join()
}

