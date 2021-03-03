package me.anmolverma.services

import com.google.protobuf.Empty
import me.anmolverma.categories.KartCategory
import me.anmolverma.categories.asCategory
import me.anmolverma.product.*
import me.anmolverma.products.KartProduct
import me.anmolverma.products.asProducts
import me.anmolverma.products.data.AsyncDataSource
import me.anmolverma.products.data.DataSource

class KartProductsService(
    private val productsDataSource: AsyncDataSource<Category, List<KartProduct>>,
    private val categoryDataSource: DataSource<Unit, List<KartCategory>>
) :
    ProductServiceGrpcKt.ProductServiceCoroutineImplBase() {

    override suspend fun fetchCategories(request: Empty): CategoryResponse {
        val categories = categoryDataSource.fetch(null)
        return CategoryResponse.newBuilder()
            .setCode(200).setMessage("Categories on board!")
            .addAllCategories(categories.map { it.asCategory() }).build()
    }

    override suspend fun fetchProducts(request: ProductFetchRequest): ProductsResponse {
        val kartProducts = productsDataSource.fetchAsync(category = request.category)
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
