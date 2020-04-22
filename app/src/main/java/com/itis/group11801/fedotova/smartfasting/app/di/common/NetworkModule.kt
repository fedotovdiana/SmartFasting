package com.itis.group11801.fedotova.smartfasting.app.di.common

import android.app.Application
import com.itis.group11801.fedotova.smartfasting.BuildConfig
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.app.features.diets.data.network.DietService
import com.itis.group11801.fedotova.smartfasting.app.features.diets.data.network.DietServiceImpl
import com.itis.group11801.fedotova.smartfasting.app.features.news.data.network.NewsApiService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class NetworkModule {

    @Provides
    @AppScope
    fun provideOkHttpClient(
        @Named(TAG_AUTH) authInterceptor: Interceptor,
        @Named(TAG_LOGGING) loggingInterceptor: Interceptor,
        cache: Cache
    ): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .cache(cache)
            .build()
    }

    @Provides
    @AppScope
    fun provideRetrofit(
        client: OkHttpClient,
        converterFactory: GsonConverterFactory,
        @Named(TAG_BASE_URL) url: String
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(url)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    @AppScope
    fun provideService(retrofit: Retrofit): NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }

    @Provides
    @AppScope
    fun provideDietPlanService(dietPlansServiceImpl: DietServiceImpl): DietService =
        dietPlansServiceImpl


    @Provides
    @AppScope
    @Named(TAG_AUTH)
    fun provideAuthInterceptor(
        @Named(TAG_API_KEY) apiKey: String
    ): Interceptor = Interceptor { chain ->
        val newUrl = chain
            .request()
            .url
            .newBuilder()
            .addQueryParameter(API_KEY, apiKey)
            .build()

        val newRequest = chain.request().newBuilder().url(newUrl).build()
        chain.proceed(newRequest)
    }

    @Provides
    @AppScope
    @Named(TAG_LOGGING)
    fun provideLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @AppScope
    fun provideConvertFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @AppScope
    fun provideHttpCache(app: Application): Cache {
        val cacheSize: Long = 10 * 1024 * 1024
        return Cache(app.cacheDir, cacheSize)
    }

    @Provides
    @AppScope
    @Named(TAG_BASE_URL)
    fun provideBaseURL(): String = BuildConfig.API_ENDPOINT

    @Provides
    @AppScope
    @Named(TAG_API_KEY)
    fun provideApyKey(): String = BuildConfig.API_KEY

    companion object {
        private const val TAG_LOGGING = "tag_logging"
        private const val TAG_AUTH = "tag_auth"
        private const val TAG_API_KEY = "tag_apy_key"
        private const val TAG_BASE_URL = "tag_base_url"
        private const val API_KEY = "apiKey"
    }
}
