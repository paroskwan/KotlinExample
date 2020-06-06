package kotlinspecific

/**
 * Generics
 * allow you to defines types that have "type parameters"
 * when instance of these types are created, those parameters are replaced by "type arguments"
 *
 * Can have generic type parameters at method of class, interface, top level function, extension function
 * You can’t declare a generic non-extension property
 */

/**
 * Generic function
 * Not that the first <T> is used to declare the type parameter
 * */
fun <T> List<T>.firstThree(indices: IntRange): List<T> {
    return if (size <= 3) this else this.subList(0, 3)
}

/**
 * Generic Class Example
 */
interface CustomList<T> {
    operator fun get(index: Int): T
}

class StringCustomList : CustomList<String> {
    /* This class provide the specific type argument, T is replaced by Strings*/
    override fun get(index: Int): String = "Its sth"
     // val <S> x: S = TODO() // not possible for non-extension properties to be generate
}

class ArrayCustomList<T> : CustomList<T> {
    /*the Type parameter are supposed to be another type parameter */
    override fun get(index: Int): T = this[index]
}

/**
 * Generic Type with constraints
 */

fun <T : Number> List<T>.sum(): T { // T must be subclass of Number
    // in Java its is <T extends Number>
    TODO("implement method")
}

fun <T : Comparable<T>> max(first: T, second: T): T {
    return if (first > second) first else second
}

/**
 *  Generic Type with multiple constraints
 */
fun <T> ensureTrailingPeriod(seq: T)
        where T : CharSequence, T : Appendable {
    if (!seq.endsWith('.')) {
        seq.append('.')
    }
}

/**
 * Ensure the Generic Type is non-nullable
 */

class Processor<T : Any> { // bound it with Any
    fun process(value: T) {
        value.hashCode()
    }
}


/**
 *
 * Generics at runtimes
 * generally type arguments are erased List<Int>, List<String> are just List
 * unless the function is inline
 *
 */

fun checkIfIsType(randomList : Any, intCollection: Collection<Int>) {


//    val firstTest = randomList is List<Int>
//    print("randomList is List<Int>$firstTest")

    //casting is non-safe
    //randomList as List<Int>

    val secondTest = randomList is List<*>
    print("randomList is List<*>$secondTest")

    val thirdTest = intCollection is List<Int>
    print("intCollection is List<int>$thirdTest")
        // as the input is already Int, the type is known

}

interface BaseDto{
    fun printiam()
}

class Herd<out T:BaseDto>{
//    fun printbyiam(){
//        (T as BaseDto)
//    }
    fun <T> printthey(animal:T){
        (animal as BaseDto).printiam()
    }
}

class Cat:BaseDto{
    override fun printiam(){
        print("i am cat")
    }
}

class Dog:BaseDto{
    override fun printiam(){
        print("i am dog")
    }
}



/**If a function accepts a read-only list, you can pass a List with a more specific
element type. If the list is mutable, you can’t do that. */

fun main(){
    val a = listOf(1,2,3)
    val b = "String"

    checkIfIsType(b,a)

}