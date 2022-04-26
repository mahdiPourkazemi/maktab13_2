package com.pourkazemi.mahdi.maktab_hw_13_2.data

import com.pourkazemi.mahdi.maktab_hw_13_2.data.model.Photo
import com.pourkazemi.mahdi.maktab_hw_13_2.data.remote.RemoteDataSource
import com.pourkazemi.mahdi.maktab_hw_13_2.data.remote.RemoteDataSourceIm
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSourceIm: RemoteDataSource
) {

    suspend fun getImage(query: HashMap<String, String>): Flow<List<Photo>> = flow{
        val getImage = remoteDataSourceIm.getImage(query)
        if (getImage.isSuccessful) {
            emit(getImage.body() ?: listOf())
        }
    }
}
/*    suspend fun getImage(query:HashMap<String,String>):Response<ListOfPhoto> {
       return remoteDataSourceIm.getImage(query)
    }*/