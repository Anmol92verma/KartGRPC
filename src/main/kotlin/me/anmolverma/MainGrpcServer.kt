package me.anmolverma

import io.grpc.ServerBuilder
import me.anmolverma.categories.data.InMemoryCategoryDataSource
import me.anmolverma.products.data.InMemoryProductsDataSource
import me.anmolverma.services.KartProductsService

object MainGrpcServer {
    @JvmStatic
    fun main(args: Array<String>) {
        try {
            val server = ServerBuilder
                .forPort(8443)
                .addService(
                    KartProductsService(
                        InMemoryProductsDataSource(),
                        InMemoryCategoryDataSource()
                    )
                )
                .build()

            server.start()
            server.awaitTermination()
            println("running on ${server.port}")
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}