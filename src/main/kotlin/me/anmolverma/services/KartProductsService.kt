package me.anmolverma.services

import me.anmolverma.product.ProductFetchRequest
import me.anmolverma.product.ProductServiceGrpcKt
import me.anmolverma.product.ProductsResponse
import me.anmolverma.products.asProducts
import me.anmolverma.products.data.ProductsDataSource

class KartProductsService(private val dataSource: ProductsDataSource) :
    ProductServiceGrpcKt.ProductServiceCoroutineImplBase() {
    override suspend fun fetchProducts(request: ProductFetchRequest): ProductsResponse {
        val kartProducts = dataSource.fetch(category = request.category)
        return if (kartProducts.isEmpty()) {
            ProductsResponse.newBuilder()
                .setCode(201)
                .setMessage("Found nothing!").build()
        } else {
            val products = kartProducts.map { it.asProducts() }
            ProductsResponse.newBuilder()
                .setCode(200)
                .setMessage("Here you go!")
                .addAllProducts(products).build()
        }
    }
}
