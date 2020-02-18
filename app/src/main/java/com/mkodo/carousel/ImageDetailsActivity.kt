package com.mkodo.carousel

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity


class ImageDetailsActivity(): AppCompatActivity() {
    private var imageView: ImageView? = null
    private var button: Button? = null
    private val DRAWABLE_RESOURE = "resource"

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_image)
        imageView = findViewById(R.id.img)
        button = findViewById(R.id.btnClose)
        val drawbleResource = intent.getIntExtra(DRAWABLE_RESOURE, 0)
        imageView?.setImageResource(drawbleResource)
        button?.setOnClickListener {finish()}
    }

    override fun onBackPressed() {
        finish()
    }
}
