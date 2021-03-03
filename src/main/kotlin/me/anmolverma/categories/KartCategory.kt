package me.anmolverma.categories

import me.anmolverma.product.Category

data class KartCategory(val id: Int, val name: String)

fun KartCategory.asCategory(): Category {
    return Category.newBuilder().apply {
        this.id = this@asCategory.id
        this.name = this@asCategory.name
    }.build()
}