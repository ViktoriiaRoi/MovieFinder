package com.example.movies.view.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movies.view.main.ListFragment

class ViewAdapter(fragment: Fragment)  : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = ListFragment()
        return fragment
    }
}