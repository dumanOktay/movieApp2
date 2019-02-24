package com.duman.movieapp.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.duman.movieapp.R
import com.duman.movieapp.model.Cast
import com.duman.movieapp.view.adapters.BaseProfileAdapter.CastViewHolder
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_profile.view.*

class BaseProfileAdapter(private var mCastList: List<Cast>?) : RecyclerView.Adapter<CastViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CastViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.adapter_profile, viewGroup, false)
        return CastViewHolder(view)
    }

    override fun onBindViewHolder(castViewHolder: CastViewHolder, i: Int) {
        castViewHolder.bind(mCastList!![i])
    }


    fun updateData(castList: List<Cast>) {
        mCastList = castList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mCastList!!.size
    }

    inner class CastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(cast: Cast) = with(itemView) {
            Picasso.get().load(cast.imageUrl).memoryPolicy(MemoryPolicy.NO_CACHE).into(img)
            cast_title.text = cast.name
        }
    }

}
