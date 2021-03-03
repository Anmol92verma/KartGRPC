package me.anmolverma

import me.anmolverma.auth.JWTInterceptor
import me.anmolverma.categories.data.InMemoryCategoryDataSource
import me.anmolverma.products.data.InMemoryProductsDataSource
import me.anmolverma.services.KartProductsService
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder
import java.io.File

object MainGrpcServer {

    const val CERT_CHAIN_FILE_PATH = "certChainFilePath"
    const val PRIVATE_KEY_FILE_PATH = "privateKeyFilePath"

    @JvmStatic
    fun main(args: Array<String>) {
        try {
            val server = NettyServerBuilder
                .forPort(8443)
                .useTransportSecurity(
                    // reference file
                    File("./src/ssl/server.crt"),
                    File("./src/ssl/server.pem")
                )
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