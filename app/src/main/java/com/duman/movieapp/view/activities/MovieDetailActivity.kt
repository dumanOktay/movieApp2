package com.duman.movieapp.view.activities

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.appbar.AppBarLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import com.duman.movieapp.R
import com.duman.movieapp.modelview.MoviesDetailViewModel
import com.duman.movieapp.modelview.ViewModelFactory
import com.duman.movieapp.utils.loadImage
import com.duman.movieapp.view.adapters.BaseProfileAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.content_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        setSupportActionBar(toolbar)
        back_icon.setOnClickListener {
            finish()
        }

        val movieId = intent.getIntExtra("movie_id", 0)
        setTitle("")



        fav_button.setOnClickListener {

            it.isActivated = !it.isActivated

        }
        val profileAdapter = BaseProfileAdapter(listOf())
        credits_list.adapter = profileAdapter
        val moviesDetailViewModel =
            ViewModelProviders.of(this, ViewModelFactory.getInstance()).get(MoviesDetailViewModel::class.java)?.apply {

                videoInfoLiveData.observe(this@MovieDetailActivity, Observer { url ->
                    url ?: fab.hide()
                    fab.setOnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        startActivity(intent)
                    }
                })

                movieDetailLiveData.observe(this@MovieDetailActivity, Observer {
                    it ?: return@Observer
                    this@MovieDetailActivity.setTitle("" + it.title)
                    over_view.text = it.overview

                    top_image.loadImage(it.backdropPath)
                    poster_img.loadImage(it.posterPath)
                })

                creditsLiveData.observe(this@MovieDetailActivity, Observer {
                    it?.cast?.let { list ->
                        profileAdapter.updateData(list)
                    }

                })
            }

        val posterContainer = poster_img.getParent() as CardView
        app_bar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                val v = (scrollRange + verticalOffset).toFloat()
                val x = v / scrollRange
                posterContainer.animate().scaleX(x).start()
                posterContainer.animate().scaleY(x).start()
            }
        })

        moviesDetailViewModel.getMoviesDetail("" + movieId)

        credits_list.layoutManager =
            LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
            )


    }
}
