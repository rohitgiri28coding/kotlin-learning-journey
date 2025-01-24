import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    eg1()
    eg2()
    eg3()
    eg4()
    eg5()
    eg6()
    eg7()
}

//----------------------------------------------------------------------------------//
//                          Coroutines and channels
/*
1. Introduction

    Channel is a way to communicate between coroutines.
    Channels are analogous to queues and enable the transfer of data streams safely
    between coroutines.

2. Channel Basics

    Channel Creation: Channel<T>() creates a channel that supports send and receive
    operations.
    Sending and Receiving Data:
    send(): Sends data to the channel.
    receive(): Receives data from the channel, suspending the caller until data is
    available.
    Example:
    A simple producer-consumer coroutine example illustrates sending and receiving
    data.


3. Closing Channels

    Why Close Channels: Signals that no more data will be sent.
    How to Close:
    Use channel.close().
    Behavior:
    After closing, receive() throws a ClosedReceiveChannelException when the channel
    is empty.
    Can iterate over the remaining elements using a for loop.
    Example:
    Demonstrates proper channel closing and iterating over elements.


4. Channels Are Fair

    Channels distribute work fairly among multiple receivers or senders.
    Fairness Example:
    Example code shows how fairness ensures that multiple consumers get data evenly
    from the same channel.


5. Buffered Channels

    Buffering in Channels:
    By default, channels are unbuffered (suspend until the other side is ready).
    Buffered channels allow predefining capacity with Channel(capacity).
    Behavior:
    Senders are suspended only when the buffer is full.
    Receivers suspend only when the buffer is empty.
    Example:
    Comparison of unbuffered and buffered channels through example.


6. Rendezvous Channel

    Definition: Unbuffered channel with a capacity of zero.
    Sender and receiver meet directly and suspend until the other is ready.


7. Conflated Channels

    Definition: Channel that buffers only the latest value, overwriting previous ones.
    Useful for use cases where only the most recent value matters.


8. Ticker Channels

    Definition: A channel that produces elements at fixed time intervals.
    Usage:
    Replace Java-style periodic tasks.
    Created with ticker(delayMillis, initialDelayMillis).

    Note: It has serious known flaws and they will be redesigned in the future.
    It will be deprecated in the future but there is no replacement for them yet,
    so it cannot be deprecated right away.

9. Best Practices

    Use structured concurrency to avoid memory leaks when working with channels.

*/


private fun eg1() = runBlocking {

    println("Channel Basics")

    val channel = Channel<Int>() // Create a channel
    coroutineScope {
        launch {
            // Sending data to the channel
            for (x in 1..5) {
                channel.send(x)
                println("Sent: $x")
            }
            channel.close() // Close the channel
        }

        // Receiving data from the channel
        for (y in channel) {
            println("Received: $y")
        }
    } // Automatically closes all child coroutines
}

private fun eg2() = runBlocking {

    println("Channels Are Fair")
    println("Output alternates messages fairly between senders.")

    val channel = Channel<String>()
    coroutineScope {
        launch {
            repeat(3) { channel.send("Message from Sender 1") }
        }

        launch {
            repeat(3) { channel.send("Message from Sender 2") }
        }

        launch {
            repeat(6) { println("Receiver got: ${channel.receive()}") }
        }

    }
    channel.close()
}

private fun eg3() = runBlocking {

    println("Buffered Channels")
    println("Buffered channels allow sending two items before suspending the sender.")

    val channel = Channel<Int>(2) // Buffer size of 2
    coroutineScope {
        launch {
            repeat(5) {
                println("Sending: $it")
                channel.send(it)
            }
            channel.close()
        }

        launch {
            for (msg in channel) {
                println("Received: $msg")
                delay(500) // Simulate processing delay
            }
        }
    }
}

private fun eg4() = runBlocking {

    println("Rendezvous Channel")
    println("Sender and receiver meet directly in unbuffered (Rendezvous) channels.")

    val channel = Channel<String>() // Unbuffered
    coroutineScope {
        launch {
            println("Sending: Hello")
            channel.send("Hello") // Suspends until received
            println("Sent: Hello")
        }

        launch {
            delay(1000) // Simulate some delay
            println("Receiving...")
            println("Received: ${channel.receive()}")
        }
    }
    channel.close()
}

private fun eg5() = runBlocking {

    println("Conflated Channels")

    val channel = Channel<Int>(Channel.CONFLATED)
    coroutineScope {
        launch {
            channel.send(1)
            channel.send(2) // Overwrites the first value
            channel.send(3) // Overwrites the second value
            println("Finished sending!")
        }

        println("Received: ${channel.receive()}") // Only gets the latest value
    }
    channel.close()
}


@OptIn(ObsoleteCoroutinesApi::class)
private fun eg6() = runBlocking {

    println("Ticker Channels")
    println("Ticks are received every second until canceled.")

    val tickerChannel = ticker(delayMillis = 1000, initialDelayMillis = 0)

    repeat(5) {
        println("Tick at ${System.currentTimeMillis()}")
        tickerChannel.receive() // Wait for the next tick
    }

    tickerChannel.cancel() // Stop the ticker
}

//                              repeat function

//The repeat(times) function in Kotlin is a utility function that allows to
// execute a block of code a specific number of times. It takes an integer times
// as a parameter and iterates from 0 to times - 1.

private fun eg7() {
    repeat(5) { index ->
        println("Iteration $index")
    }
}

/*
Why Use repeat?
    It is a concise and readable way to run repetitive code without needing
    traditional loops like for or while.
    Useful for fixed iteration tasks, such as sending multiple messages,
    initializing data, or testing scenarios.
*/
