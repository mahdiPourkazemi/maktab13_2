package com.pourkazemi.mahdi.maktab_hw_13_2.data.model

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class PhotoDeserializer : JsonDeserializer<List<Photo>> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<Photo> {
        val photos = mutableListOf<Photo>()
        json?.asJsonObject?.getAsJsonObject("photos")
            ?.getAsJsonArray("photo")?.let {
                for (photo in it){
                    photo.asJsonObject?.let {
                        Photo(
                            it.get("id").asString,
                            it.get("title").asString,
                            it.get("url_s").asString
                        ).let { Photo -> photos.add(Photo) }
                    }
                }
        }
        return photos.toList()
    }
}

/*
responseJson?.map {
    with(it.asJsonObject) {
        Photo(
            get("id").asString,
            get("title").asString,
            get("url_s").asString
        ).let { Photo -> photos.add(Photo) }
    }*/
