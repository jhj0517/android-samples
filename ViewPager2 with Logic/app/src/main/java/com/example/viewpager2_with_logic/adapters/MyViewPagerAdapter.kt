package com.example.viewpager2_with_logic.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import com.example.viewpager2_with_logic.views.FirstFragment
import com.example.viewpager2_with_logic.views.SecondFragment


const val FRAGMENT_A_INDEX = 0
const val FRAGMENT_B_INDEX = 1
class MyViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    private val fragmentsMap: Map<Int, Fragment> = mapOf(
        FRAGMENT_A_INDEX to FirstFragment(),
        FRAGMENT_B_INDEX to SecondFragment(),
    )

    override fun onBindViewHolder(
        holder: FragmentViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
    }

    override fun getItemCount() = fragmentsMap.size

    override fun createFragment(position: Int): Fragment {
        return fragmentsMap[position]
            ?: throw IndexOutOfBoundsException("invalid Position")
    }
}