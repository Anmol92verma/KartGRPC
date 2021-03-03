package me.anmolverma

import me.anmolverma.auth.JWTInterceptor
import me.anmolverma.categories.data.InMemoryCategoryDataSource
import me.anmolverma.products.data.InMemoryProductsDataSource
import me.anmolverma.services.KartProductsService
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder
import java.io.File


object MainGrpcServer {

    @JvmStatic
    fun main(args: Array<String>) {
        try {
            val server = NettyServerBuilder
                .forPort(8443)
                .useTransportSecurity(File(System.getenv("certChainFilePath")),
                    File( System.getenv("privateKeyFilePath")))
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