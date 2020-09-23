package com.hazizah.titlelist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hazizah.titlelist.R
import kotlinx.android.synthetic.main.view_holder_title.view.*

class TitleAdapter: RecyclerView.Adapter<TitleAdapter.ViewHolder>() {
    private lateinit var titles: List<String>

    fun setTitles(titleList: List<String>) {
        titles = titleList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_title, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(titles[position])
    }

    inner class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        fun bind(title: String) {
            view.txt_title.text = title
        }
    }
}