package com.example.wallpapersapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.wallpapersapp.R
import com.example.wallpapersapp.data.models.ImageModel
import com.example.wallpapersapp.ui.adapter.ImageAdapter
import com.example.wallpapersapp.viewmodels.ImageViewModel
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import kotlinx.android.synthetic.main.activity_main.*
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    private lateinit var mImageViewModel: ImageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mImageViewModel = ViewModelProviders.of(this).get(ImageViewModel::class.java)

        val imageAdapter = ImageAdapter()

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = imageAdapter
        }

        mImageViewModel.imageData.observe(this, Observer { list: List<ImageModel> ->
            imageAdapter.updateData(list)
        })

        mImageViewModel.loadData()

    }


}
