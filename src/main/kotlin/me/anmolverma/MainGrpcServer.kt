package me.anmolverma

import io.grpc.ServerBuilder
import me.anmolverma.auth.JWTInterceptor
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
                ).intercept(JWTInterceptor())
                .build()

            server.start()
            println("running on ${server.port}")
            server.awaitTermination()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}