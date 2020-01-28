package com.example.wallpapersapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.wallpapersapp.R
import com.example.wallpapersapp.data.models.ImageModel
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }


}
