package com.duman.movieapp.view.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.duman.movieapp.view.activities.MovieDetailActivity

import com.duman.movieapp.R
import com.duman.movieapp.model.Movie
import com.duman.movieapp.modelview.MoviesViewModel
import com.duman.movieapp.modelview.ViewModelFactory
import com.duman.movieapp.view.adapters.BaseMovieAdapter
import kotlinx.android.synthetic.main.fragment_top_rated.*


class TopRatedFragment : BaseFragment() {


    private val baseMovieAdapter = BaseMovieAdapter()

    private val instance = ViewModelFactory.getInstance()

    private val observer = Observer<PagedList<Movie>> {
        it ?: return@Observer
        baseMovieAdapter.submitList(it)
    }

    var mMovieViewModel: MoviesViewModel? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_rated, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    override fun onSearch(str: String) {
        mMovieViewModel?.filter(str, observer)
    }

    private fun initUi() {

        top_rated_list.layoutManager = LinearLayoutManager(requireContext())

        mMovieViewModel = ViewModelProviders.of(this, instance).get(MoviesViewModel::class.java)
        val queryString = arguments?.getString(KEY_QUERY) ?: "top_rated"
        top_rated_list.adapter = baseMovieAdapter

        baseMovieAdapter.setMovieClickListener(object : BaseMovieAdapter.MovieClickListener {
            override fun onMovieClick(movie: Movie) {
                val intent = Intent(activity, MovieDetailActivity::class.java)
                intent.putExtra("movie_id", movie.id)
                activity!!.startActivity(intent)
            }
        })

        mMovieViewModel?.getItems(queryString)?.observe(this, observer)

    }

    companion object {
        const val KEY_QUERY = "key_query"
        @JvmStatic
        fun newInstance(queryString: String) =
            TopRatedFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_QUERY, queryString)
                }
            }
    }
}
