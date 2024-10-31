package question1

class Storage {
    private var productList: List<Product> = emptyList()

    fun calculateTotalValue(): Double {
        val result = productList.sumOf { product -> product.price * product.quantity }
        return result
    }

    fun findTheMostExpensive(): Product? {
        val result = productList.maxByOrNull { product -> product.price }
        return result
    }

    fun isInStock(productName: String): Boolean {
        return productList.any { product -> product.name.lowercase() == productName.lowercase() }
    }

    fun sortProducts(sortedBy: String, ascending: Boolean = true): List<Product> {
        return when (sortedBy.lowercase()) {
            "name" -> if (ascending) productList.sortedBy { it.name } else productList.sortedByDescending { it.name }
            "price" -> if (ascending) productList.sortedBy { it.price } else productList.sortedByDescending { it.price }
            "quantity" -> if (ascending) productList.sortedBy { it.quantity } else productList.sortedByDescending { it.quantity }
            else -> throw IllegalArgumentException("An exception thrown")
        }
    }

    fun input() {
        println("Nhap so luong san pham: ")
        val n: Int = readln().toInt()
        val result = mutableListOf<Product>()
        for (i in 0..< n) {
            println("Nhap ten san pham: ")
            val name: String = readln()
            println("Nhap gia san pham: ")
            val price: Double = readln().toDouble()
            println("Nhap so luong san pham: ")
            val quantity: Int = readln().toInt()
            val product = Product(name, price, quantity)
            result += product
        }
        productList = result
    }

    fun output() {
        println("***********************")
        productList.forEach { item->
            println(item.toString())
        }
        println("***********************")
    }
}