package com.carlosscotus.wecasts.presentation.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapter(
    context: FragmentActivity,
    private val fragments: List<Fragment>
) : FragmentStateAdapter(context){
    override fun getItemCount() = fragments.size
    override fun createFragment(position: Int) = fragments[position]
}