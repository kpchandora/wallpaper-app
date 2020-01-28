package com.example.wallpapersapp.viewmodels

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.wallpapersapp.data.models.ImageModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import java.io.InputStreamReader

/**
 * A ViewModel class which contains the logic of loading the data from
 * assets folder.
 *
 * @param context Takes an [Application] constructor param.
 */

class ImageViewModel(private val context: Application) : AndroidViewModel(context) {

    /**
     * This field is a LiveData which notifies whenever data is changed. The corresponding
     * observers will get notified on data changes.
     * Stores a [List] of [ImageModel]
     */
    val imageData = MutableLiveData<List<ImageModel>>()


    /**
     * This method gets the json from assets folder and converts it into [List]
     * The loading of json is done in background thread using [AsyncTask]
     */
    fun loadData() {
        AsyncTask.execute {
            try {
                val jsonReader: JsonReader? =
                    JsonReader(InputStreamReader(context.assets.open("data.json")))

                jsonReader?.apply {
                    val imageList = Gson().fromJson<List<ImageModel>>(
                        this,
                        object : TypeToken<List<ImageModel>>() {}.type
                    )
                    imageList?.apply {
                        imageData.postValue(this)
                        return@execute
                    }
                }

                imageData.postValue(emptyList())

            } catch (e: Exception) {
                e.printStackTrace()
                imageData.postValue(emptyList())
            }
        }
    }

}