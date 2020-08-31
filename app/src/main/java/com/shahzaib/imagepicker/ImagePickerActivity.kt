package com.shahzaib.imagepicker

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shahzaib.imagepicker.databinding.ImagePickerActivityBinding

class ImagePickerActivity: AppCompatActivity() {
    private lateinit var binding: ImagePickerActivityBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    private var imageList: MutableList<Bitmap> =  mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ImagePickerActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sampleBitmap = BitmapFactory.decodeResource(resources, R.mipmap.sample_image)

        imageList.add(sampleBitmap)
        imageList.add(sampleBitmap)


        binding.btnSubmit.setOnClickListener{
            viewAdapter = ImageRecyclerAdapter(imageList, this@ImagePickerActivity)
            recyclerView.adapter = viewAdapter
        }

        recyclerView = binding.recyclerList.apply {
            layoutManager = GridLayoutManager(this@ImagePickerActivity, 3, LinearLayoutManager.VERTICAL,false)
        }
    }
}