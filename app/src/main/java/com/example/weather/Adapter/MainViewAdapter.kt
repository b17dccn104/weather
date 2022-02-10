package com.example.weather.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.*

class MainViewAdapter(suppoFragmentManager: FragmentManager)
    : FragmentPagerAdapter(suppoFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val arrayFragment = ArrayList<Fragment>()
    private val arraytitle = ArrayList<String>()

//    fun MainViewPagerAdapter(fm: FragmentManager) {
//        super(fm)
//    }


    override fun getItem(position: Int): Fragment {
        return arrayFragment[position]
    }

    override fun getCount(): Int {
        return arrayFragment.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        arrayFragment.add(fragment)
        arraytitle.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return arraytitle[position]
    }

}