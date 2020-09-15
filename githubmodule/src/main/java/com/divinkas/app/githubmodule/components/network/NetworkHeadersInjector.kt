package com.divinkas.app.githubmodule.components.network

import okhttp3.Interceptor
import okhttp3.Response

internal class NetworkHeadersInjector(
    private val httpCommunicationComponent: HttpCommunicationComponent
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequestBuilder = request.newBuilder()

        if (!request.headers().names().contains("Accept")) {
            newRequestBuilder.addHeader("Accept", httpCommunicationComponent.getAcceptHeader())
        }

        /**
         * Added token etc...
         */
        return chain.proceed(newRequestBuilder.build())
    }
}