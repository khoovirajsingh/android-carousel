package com.mkodo.carousel

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager

const val FIRST_PAGE = 10

class MainActivity : AppCompatActivity() {

    lateinit var pager: ViewPager
    lateinit var adapter: CarouselPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        pager = findViewById(R.id.myviewpager)

        //set page margin between pages for viewpager
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        val pageMargin = metrics.widthPixels / 4 * 2
        pager.pageMargin = -pageMargin

        adapter = CarouselPagerAdapter(supportFragmentManager, this)
        pager.adapter = adapter
        adapter.notifyDataSetChanged()

        pager.addOnPageChangeListener(adapter)

        // Set current item to the middle page so we can fling to both
        // directions left and right
        pager.currentItem = FIRST_PAGE
        pager.offscreenPageLimit = 3
    }
}
