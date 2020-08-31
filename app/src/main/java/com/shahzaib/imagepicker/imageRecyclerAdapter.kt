package com.shahzaib.imagepicker

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

class ImageRecyclerAdapter(private var list: MutableList<Bitmap>, private val context: Context) :
    RecyclerView.Adapter<ImageRecyclerAdapter.CustomViewHolder>() {

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val listImage: ImageView = view.findViewById(R.id.listImage)
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_recycler_list, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(@NonNull holder: CustomViewHolder, position: Int) {
        holder.listImage.setImageBitmap(list[position])
    }

    override fun getItemCount() = list.size
}