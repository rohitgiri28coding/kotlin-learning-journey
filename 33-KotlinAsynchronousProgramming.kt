//----------------------------------------------------------------------------------//
//                      Asynchronous programming

//Asynchronous programming in Kotlin is primarily facilitated through coroutines, which allow developers to write non-blocking, concurrent code in a sequential and readable manner.
//
//Threading

//Threads are by far probably the most well-known approach to avoid applications from blocking.
/*
fun postItem(item: Item) {
    val token = preparePost()
    val post = submitPost(token, item)
    processPost(post)
}

fun preparePost(): Token {
    // makes a request and consequently blocks the main thread
    return token
}

 */
//But it has a series of drawbacks:
//1. Threads aren't cheap. Threads require context switches which are costly.
//2. Threads aren't infinite. The number of threads that can be launched is limited by the underlying operating system. In server-side applications, this could cause a major bottleneck.
//3. Threads aren't always available. Some platforms, such as JavaScript do not even support threads.
//4. Threads aren't easy. Debugging threads and avoiding race conditions are common problems we suffer in multi-threaded programming.

//Callbacks
//With callbacks, the idea is to pass one function as a parameter to another function, and have this one invoked once the process has completed.
/*
fun postItem(item: Item) {
    preparePostAsync { token ->
        submitPostAsync(token, item) { post ->
            processPost(post)
        }
    }
}
fun preparePostAsync(callback: (Token) -> Unit) {
    // make request and return immediately
    // arrange callback to be invoked later
}
*/
//Issues:
//1. Difficulty of nested callbacks
//2. Error handling is complicated

//Futures, promises, and similar abstractions represent a fundamental shift in asynchronous programming, helping manage operations that complete at some future time. Here's a breakdown of their concepts, advantages, and challenges:
//
//Key Concepts

//Future or Promise:
//Represents a placeholder for a result that will be available in the future.
//The Promise is what is returned immediately, and it "promises" to eventually provide a result (or an error).
//Chaining Operations:
//Promises often provide methods like thenCompose (or flatMap in some languages) to chain dependent asynchronous calls, forming a compositional structure.

/*
fun postItem(item: Item) {
    preparePostAsync()               // Step 1: Prepare request
        .thenCompose { token ->      // Step 2: Use token to submit the post
            submitPostAsync(token, item)
        }
        .thenAccept { post ->        // Step 3: Process the post once received
            processPost(post)
        }
}
*/
//Key Changes in Programming
//Shift to Asynchronous and Compositional Thinking:
//Moves away from the synchronous, step-by-step imperative model to a more declarative and compositional style.
//Loss of Traditional Constructs:
//Constructs like for loops, try-catch, and if-else often need to be rethought or replaced with Promise-specific approaches.
//Specific Return Types:
//Instead of directly receiving the data, the API returns a Promise or Future that must be resolved to access the result.

//Reactive Extensions
//Reactive Extensions (Rx) provide a powerful way to handle asynchronous programming by treating data as observable streams. Here's a breakdown of key concepts and benefits:
//
//Key Concepts
//Observable Streams:
//Rx revolves around treating data as streams, which can represent a sequence of asynchronous events or data points over time.
//These streams are infinite and can be observed, making it easier to react to changes or events as they occur.
//Observer Pattern:
//Rx extends the traditional Observer Pattern, enabling efficient handling of asynchronous event streams.
//It provides operators that allow us to filter, transform, combine, and perform other operations on the data flowing through streams.
//Cross-Platform Consistency:
//Rx has been ported to multiple platforms like C# (.NET), Java (RxJava), and JavaScript (RxJS), offering a similar API across different programming languages.
