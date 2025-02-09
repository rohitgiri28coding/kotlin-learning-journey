import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main(){
    eg1()
    eg2()
    eg3()
    eg4()
    eg5()
    eg6()
    eg7()
    eg8()
    eg9()
    eg10()
    eg11()
}

//----------------------------------------------------------------------------------//

//              Asynchronous Flow

/*
Introduction
    Kotlin’s Flow API is a powerful tool for working with asynchronous streams of data.
    It lets you represent a sequence of values that are computed asynchronously and
    emitted over time. The Flow framework is built on top of Kotlin’s coroutines,
    offering a way to write reactive and non-blocking code in a clear, sequential style.
*/

/*
What is a Flow?
    A Flow is an asynchronous stream that can emit multiple values sequentially.
    Unlike a regular function that returns a single value, a Flow can emit a series of
    values over time.

Cold Streams:
    Flows are “cold,” meaning that the code inside a flow builder does not run until
    the flow is collected. Each new collection creates a fresh stream of values.

*/

/*
Creating Flows

Using the flow Builder
    The most common way to create a Flow is by using the flow { ... } builder.
    Inside the builder, you can perform suspendable operations and emit values using
    the emit() function.

*/
fun simpleFlow(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100L)  // Simulate some asynchronous work
        emit(i)      // Emit the next value
    }
}

private fun eg1() = runBlocking {
    // Collect the flow of numbers
    simpleFlow().collect { value ->
        println("Received $value")
    }
}
/*

Other Ways to Create Flows

flowOf: Creates a flow from a given set of values.
val numbers = flowOf(1, 2, 3, 4, 5)

asFlow: Converts collections or sequences into a flow.
val listFlow = listOf("A", "B", "C").asFlow()

*/

/*
Collecting Flows

Collecting a flow is what triggers its execution. The collect terminal operator
is a suspending function that processes each emitted value.

When collect is called, the flow starts executing, emitting values one by one.
Each value is processed in the lambda passed to collect.
*/

private fun eg2() = runBlocking {
    simpleFlow().collect { value ->
        println("Collected $value")
    }
}

/*
Flow Operators

Flow operators allow you to transform, filter, or combine flows.
They come in two types: intermediate operators and terminal operators.

Intermediate Operators
    These operators transform flows but do not trigger execution by themselves.
    They return a new Flow.

    map: Transforms each value.
    simpleFlow()
        .map { value -> value * 2 }
        .collect { println("Mapped value: $it") }

    filter: Filters emitted values.
    simpleFlow()
        .filter { it % 2 == 1 }
        .collect { println("Odd number: $it") }

    onEach: Allows you to perform an action on each value (e.g., logging) without altering the stream.
    simpleFlow()
        .onEach { println("Processing $it") }
        .collect { println("Result: $it") }

Terminal Operators
    Terminal operators start the flow’s execution and produce a final result.

    collect: Processes each value.
    toList / toSet: Collects all emitted values into a collection.
    single / first: Retrieves a single element from the flow.
*/


private fun eg3() = runBlocking {
    val numbers = simpleFlow().toList()
    println("Collected numbers: $numbers")
}

/*
Exception Handling in Flows

Handling errors in flows can be done with the catch operator or by using try/catch around terminal operators.

Using the catch Operator
The catch operator intercepts exceptions that occur upstream in the flow.
*/

fun errorProneFlow(): Flow<Int> = flow {
    emit(1)
    throw RuntimeException("Oops!")
}

private fun eg4() = runBlocking {
    errorProneFlow()
        .catch { e -> println("Caught exception: $e") }
        .collect { println("Collected $it") }
}

/*
Explanation:
The flow emits 1 then throws an exception.
The catch operator handles the exception, preventing it from crashing the application.


Using try/catch Around collect
You can also handle exceptions by wrapping the collection in a try/catch block.
*/

private fun eg5() = runBlocking {
    try {
        errorProneFlow().collect { println("Collected $it") }
    } catch (e: Exception) {
        println("Caught exception in collect: $e")
    }
}

/*
Context Preservation and the flowOn Operator

By default, a flow runs in the context (dispatcher) of the coroutine that collects
it. However, you can change the upstream context (where the emission happens) with
 the flowOn operator.

*/
private fun numbersFlow(): Flow<Int> = flow {
    // Emission happens on the IO dispatcher
    for (i in 1..5) {
        delay(100L)
        emit(i)
    }
}.flowOn(Dispatchers.IO)

private fun eg6() = runBlocking {
    numbersFlow().collect { value ->
        // Collection happens in runBlocking's context (main thread, by default)
        println("Received $value on thread ${Thread.currentThread().name}")
    }
}

//The flowOn operator changes the context for upstream operations (here,
// the emission of values happens on Dispatchers.IO).
//Downstream operators (like collect) run in the collecting coroutine’s context.

/*
Flow Cancellation

Flows are cancellable by default. If the coroutine that is collecting the flow gets
cancelled, the flow collection stops.
 */

private fun cancellableFlow(): Flow<Int> = flow {
    for (i in 1..10) {
        delay(100L)
        println("Emitting $i")
        emit(i)
    }
}

private fun eg7() = runBlocking {
    val job = launch {
        cancellableFlow().collect { value ->
            println("Collected $value")
            if (value == 5) cancel()  // Cancel the collection when value equals 5
        }
    }
    job.join()
    println("Flow collection cancelled.")
}

//The flow emits values every 100 milliseconds.
//When the value 5 is collected, the coroutine cancels, stopping further collection.


/*
Combining Flows

Flows can be combined using operators like zip and combine.

Using zip
The zip operator pairs values from two flows together.

 */
private fun numbersFlow1(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100L)
        emit(i)
    }
}

private fun lettersFlow(): Flow<String> = flow {
    for (letter in listOf("A", "B", "C")) {
        delay(150L)
        emit(letter)
    }
}

private fun eg8() = runBlocking {
    numbersFlow1()
        .zip(lettersFlow()) { number, letter ->
            "$number -> $letter"
        }
        .collect { println(it) }
}

/*
Explanation:

The zip operator combines the emissions of two flows, pairing the first value from each, then the second, and so on.
The resulting flow emits pairs like "1 -> A", "2 -> B", etc.
Using combine
The combine operator reacts to every new value from either flow, combining the latest values from both.

 */
private fun eg9() = runBlocking {
    val flow1 = flow {
        emit(1)
        delay(200L)
        emit(2)
    }
    val flow2 = flow {
        delay(100L)
        emit("A")
        delay(300L)
        emit("B")
    }

    flow1.combine(flow2) { number, letter ->
        "$number combined with $letter"
    }.collect { println(it) }
}

//Here, combine produces a new value every time either flow emits, using the latest values from both flows.


/*

Buffering in Flows
Sometimes the producer (emitter) is faster than the consumer (collector), or you want to decouple them to allow more concurrent processing. This is where buffering comes in.

The buffer Operator:
It introduces a buffer between the emission of values and their collection.
 */

private fun fastFlow(): Flow<Int> = flow {
    for (i in 1..5) {
        println("Emitting $i")
        emit(i)
        delay(100L)  // Fast producer
    }
}

private fun eg10() = runBlocking {
    fastFlow()
        .buffer()  // Buffer operator allows the flow to emit without waiting for the collector
        .collect { value ->
            delay(300L)  // Simulate a slow collector
            println("Collected $value")
        }
}

//Without buffering, each emission would wait for the collector to finish processing (sequential behavior).
//With buffer(), the emitter can run ahead, storing emitted values in a buffer until the collector is ready.


/*
Launching a Flow
Instead of using collect directly, you can launch a flow in a separate coroutine
using the launchIn operator. This is useful when you want the flow to be collected
concurrently with other work.

Using launchIn:
 */

private fun eg11() = runBlocking {
    // The onEach operator lets us perform an action on each emitted value
    val job = simpleFlow()
        .onEach { println("Collected $it") }
        .launchIn(this)  // Launches the flow collection in the scope of runBlocking

    job.join()  // Wait for the launched coroutine to finish
}

//launchIn starts collection in a new coroutine within the provided scope.
//This is handy for scenarios where you need the flow to run concurrently with
// other operations without manually launching a separate coroutine for collection.


/*
Summary

Flow Basics:
    Flow emits multiple asynchronous values.
    It’s a cold stream—nothing happens until you call a terminal operator like collect.

Creating Flows:
    Use the flow { } builder, flowOf, or asFlow for creating flows.

Collecting Flows:
    The collect operator triggers execution and processes each value.

Operators:
    Intermediate operators (map, filter, onEach) transform flows.
    Terminal operators (collect, toList, etc.) trigger flow execution.

Exception Handling:
    Use catch or try/catch around collect to manage errors.

Context and flowOn:
    Change the emission context using flowOn.

Cancellation:
    Flow collection is cancellable; cancellation propagates to the flow.

Combining Flows:
    Use operators like zip and combine to merge streams.

Buffering:
    Use buffer() to decouple the producer and consumer, allowing the emitter to run
    ahead of the collector.

Launching a Flow:
    Use launchIn (often combined with onEach) to start collecting a flow in a
    separate coroutine, enabling concurrent operations.
 */