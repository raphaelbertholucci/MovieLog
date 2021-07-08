package com.bertholucci.data

import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit

const val apiKey = "api_key"
const val CONN_TIMEOUT_SEC = 60L

val apiModule = module {
    single {
        HttpLoggingInterceptor().apply { this.level = HttpLoggingInterceptor.Level.BODY }
    }

    single<Interceptor> {
        object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val httpUrl = original.url.newBuilder()
                    .addQueryParameter(apiKey, BuildConfig.API_KEY).build()
                val request = original.newBuilder()
                    .header("Cache-Control", "public, max-stale=" + 60 * 60 * 24)
                    .url(httpUrl)
                    .build()
                return chain.proceed(request)
            }
        }
    }

    single {
        val spec = ConnectionSpec
            .Builder(ConnectionSpec.COMPATIBLE_TLS)
            .allEnabledCipherSuites()
            .build()

        val cacheSize = (5 * 1024 * 1024).toLong()
        val cache = Cache(androidContext().cacheDir, cacheSize)

        OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor(get<Interceptor>())
            .connectionSpecs(Collections.singletonList(spec))
            .connectTimeout(CONN_TIMEOUT_SEC, TimeUnit.SECONDS)
            .readTimeout(CONN_TIMEOUT_SEC, TimeUnit.SECONDS)
            .writeTimeout(CONN_TIMEOUT_SEC, TimeUnit.SECONDS)
            .connectionSpecs(listOf(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT))
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get<OkHttpClient>())
            .build()
    }

    single { get<Retrofit>().create(MovieLogApi::class.java) }
}