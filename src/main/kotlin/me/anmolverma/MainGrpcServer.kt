package me.anmolverma

import io.grpc.ServerBuilder
import me.anmolverma.auth.JWTInterceptor
import me.anmolverma.categories.data.InMemoryCategoryDataSource
import me.anmolverma.products.data.InMemoryProductsDataSource
import me.anmolverma.services.KartProductsService
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder

import io.grpc.netty.shaded.io.netty.handler.ssl.ClientAuth

import io.grpc.netty.shaded.io.netty.handler.ssl.SslContextBuilder
import java.io.File


object MainGrpcServer {

    @JvmStatic
    fun main(args: Array<String>) {
        try {
            val server = NettyServerBuilder
                .forPort(8443)
                .sslContext(
                    getSslContextBuilder(
                        certChainFilePath = args[0],
                        privateKeyFilePath = args[1],
                        trustCertCollectionFilePath = args[2]
                    ).build()
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

    private fun getSslContextBuilder(
        certChainFilePath: String,
        privateKeyFilePath: String,
        trustCertCollectionFilePath: String?
    ): SslContextBuilder {
        val sslClientContextBuilder: SslContextBuilder = SslContextBuilder.forServer(
            File(certChainFilePath),
            File(privateKeyFilePath)
        )
        trustCertCollectionFilePath?.let {
            sslClientContextBuilder.trustManager(File(trustCertCollectionFilePath))
            sslClientContextBuilder.clientAuth(ClientAuth.REQUIRE)
        }
        return GrpcSslContexts.configure(sslClientContextBuilder)
    }
}