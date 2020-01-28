package com.example.wallpapersapp.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.wallpapersapp.R
import com.example.wallpapersapp.data.models.ImageModel
import com.example.wallpapersapp.ui.adapter.ImageAdapter
import com.example.wallpapersapp.viewmodels.ImageViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ImageAdapter.OnImageClickListener {

    private lateinit var mImageViewModel: ImageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mImageViewModel = ViewModelProviders.of(this).get(ImageViewModel::class.java)

        val imageAdapter = ImageAdapter()
        imageAdapter.setListener(this)

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

    override fun onImageClick(model: ImageModel) {
        Intent(this, ImageDetailsActivity::class.java).also {
            it.putExtra("ImageModel", model)
            startActivity(it)
        }
    }


}
