package com.innoventus.swiggydemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.innoventus.swiggydemo.databinding.ItemMovieBinding

class MovieAdapter(
    private val list: MutableList<Search>,
    private val listener: OnItemClickListener
): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: ItemMovieBinding): RecyclerView.ViewHolder(itemView.root) {
        var binding = itemView
        init {
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binding.setVariable(BR.item, list[position])
        holder.binding.setVariable(BR.listener, listener)
    }


    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Search>() {
            override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
                return oldItem.imdbID == newItem.imdbID
            }

            override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
                return oldItem.Title == newItem.Title || oldItem.Year == newItem.Year
            }

        }
    }
}