package me.anmolverma.products.data

interface DataSource<in Input, out Output> {
    fun fetch(category: Input?): Output
}

interface AsyncDataSource<in Input, out Output> {
    suspend fun fetchAsync(category: Input?): Output
}
