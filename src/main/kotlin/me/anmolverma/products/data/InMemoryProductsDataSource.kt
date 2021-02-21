package me.anmolverma.products.data

import me.anmolverma.product.Category
import me.anmolverma.products.KartProduct
import kotlin.random.Random

class InMemoryProductsDataSource : ProductsDataSource {
    private val products = mutableListOf<KartProduct>()

    init {
        for (index in 1..100) {
            products.add(KartProduct(index, "Name $index", randomCategory(), getImages()))
        }
    }

    private fun randomCategory(): Int {
        return Category.values()[(0..Category.values().size.minus(1)).random()].ordinal
    }

    private fun getImages(): List<String> {
        return mutableListOf<String>().apply {
            add("https://homepages.cae.wisc.edu/~ece533/images/airplane.png")
            add("https://homepages.cae.wisc.edu/~ece533/images/arctichare.png")
            add("https://homepages.cae.wisc.edu/~ece533/images/baboon.png")
            add("https://homepages.cae.wisc.edu/~ece533/images/boat.png")
        }
    }


    override suspend fun fetch(category: Category): List<KartProduct> {
        if(category == Category.UNRECOGNIZED){
            return emptyList()
        }
        return products.filter { it.category == category.number }
    }
}