package com.example.wallpapersapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpapersapp.R
import com.example.wallpapersapp.data.models.ImageModel
import com.example.wallpapersapp.ui.viewholder.ImageViewHolder

class ImageAdapter : RecyclerView.Adapter<ImageViewHolder>() {

    private val imageList = ArrayList<ImageModel>()
    private var imageClickListener: OnImageClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_list_item_layout, parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount() = imageList.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(imageList[position])
        holder.view.setOnClickListener {
            imageClickListener?.onImageClick(imageList[position])
        }
    }

    fun updateData(list: List<ImageModel>) {
        imageList.clear()
        imageList.addAll(list)
        notifyDataSetChanged()
    }

    fun setListener(listener: OnImageClickListener) {
        imageClickListener = listener
    }

    interface OnImageClickListener {
        fun onImageClick(model: ImageModel)
    }

}