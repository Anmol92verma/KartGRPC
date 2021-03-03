package me.anmolverma.auth

import io.grpc.CallCredentials
import io.grpc.Metadata
import io.grpc.Status
import java.util.concurrent.Executor
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.time.Instant
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * CallCredentials implementation, which carries the JWT value that will be propagated to the
 * server in the request metadata with the "Authorization" key and the "Bearer" prefix.
 */
class JwtCredential(private val subject: String) : CallCredentials() {
    override fun applyRequestMetadata(
        requestInfo: RequestInfo, executor: Executor,
        metadataApplier: MetadataApplier
    ) {
        // Make a JWT compact serialized string.
        //set one day as expiration time
        val jwt: String = Jwts.builder()
            .setSubject(subject)
            .setExpiration(Date.from(Instant.now().plusMillis(TimeUnit.DAYS.toMillis(1))))
            .signWith(SignatureAlgorithm.HS256, AuthConstants.JWT_SIGNING_KEY)
            .compact()
        executor.execute {
            try {
                val headers = Metadata()
                headers.put(
                    AuthConstants.AUTHORIZATION_METADATA_KEY,
                    java.lang.String.format("%s %s", AuthConstants.BEARER_TYPE, jwt)
                )
                metadataApplier.apply(headers)
            } catch (e: Throwable) {
                metadataApplier.fail(Status.UNAUTHENTICATED.withCause(e))
            }
        }
    }

    override fun thisUsesUnstableApi() {
        // noop
    }
}