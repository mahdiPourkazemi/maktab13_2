package com.pourkazemi.mahdi.maktab_hw_13_2

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pourkazemi.mahdi.maktab_hw_13_2.data.Repository
import com.pourkazemi.mahdi.maktab_hw_13_2.data.model.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {


    private val _pictureListState:
            MutableStateFlow<List<Photo>> = MutableStateFlow(listOf())
    val pictureListState get() = _pictureListState.asStateFlow()

    init {
        getFlickerList()

    }

    val myHashMap = hashMapOf(
        "api_key" to "1c04e05bce6e626247758d120b372a73",
        "method" to "flickr.photos.getRecent",
        "extras" to "url_s",
        "format" to "json",
        "nojsoncallback" to "1",
        "per_page" to "30",
        "page" to "1"
    )

    fun getFlickerList() {
        viewModelScope.launch {
            Log.d("mahdiTest", "viewModelScope")
            val mResponse = repository.getImage(myHashMap)
            mResponse.collect {
                _pictureListState.emit(it)
                Log.d("mahdiTest", it.toString())
            }
            //Log.d("mahdiTest", mResponse.body().toString())
            Log.d("mahdiTest", "yyyyoooohhhhooooooo")
        }
    }
}