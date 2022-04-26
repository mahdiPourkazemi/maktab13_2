package com.pourkazemi.mahdi.maktab_hw_13_2.data.remote

import com.pourkazemi.mahdi.maktab_hw_13_2.data.model.Photo
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getImage(query: HashMap<String, String>): Response<ArrayList<Photo>>
}