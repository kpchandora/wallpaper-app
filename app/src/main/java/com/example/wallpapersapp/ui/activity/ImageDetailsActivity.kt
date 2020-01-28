package com.example.wallpapersapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.wallpapersapp.R
import com.example.wallpapersapp.data.models.ImageModel
import kotlinx.android.synthetic.main.activity_image_details.*
import kotlinx.android.synthetic.main.image_list_item_layout.*
import kotlinx.android.synthetic.main.image_list_item_layout.view.*

class ImageDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_details)

        intent?.extras?.apply {
            getParcelable<ImageModel>("ImageModel")?.apply {
                if (!hdUrl.isNullOrEmpty()) {
                    RequestOptions()
                        .centerCrop()
                        .placeholder(R.drawable.ic_image_black_24dp)
                        .error(R.drawable.ic_image_black_24dp)
                        .also { options ->
                            Glide.with(this@ImageDetailsActivity).load(hdUrl)
                                .thumbnail(0.10f)
                                .apply(options)
                                .into(image_details_iv)
                        }
                }

                handleEmptyOrNullText(view = image_details_title_tv, value = title)
                handleEmptyOrNullText(view = image_details_desc_tv, value = explanation)
                handleEmptyOrNullText(view = image_details_copyright_tv, value = copyright)

            }
        }
    }

    private fun handleEmptyOrNullText(view: TextView, value: String?) {
        if (value.isNullOrEmpty()) {
            view.visibility = View.GONE
        } else {
            view.visibility = View.VISIBLE
            view.text = value
        }
    }

}
