package question2

fun main() {
    val list = mutableListOf<Int>()
    println("Nhap so phan tu cua mang: ")
    val n = readln().toInt()
    repeat(n) {
        list += readln().toInt()
    }
    println("Phan tu con thieu la: ")
    println("first way: ${firstWay(list)}")
    println("second way: ${secondWay(list)}")
}

// this way is better since it only requires 1 loop
fun firstWay(list: List<Int>): Int {
    val sum = (list.size + 2) * (list.size + 1) / 2
    return sum - list.sum()
}

fun secondWay(list: List<Int>): Int {
    val newList = list.sorted()
    for (i in 0 .. newList.size - 2) {
        if (newList[i] + 1 != newList[i + 1]) {
            return newList[i] + 1
        }
    }
    return -1
}