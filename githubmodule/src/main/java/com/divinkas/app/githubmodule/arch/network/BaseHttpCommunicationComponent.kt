package com.divinkas.app.githubmodule.arch.network

import androidx.databinding.library.BuildConfig
import com.divinkas.app.githubmodule.bean.BackendError
import com.divinkas.app.githubmodule.bean.ServerError
import com.divinkas.app.githubmodule.bean.ServerResult
import com.divinkas.app.githubmodule.configuration.ConfigUtils
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

class BaseHttpCommunicationComponent(
    private val configUtils: ConfigUtils
) : HttpCommunicationComponent {
    private val gsonProvider = Gson()

    companion object {
        private const val ACCEPT: String = "application/vnd.github.mercy-preview+json"
    }

    private val okHttpLoggingInterceptor =
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message: String? ->
            Timber.tag("OkHttp: ")
            Timber.d(message)
        }).apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(NetworkHeadersInjector(httpCommunicationComponent = this))
        .addInterceptor(okHttpLoggingInterceptor)
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(configUtils.apiLink)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gsonProvider))
        .addCallAdapterFactory(NetworkCallAdapter(gsonProvider))
        .build()

    override fun <T> createService(clazz: Class<T>): T = retrofit.create(clazz)

    @Suppress("UNCHECKED_CAST")
    override suspend fun <T> execute(request: suspend () -> Any?): ServerResult<T> {
        val result = runCatching { request() }

        return when {
            result.isSuccess -> ServerResult.Success(result.getOrThrow() as T)
            result.isFailure -> when (val error = result.exceptionOrNull()) {
                is BackendError -> {
                    if (error is BackendError.NotFound)
                        ServerResult.Error(ServerError.Backend())
                    else
                        ServerResult.Error(ServerError.Unknown())
                }
                else -> ServerResult.Error(ServerError.Unknown())
            }
            else -> ServerResult.Error(ServerError.Unknown())
        }
    }

    override fun getAcceptHeader(): String = ACCEPT
}