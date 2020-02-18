package com.mkodo.carousel

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment


const val POSITON = "position"
const val SCALE = "scale"
const val DRAWABLE_RESOURE = "resource"

class ItemFragment : Fragment() {
    private var screenWidth = 0
    private var screenHeight = 0

    private val imageArray = intArrayOf(
        R.drawable.bentley, R.drawable.honda_supercar,
        R.drawable.mclaren, R.drawable.mercedes_jeep, R.drawable.mercedes_coup,
        R.drawable.mercedes_jeep
    )

    companion object {
        fun newInstance(context: Context, pos: Int, scale: Float): Fragment? {
            val b = Bundle()
            b.putInt(POSITON, pos)
            b.putFloat(SCALE, scale)
            return instantiate(context, ItemFragment::class.java.name, b)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWidthAndHeight()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (container == null) {
            return null
        }

        val postion = this.arguments!!.getInt(POSITON)
        val scale = this.arguments!!.getFloat(SCALE)
        val layoutParams = LinearLayout.LayoutParams(screenWidth / 2, screenHeight / 2)
        val linearLayout = inflater.inflate(R.layout.fragment_image, container, false) as LinearLayout
        val textView = linearLayout.findViewById<View>(R.id.text) as TextView
        val root = linearLayout.findViewById<View>(R.id.root_container) as CarouselLinearLayout
        val imageView: ImageView = linearLayout.findViewById<View>(R.id.pagerImg) as ImageView

        textView.text = "Carousel item: $postion"
        imageView.layoutParams = layoutParams
        imageView.setImageResource(imageArray[postion])

        imageView.setOnClickListener {
            val intent = Intent(activity, ImageDetailsActivity::class.java)
            intent.putExtra(DRAWABLE_RESOURE, imageArray[postion])
            startActivity(intent)
        }

        root.setScaleBoth(scale)
        return linearLayout
    }

    private fun getWidthAndHeight() {
        val displaymetrics = DisplayMetrics()
        activity!!.windowManager.defaultDisplay.getMetrics(displaymetrics)
        screenHeight = displaymetrics.heightPixels
        screenWidth = displaymetrics.widthPixels
    }
}
