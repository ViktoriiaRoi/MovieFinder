package com.example.movies.view.adapters

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movies.model.data.Sorting
import com.example.movies.view.main.ListFragment

class ViewAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = Sorting.values().size

    override fun createFragment(position: Int): Fragment {
        return ListFragment().apply {
            arguments = bundleOf(
                "position" to position
            )
        }
    }
}