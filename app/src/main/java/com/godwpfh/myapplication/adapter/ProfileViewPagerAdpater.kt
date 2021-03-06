package com.godwpfh.myapplication.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ProfileViewPagerAdpater(fragmentActivity: FragmentActivity)
    :FragmentStateAdapter(fragmentActivity){

    private val fragments= mutableListOf<Fragment>()

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

    fun setFragments(list: List<Fragment>){
        fragments.addAll(list)
    }

}