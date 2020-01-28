package com.example.wallpapersapp.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.wallpapersapp.R
import com.example.wallpapersapp.data.models.ImageModel
import kotlinx.android.synthetic.main.image_list_item_layout.view.*

class ImageViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(model: ImageModel) {
        model.apply {
            if (!url.isNullOrEmpty()) {
                RequestOptions()
                    .error(R.drawable.ic_image_black_24dp)
                    .also { options ->
                        Glide.with(view).load(url)
                            .thumbnail(0.1f)
                            .apply(options)
                            .into(view.image_item_iv)
                    }
            } else {

            }
        }
    }

}