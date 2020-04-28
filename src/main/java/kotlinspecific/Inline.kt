package kotlinspecific

/** for a function that accept another function as input
 mark it inline to copy the incoming function, instead of creating function object
 work best for short incomming function, and then the incomming function is called repeatedly
 cuz if the incomming function is called n times, the function object is created for n times */
inline fun repeat(times: Int, action: (Int) -> Unit) {
    for (index in 0 until times) {
        action(index)
    }
}
/** Say you have multiple lambdas in your inlined function and
 you don't want all of them to be inlined, you can mark the lambdas you don't want to be inlined with the noinline keyword: */
inline fun higherOrderFunction(aLambda: () -> Unit, noinline dontInlineLambda: () -> Unit, aLambda2: () -> Unit) {

    aLambda()
    dontInlineLambda()//won't be inlined.
    aLambda2()

    sum(1, 2) {
        println("Result is: $it")
        //return // return is not allowed here
        return@sum  // ok
    }
}
/**The crossinline marker is used to mark lambdas that mustn't allow non-local returns,
especially when such lambda is passed to another execution context such as a higher order
function that is not inlined, a local object or a nested function. In other words,
you won't be able to do a return in such lambdas.*/
inline fun sum(a: Int, b: Int, crossinline lambda: (result: Int) -> Unit): Int {
    val r = a + b
    lambda.invoke(r)
    return r
}

//注意程序控制流
//当使用 inline 时，如果传递给 inline 函数的 lambda，有 return 语句，那么会导致闭包的调用者也返回。
//例子:
//inline fun sum(a: Int, b: Int, lambda: (result: Int) -> Unit): Int {
//    val r = a + b
//    lambda.invoke(r)
//    return r
//}
//fun main(args: Array<String>) {
//    println("Start")
//    sum(1, 2) {
//        println("Result is: $it")
//        return // 这个会导致 main 函数 return
//    }
//    println("Done")
//}
