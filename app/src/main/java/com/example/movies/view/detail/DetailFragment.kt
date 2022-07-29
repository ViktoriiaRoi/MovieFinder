package com.example.movies.view.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.Contract
import com.example.movies.databinding.FragmentDetailBinding
import com.example.movies.model.Model
import com.example.movies.model.data.Video
import com.example.movies.presenter.DetailPresenter
import com.example.movies.view.adapters.VideoAdapter

class DetailFragment : Fragment(), Contract.DetailView {

    private val args by navArgs<DetailFragmentArgs>()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private var presenter: Contract.DetailPresenter? = null
    private val adapter = VideoAdapter()

    companion object {
        private const val BASE_YOUTUBE_URL = "https://www.youtube.com/watch?v="
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.movie = args.movie

        setupRecycler()
        presenter = DetailPresenter(this, Model())

        args.movie.id?.let {
            presenter?.requestVideos(it)
        }

        return binding.root
    }

    private fun setupRecycler() {
        val recycler = binding.videoRecycler
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        adapter.onVideoClickListener = object : VideoAdapter.OnVideoClickListener {
            override fun onVideoClick(videoKey: String?) {
                val url = BASE_YOUTUBE_URL + videoKey
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            }
        }
    }

    override fun onVideoResponse(videos: List<Video>) {
        adapter.videoList = videos
    }

    override fun onFailure(t: Throwable) {
        Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter!!.onDestroy()
    }
}