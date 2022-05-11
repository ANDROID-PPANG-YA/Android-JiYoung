package com.godwpfh.myapplication.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabProfileAdpater(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    private val fragments= mutableListOf<Fragment>()

    override fun getItemCount(): Int= fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

    fun setFragments(list: List<Fragment>){
        fragments.addAll(list)
    }

}