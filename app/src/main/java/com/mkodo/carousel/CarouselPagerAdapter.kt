package com.mkodo.carousel

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager


const val BIG_SCALE = 1.0f
const val SMALL_SCALE = 0.7f

class CarouselPagerAdapter(private val fragmentManager: FragmentManager, private val mainActivity: MainActivity) :
    FragmentPagerAdapter(fragmentManager), ViewPager.OnPageChangeListener {

    private val diffScale = BIG_SCALE - SMALL_SCALE
    private var scale = 0f

    override fun getItem(position: Int): Fragment {
        scale = if (position == FIRST_PAGE) BIG_SCALE else SMALL_SCALE
        val newPosition = position % 6

        return ItemFragment.newInstance(mainActivity, newPosition, scale)!!
    }

    override fun getCount(): Int {
        return 6000
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

        if (positionOffset in 0f..1f) {
            val cur: CarouselLinearLayout? = getRootView(position)
            val next: CarouselLinearLayout? = getRootView(position + 1)
            cur?.setScaleBoth(BIG_SCALE - diffScale * positionOffset)
            next?.setScaleBoth(SMALL_SCALE + diffScale * positionOffset)
        }

    }

    private fun getRootView(position: Int): CarouselLinearLayout? {
        return fragmentManager.findFragmentByTag(getFragmentTag(position))
            ?.view?.findViewById(R.id.root_container)
    }

    private fun getFragmentTag(position: Int): String? {
        return "android:switcher:" + mainActivity.pager.getId().toString() + ":" + position
    }

    override fun onPageSelected(position: Int) {
    }
}
