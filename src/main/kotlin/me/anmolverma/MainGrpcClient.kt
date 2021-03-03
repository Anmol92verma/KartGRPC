package me.anmolverma

import io.grpc.CallCredentials
import me.anmolverma.auth.JwtCredential
import java.lang.Exception
import java.util.logging.Logger

object MainGrpcClient {
    private val logger = Logger.getLogger(AllServicesAllMethods::class.java.name)

    /**
     * Greet server. If provided, the first element of `args` is the name to use in the greeting
     * and the second is the client identifier to set in JWT
     */
    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val host = "localhost"
        val port = 8443
        val clientId = "default-client"
        val credentials: CallCredentials = JwtCredential(clientId)
        val client = AllServicesAllMethods(credentials, host, port)
        try {
            client.requestCategories(logger)
            client.requestProducts(logger)
        } finally {
            client.shutdown()
        }
    }
}