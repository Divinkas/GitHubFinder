package com.divinkas.app.githubmodule.arch.network

import com.divinkas.app.githubmodule.bean.BackendError
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.Type

class NetworkCallAdapter(private val gson: Gson): CallAdapter.Factory() {
    companion object {
        private const val HTTP_LOGICAL_ERROR: Int = 400
        private const val HTTP_NOT_FOUND: Int = 404
    }

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *> {
        return object : CallAdapter<Any?, Any?> {
            override fun responseType(): Type {
                return returnType
            }

            override fun adapt(call: Call<Any?>): Any {
                try {
                    val response = call.execute()
                    val responseBody = response.body()

                    if (response.isSuccessful) return responseBody ?: Unit

                    throw try {
                        val errorBody = response.errorBody()!!.string()

                        when {
                            response.code() == HTTP_LOGICAL_ERROR -> {
                                gson.fromJson(errorBody, BackendError.Logical::class.java)
                            }
                            response.code() == HTTP_NOT_FOUND -> {
                                BackendError.NotFound(details = errorBody)
                            }
                            else -> {
                                BackendError.Unknown(details = errorBody)
                            }
                        }
                    } catch (e: IOException) {
                        throw BackendError.System(details = e.message ?: "")
                    } catch (e: JsonSyntaxException) {
                        throw BackendError.System(details = e.message ?: "")
                    }
                } catch (e: IOException) {
                    throw BackendError.System(details = e.message ?: "")
                }
            }
        }
    }
}