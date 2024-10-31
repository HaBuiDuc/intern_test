package question1

data class Product(
    val name: String,
    val price: Double,
    val quantity: Int
) {
    override fun toString(): String {
        return "$name: price $price, quantity $quantity"
    }
}