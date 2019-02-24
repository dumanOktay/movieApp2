package com.duman.movieapp.view.activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.Gravity
import com.duman.movieapp.R
import com.duman.movieapp.view.fragments.BaseFragment
import com.duman.movieapp.view.fragments.TopRatedFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {

                title = getString(R.string.top_rated)
                replaceFragment(TopRatedFragment.newInstance("top_rated"))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                title = getString(R.string.now_playing)
                replaceFragment(TopRatedFragment.newInstance("now_playing"))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                title = getString(R.string.popular)
                replaceFragment(TopRatedFragment.newInstance("popular"))
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    var mCurrentFargment:BaseFragment?=null
    private fun replaceFragment(topRatedFragment: BaseFragment) {
        mCurrentFargment= topRatedFragment
        supportFragmentManager.beginTransaction().replace(R.id.fr_container, topRatedFragment).commitAllowingStateLoss()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        search_ed.layoutParams = Toolbar.LayoutParams(Gravity.END)
        title = getString(R.string.top_rated)
        replaceFragment(TopRatedFragment.newInstance("top_rated"))
        search_ed.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                p0?.let { mCurrentFargment?.onSearch(it) }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                p0?.let { mCurrentFargment?.onSearch(it) }
                return true
            }


        })
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
