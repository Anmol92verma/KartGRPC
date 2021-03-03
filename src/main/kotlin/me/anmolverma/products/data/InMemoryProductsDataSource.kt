package me.anmolverma.products.data

import me.anmolverma.categories.asCategory
import me.anmolverma.categories.data.InMemoryCategoryDataSource
import me.anmolverma.product.Category
import me.anmolverma.products.KartProduct

class InMemoryProductsDataSource : AsyncDataSource<Category, List<KartProduct>> {
    private val products = mutableListOf<KartProduct>()
    private val categories = InMemoryCategoryDataSource().fetch(null);

    init {
        categories.forEach { category ->
            for (index in 1..10) {
                products.add(KartProduct(index, "Name $index", category.asCategory(), getImages()))
            }
        }
    }

    private fun getImages(): List<String> {
        return mutableListOf<String>().apply {
            add("https://homepages.cae.wisc.edu/~ece533/images/airplane.png")
            add("https://homepages.cae.wisc.edu/~ece533/images/arctichare.png")
            add("https://homepages.cae.wisc.edu/~ece533/images/baboon.png")
            add("https://homepages.cae.wisc.edu/~ece533/images/boat.png")
        }
    }


    override suspend fun fetchAsync(category: Category?): List<KartProduct> {
        return products.filter { it.category == category }
    }
}