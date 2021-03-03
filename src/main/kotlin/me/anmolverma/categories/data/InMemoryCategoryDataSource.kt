package me.anmolverma.categories.data

import me.anmolverma.categories.KartCategory
import me.anmolverma.products.data.DataSource

class InMemoryCategoryDataSource : DataSource<Unit, List<KartCategory>> {
    private val categories = mutableListOf<KartCategory>().apply {
        add(KartCategory(1, "One"))
        add(KartCategory(2, "Two"))
        add(KartCategory(3, "Three"))
        add(KartCategory(4, "Four"))
        add(KartCategory(5, "Five"))
        add(KartCategory(6, "Six"))
        add(KartCategory(7, "Seven"))
    }

    override fun fetch(nothing: Unit?): List<KartCategory> {
        return categories
    }
}