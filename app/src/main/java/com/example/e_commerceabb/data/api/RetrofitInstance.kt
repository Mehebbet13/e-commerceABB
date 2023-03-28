package com.example.e_commerceabb.data.api

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://mobile.test-danit.com/api/"

@InstallIn(SingletonComponent::class)
@Module
class RetrofitInstance {

    @Singleton
    @Provides
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
    }

    @Singleton
    @Provides
    fun getCustomerAPI(retrofit: Retrofit): CustomerApi {
        return retrofit.create(CustomerApi::class.java)
    }

    @Singleton
    @Provides
    fun getOrdersAPI(retrofit: Retrofit): OrdersApi {
        return retrofit.create(OrdersApi::class.java)
    }

    @Singleton
    @Provides
    fun getFavoritesAPI(retrofit: Retrofit): FavoritesApi {
        return retrofit.create(FavoritesApi::class.java)
    }

    @Singleton
    @Provides
    fun getCartProductAPI(retrofit: Retrofit): CartProductApi {
        return retrofit.create(CartProductApi::class.java)
    }

    @Provides
    @Singleton
    fun providesOKHttpClient(rcInterceptor: RetroClientInterceptor): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(rcInterceptor)
            .addInterceptor(interceptor)
            .build()
    }
}
