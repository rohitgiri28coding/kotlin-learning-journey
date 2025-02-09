import kotlinx.coroutines.*

fun main() {
    eg1()
    eg2()
    eg3()
    eg4()
}

//----------------------------------------------------------------------------------//
//                        Cancellation & Timeouts                              //
/*
Cooperative Cancellation

    What It Means:
    Kotlin’s coroutines are designed to be cooperative when it comes to cancellation.
    This means that a coroutine must periodically check if it has been cancelled and
    then gracefully exit.

    How It Works:
    Many suspending functions (like delay()) check for cancellation automatically.
    If a coroutine is performing a long-running CPU-bound task that doesn’t suspend,
    you might need to explicitly check for cancellation (using functions like yield()
    or ensureActive()).

Cancelling a Coroutine

    Using a Job:
    Every coroutine is associated with a Job. When you call cancel() on a Job,
    it requests that the coroutine stop its work. The coroutine will throw a
    CancellationException (a normal part of its control flow) when it notices the
    cancellation.

    Structured Concurrency:
    Cancelling a parent coroutine cancels all its child coroutines, keeping your
    concurrency model clean and predictable.

Timeouts

    withTimeout:
    Runs a block of code with a time limit. If the block doesn’t complete within the
    given time, it throws a TimeoutCancellationException and cancels the coroutine.

    withTimeoutOrNull:
    Similar to withTimeout, but instead of throwing an exception on timeout, it
    returns null. This can be useful when you want to handle a timeout case without
    using try/catch.

Clean-up and Finalization

    Using finally:
    When a coroutine is cancelled, you might need to perform cleanup actions
    (like releasing resources). A finally block can be used to run cleanup code.

    Non-Cancellable Context:
    If you need to execute cleanup code that should not be cancelled (for example,
    saving state or closing a connection), you can wrap that code in
    withContext(NonCancellable) { … }.

*/

//Cooperative Cancellation

private fun eg1() = runBlocking {
    // Launch a coroutine that does some work
    val job = launch {
        repeat(1000) { i ->
            println("Coroutine: Working on iteration $i")
            delay(500L)  // 'delay' is cancellable
        }
    }

    // Let the coroutine work for a while
    delay(1300L)
    println("EG1: Cancelling the coroutine!")
    job.cancel()   // Request cancellation
    job.join()     // Wait for the coroutine to finish its cancellation
    println("EG1: Coroutine has been cancelled. Exiting.")
}

//The coroutine repeatedly delays for 500 milliseconds, which is a suspending and
// cancellable operation.
//After 1300 milliseconds, the main block cancels the coroutine.
//The cancellation is cooperative; the coroutine stops at the next suspension
// point (during delay).


//Using withTimeout

private fun eg2() = runBlocking {
    try {
        withTimeout(1300L) {
            repeat(1000) { i ->
                println("Task: Processing iteration $i")
                delay(500L)  // Work that is subject to timeout
            }
        }
    } catch (e: TimeoutCancellationException) {
        println("Task: Timed out!")
    }
}

//The withTimeout(1300L) block will throw a TimeoutCancellationException if the
// work inside it (the repeat loop) takes longer than 1300 milliseconds.
//The exception is caught and handled, printing "Task: Timed out!"


//Using withTimeoutOrNull

private fun eg3() = runBlocking {
    val result = withTimeoutOrNull(1300L) {
        repeat(1000) { i ->
            println("Task: Working on iteration $i")
            delay(500L)
        }
        "Completed Successfully"  // This is returned if the block finishes in time
    }

    // Check the result; it will be null if a timeout occurred.
    println("Result: $result")
}

//If the block finishes before the timeout, the string "Completed Successfully"
// is returned.
//If the timeout occurs, withTimeoutOrNull returns null, which can be handled
// accordingly.


//Handling Cleanup on Cancellation

private fun eg4() = runBlocking {
    val job = launch {
        try {
            repeat(1000) { i ->
                println("Coroutine: Working on iteration $i")
                delay(500L)
            }
        } finally {
            // Cleanup code that runs when the coroutine is cancelled
            println("Coroutine: Running cleanup before cancellation.")
            // If needed, execute non-cancellable cleanup code:
            withContext(NonCancellable) {
                // Non-cancellable cleanup actions here
                delay(100L) // Even delay here will not be cancelled
                println("Coroutine: Cleanup complete.")
            }
        }
    }

    delay(1300L)
    println("EG4: Cancelling the coroutine!")
    job.cancelAndJoin()
    println("EG4: Coroutine cancelled and cleanup done.")
}

//The finally block ensures that cleanup code runs when the coroutine is cancelled.
//withContext(NonCancellable) is used to ensure that the cleanup itself isn’t
//interrupted by further cancellation.
