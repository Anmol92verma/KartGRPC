package me.anmolverma.products.data

import me.anmolverma.product.Category
import me.anmolverma.products.KartProduct

interface ProductsDataSource {
    suspend fun fetch(category: Category) : List<KartProduct>
}
