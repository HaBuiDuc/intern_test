package question1

fun main() {
    val storage = Storage()
    var select = 0
    do {
        println("Nhap lua chon: ")
        println("1. Nhap san pham")
        println("2. Xuat san pham")
        println("3. Tinh tong gia tri san pham")
        println("4. Tim san pham dat tien nhat")
        println("5. Kiem tra neu san pham co hang")
        println("6. Sap xep san pham")
        println("7. Thoat")
        select = readln().toInt()
        when (select) {
            1 -> storage.input()
            2 -> storage.output()
            3 -> {
                val result = storage.calculateTotalValue()
                println("Tong gia tri san pham la: $result")
            }
            4 -> {
                val result = storage.findTheMostExpensive()
                if (result == null) {
                    println("Khong tim thay san pham")
                } else {
                    println("San pham co gia tri dat nhat la: $result")
                }
            }
            5 -> {
                println("Nhap ten san pham muon tim: ")
                val name = readln()
                val result = storage.isInStock(name)
                if (result) {
                    println("San pham co trong kho")
                } else {
                    println("San pham khong co trong kho")
                }
            }
            6 -> {
                println("Nhap thuoc tinh sap xep: ")
                val sortField = readln()
                println("Sap xep tang dan hay giam dan: 1. Tang dan, 2. Giam dan")
                val sortType = readln().toInt()
                val result = storage.sortProducts(sortField, sortType == 1)
                println("San pham sau khi sap xep: ")
                result.forEach { println(it.toString()) }
            }
        }
    } while (select != 7)
}