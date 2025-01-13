//----------------------------------------------------------------------------------//
//                            Type Aliases

//Type aliases provide alternative names for existing types.

//typealias NodeSet = Set<Network.Node>

//typealias FileTable<K> = MutableMap<K, MutableList<File>>

//aliases for function types:

private typealias MyHandler = (Int, String, Any) -> Unit

private typealias Predicate<T> = (T) -> Boolean

//You can have new names for inner and nested classes:

private class A1 {
    inner class Inner
}
private class B1 {
    inner class Inner
}

private typealias AInner = A1.Inner
private typealias BInner = B1.Inner


//typealias Predicate<T> = (T) -> Boolean
//This defines a type alias Predicate<T> for the functional type (T) -> Boolean.
// This alias does not create a new type; it’s simply a shorthand.

//Why Use Type Aliases?
// 1) Readability: Simplifies complex or verbose type signatures.
// 2) Code Clarity: Provides meaningful names for common patterns or types.
