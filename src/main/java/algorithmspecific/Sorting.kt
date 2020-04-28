package algorithmspecific

/**
 * @param MutableList<T> unsorted list
 * insertion sort is of O(n^2)
 * which make it much less effiency
 */
fun <T : Comparable<T>> insertionSort(items: MutableList<T>): List<T> {
    if (items.isEmpty() || items.size < 2) {
        return items
    }
    for (key in 1 until items.count()) {
        val item = items[key]
        var i = key
        while (i > 0 && item < items[i - 1]) {
            items[i] = items[i - 1]
            i -= 1
        }
        items[i] = item
    }
    return items
}

fun main() {
    val unsortedList = mutableListOf(8, 3, 2, 7, 4)
    var orderedList = insertionSort(unsortedList)
    println(orderedList)
}