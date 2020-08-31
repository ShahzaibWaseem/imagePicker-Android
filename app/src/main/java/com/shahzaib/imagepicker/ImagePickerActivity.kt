package com.shahzaib.imagepicker

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
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

        binding.layoutCamera.setOnClickListener{
            viewAdapter = ImageRecyclerAdapter(imageList, this@ImagePickerActivity)
            openCameraIntent()
        }

        binding.layoutGallery.setOnClickListener{
            viewAdapter = ImageRecyclerAdapter(imageList, this@ImagePickerActivity)
            openGalleryIntent()
        }

        recyclerView = binding.recyclerList.apply {
            layoutManager = GridLayoutManager(this@ImagePickerActivity,3,
                LinearLayoutManager.VERTICAL,false)
        }
    }

    private fun openCameraIntent() {
        val pictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (pictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(pictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    private fun openGalleryIntent() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        if (galleryIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(galleryIntent, REQUEST_GALLERY_IMAGES)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (data != null && data.extras != null) {
                val imageBitmap: Bitmap = data.extras!!.get("data") as Bitmap
                imageList.add(imageBitmap)
                recyclerView.adapter = viewAdapter
            }
        }

        if (requestCode == REQUEST_GALLERY_IMAGES && resultCode == RESULT_OK) {
            if (data != null) {
                val imageUri: Uri? = data.data
                val imageBitmap= MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
                imageList.add(imageBitmap)
                recyclerView.adapter = viewAdapter
            }
        }
    }

    companion object {
        const val REQUEST_IMAGE_CAPTURE = 1
        const val REQUEST_GALLERY_IMAGES = 2
        const val REQUEST_FILE_IMAGES = 3
    }
}