package com.pourkazemi.mahdi.maktab_hw_13_2.data.remote

import com.pourkazemi.mahdi.maktab_hw_13_2.data.model.Photo
import retrofit2.Response
import javax.inject.Singleton

@Singleton
class RemoteDataSourceIm(private val flickerAPIService: FlickerAPIService) : RemoteDataSource {
    override suspend fun getImage(
        query: HashMap<String, String>
    ):Response<ArrayList<Photo>> {
        return flickerAPIService.getImage(query)
    }
}
