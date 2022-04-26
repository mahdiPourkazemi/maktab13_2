package com.pourkazemi.mahdi.maktab_hw_13_2.data.remote

import com.pourkazemi.mahdi.maktab_hw_13_2.data.model.Photo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FlickerAPIService {
    @GET("rest")
    suspend fun getImage(
        @QueryMap query: HashMap<String, String>
    ): Response<ArrayList<Photo>>
}
/*

private val client = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    })
*/
/*    .addInterceptor(Interceptor { chain ->
        val request = chain.request().newBuilder().build()
        chain.proceed(request)
    })*//*


    .build()
private const val base_url = "https://www.flickr.com/services/"
private val retrofit = Retrofit.Builder()
    .baseUrl(base_url)
    //.client(client)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object FlickerRetrofit {
    val retrofitService = retrofit.create(FlickerService::class.java)
}*/
