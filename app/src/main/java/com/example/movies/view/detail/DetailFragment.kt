package com.example.movies.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.Contract
import com.example.movies.databinding.FragmentDetailBinding
import com.example.movies.model.Model
import com.example.movies.model.data.Actor
import com.example.movies.model.data.Genre
import com.example.movies.model.data.MovieDetails
import com.example.movies.model.data.Video
import com.example.movies.presenter.DetailPresenter
import com.example.movies.view.adapters.ActorAdapter
import com.example.movies.view.adapters.GenreAdapter
import com.example.movies.view.adapters.VideoAdapter

class DetailFragment : Fragment(), Contract.DetailView {

    private val args by navArgs<DetailFragmentArgs>()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private var presenter: Contract.DetailPresenter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.movie = args.movie
        presenter = DetailPresenter(this, Model())

        args.movie.id?.let {
            presenter?.requestMovieInfo(it)
        }
        return binding.root
    }


    private fun setupGenreRecycler(genres: List<Genre>) {
        val recycler = binding.genreRecycler
        recycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recycler.adapter = GenreAdapter(genres)
    }

    private fun setupCastRecycler(actors: List<Actor>) {
        val recycler = binding.castRecycler
        recycler.layoutManager = GridLayoutManager(requireContext(), 2)
        recycler.adapter = ActorAdapter(actors)
    }

    private fun setupVideoRecycler(videos: List<Video>) {
        val recycler = binding.videoRecycler
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = VideoAdapter(videos)
    }

    override fun onDetailsResponse(details: MovieDetails) {
        setupGenreRecycler(details.genres)
        binding.details = details
    }

    override fun onCastResponse(actors: List<Actor>) {
        setupCastRecycler(actors)
        binding.hasActors = true
    }

    override fun onVideoResponse(videos: List<Video>) {
        setupVideoRecycler(videos)
        binding.hasVideos = true
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter!!.onDestroy()
    }
}