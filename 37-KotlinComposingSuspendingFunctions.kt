import kotlinx.coroutines.*

fun main(){
    eg1()
    eg2()
    eg3()
}

//----------------------------------------------------------------------------------//
//                              Composing Suspending                             //

/*

1.Suspending Functions Are Like Regular Functions…
…but They Can Suspend Work

    A suspending function is marked with the suspend keyword and can suspend its
    execution without blocking the underlying thread.

    You can call suspending functions from other suspending functions or within a
    coroutine. Their ability to suspend means that while waiting (for example,
    during a network request or delay), they don’t block the thread, which makes them
    efficient and easy to compose.

2. Sequential Composition

    What It Means:
    When you call suspending functions one after another, the code reads just like
    normal sequential code—even though under the hood, it might be suspending and
    resuming.

    Example Scenario:
    Imagine you have two independent operations, like fetching two different pieces
    of data. If the operations don’t depend on each other, you can call them
    sequentially, one after the other.


3. Concurrent Composition

    What It Means:
    When operations are independent and can run at the same time, you can launch
    them concurrently.

    How It’s Done:
    You can use coroutine builders like async to start concurrent operations and then
    wait for their results using await(). This allows your program to perform both
    tasks in parallel, potentially reducing the overall execution time.

4. Structured Concurrency
    Concept:
    Kotlin enforces structured concurrency. This means that when you launch new
    coroutines, they are tied to a specific scope (e.g., using runBlocking or a
    custom CoroutineScope). This helps manage the lifetime of coroutines, making it
    easier to cancel or wait for them as needed.

*/


//Sequential Composition

private suspend fun doSomethingUsefulOne(): Int {
    delay(1000L)  // Simulate a long-running task
    return 13
}

private suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L)  // Simulate another long-running task
    return 29
}

private fun eg1() = runBlocking {
    println("Starting sequential work...")
    // The functions are called one after the other:
    val result = doSomethingUsefulOne() + doSomethingUsefulTwo()
    println("Sequential Result: The answer is $result")
}

/*
Both doSomethingUsefulOne() and doSomethingUsefulTwo() are suspending functions
that simulate a delay.

The calls are made sequentially, so the total time taken will be roughly the sum
of both delays (about 2 seconds).
*/


//Concurrent Composition

private fun eg2() = runBlocking {
    println("Starting concurrent work...")
    // Launch both tasks concurrently
    val deferredOne = async { doSomethingUsefulOne() }
    val deferredTwo = async { doSomethingUsefulTwo() }

    // Await both results concurrently
    val result = deferredOne.await() + deferredTwo.await()
    println("Concurrent Result: The answer is $result")
}

/*
The async builder launches each suspending function in its own coroutine.

Both operations run concurrently. Since they run in parallel, the total time
taken will be around the time of the longer single task (about 1 second) rather
than the sum of both.

The await() function is used to retrieve the result of each async computation
once it is ready.
*/


//Structured Concurrency

private fun eg3() = runBlocking {
    println("Structured Concurrency")
    println("Child coroutines are cancelled when the parent coroutine is cancelled.")

    // Launch a new coroutine
    val job = launch {
        repeat(10) { i ->
            println("Coroutine is still running: $i")
            delay(500L)
        }
    }

    delay(1300L) // Delay for a while
    println("Cancelling the coroutine!")
    job.cancel() // Cancel the job
    job.join() // Wait for the job's completion
    println("Coroutine is cancelled!")
}

/*
Summary

1. Suspending functions let you write asynchronous, non-blocking code in a clear,
   sequential style.

2. Sequential composition is as simple as calling one suspending function after
   another.

3. Concurrent composition is achieved with coroutine builders like async to run
   independent operations in parallel, then combining their results with await().

4. Structured concurrency ensures that all launched coroutines are managed within a
   specific scope, making them easier to control.
*/