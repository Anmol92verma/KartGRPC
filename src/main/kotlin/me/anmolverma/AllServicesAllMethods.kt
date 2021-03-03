package me.anmolverma

import com.google.protobuf.Empty
import io.grpc.CallCredentials
import io.grpc.ManagedChannel
import me.anmolverma.product.ProductServiceGrpc.ProductServiceBlockingStub
import io.grpc.ManagedChannelBuilder
import me.anmolverma.product.ProductFetchRequest
import kotlin.Throws
import java.lang.InterruptedException
import java.util.concurrent.TimeUnit
import me.anmolverma.product.ProductServiceGrpc
import java.util.logging.Logger

/**
 * An authenticating client that requests a product from the [MainGrpcServer].
 */
class AllServicesAllMethods internal constructor(
    private val callCredentials: CallCredentials?,
    private val channel: ManagedChannel
) {
    private val productServiceBlockingStub: ProductServiceBlockingStub = ProductServiceGrpc.newBlockingStub(channel)
    /**
     * Construct client for accessing GreeterGrpc server.
     */
    internal constructor(callCredentials: CallCredentials?, host: String?, port: Int) : this(
        callCredentials,
        ManagedChannelBuilder
            .forAddress(host, port) // Channels are secure by default (via SSL/TLS). For this example we disable TLS
            // to avoid needing certificates, but it is recommended to use a secure channel
            // while passing credentials.
            .usePlaintext()
            .build()
    ) {
    }

    @Throws(InterruptedException::class)
    fun shutdown() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }

    /**
     * Say hello to server.
     *
     * @return the message in the HelloReply from the server
     */
    fun requestCategories(logger: Logger): String {
        logger.info("Will try to fetch categories")
        // Use a stub with the given call credentials applied to invoke the RPC.
        val response = productServiceBlockingStub
            .withCallCredentials(callCredentials)
            .fetchCategories(Empty.newBuilder().build())
        logger.info("Greeting: " + response.message)
        logger.info("Greeting: " + response.categoriesCount)
        response.categoriesList.forEach {
            logger.info(it.toString())
        }
        return response.message
    }

    fun requestProducts(logger: Logger): String? {
        logger.info("Will try to fetch products")
        // Use a stub with the given call credentials applied to invoke the RPC.
        val response = productServiceBlockingStub
            .withCallCredentials(callCredentials)
            .fetchProducts(ProductFetchRequest.newBuilder().build())
        logger.info("Greeting: " + response.message)
        logger.info("Greeting: " + response.productsCount)

        response.productsList.forEach {
            logger.info(it.toString())
        }
        return response.message
    }

}