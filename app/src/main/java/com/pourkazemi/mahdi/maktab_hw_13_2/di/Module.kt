package com.pourkazemi.mahdi.maktab_hw_13_2.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.pourkazemi.mahdi.maktab_hw_13_2.data.model.Photo
import com.pourkazemi.mahdi.maktab_hw_13_2.data.model.PhotoDeserializer
import com.pourkazemi.mahdi.maktab_hw_13_2.data.remote.FlickerAPIService
import com.pourkazemi.mahdi.maktab_hw_13_2.data.remote.RemoteDataSource
import com.pourkazemi.mahdi.maktab_hw_13_2.data.remote.RemoteDataSourceIm
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Module {
    private const val BASE_URL = "https://www.flickr.com/services/"
    @Singleton
    @Provides
    fun providesGson(): Gson = GsonBuilder().registerTypeAdapter(
        object : TypeToken<ArrayList<Photo>>(){}.type,
        PhotoDeserializer()
    ).create()

    @Singleton
    @Provides
    fun provideGsonFactory(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

    @Singleton
    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory, client: OkHttpClient
    ): Retrofit = Retrofit
        .Builder().baseUrl(BASE_URL).client(client)
        .addConverterFactory(gsonConverterFactory).build()

    @Provides
    fun provideFlickerAPIService(
        retrofit: Retrofit
    ): FlickerAPIService = retrofit
        .create(FlickerAPIService::class.java)

    @Provides
    fun provideRemoteDataSourceIm(
        flickerAPIService: FlickerAPIService
    ): RemoteDataSource =
        RemoteDataSourceIm(flickerAPIService)


    @Provides
    fun provideClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor(Interceptor {
            val mRequest = it.request().newBuilder().build()
            it.proceed(mRequest)
        }).build()
}