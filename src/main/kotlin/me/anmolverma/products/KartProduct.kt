package me.anmolverma.products

import me.anmolverma.product.Category
import me.anmolverma.product.Product

data class KartProduct(val id: Int, val name: String, val category: Category, val imageUrls: List<String>)

fun KartProduct.asProducts(): Product {
    return Product.newBuilder().apply {
        this.id = this@asProducts.id
        this.name = this@asProducts.name
        this.category = this@asProducts.category
        this.addAllImageUrls(this@asProducts.imageUrls)
    }.build()
}
