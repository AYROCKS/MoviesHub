package com.example.moiveshub.ym_ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moiveshub.R
import com.example.moiveshub.databinding.MoviesListBinding
import com.example.moiveshub.my_models.Result
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.Locale

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MyAdapter>() {

    private val diffUtil = object : DiffUtil.ItemCallback<Result>() {

        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

    }

    var differ = AsyncListDiffer(this, diffUtil) //Isko Call Karo MainActivity Mai

    inner class MyAdapter(val binding: MoviesListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter {
        val binding = MoviesListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyAdapter(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MyAdapter, position: Int) {

        val pos = holder.adapterPosition
        val modelClass = differ.currentList[pos]

        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/original/" + modelClass.poster_path)
            .into(holder.binding.Rimage)

        holder.binding.Rtitle.text = modelClass.title

        holder.itemView.setOnClickListener {
            val dialog = BottomSheetDialog(holder.itemView.context)
            dialog.setCanceledOnTouchOutside(false)
            dialog.setContentView(R.layout.bottom_sheet)

            val textView = dialog.findViewById<TextView>(R.id.textView5)
            val imageView = dialog.findViewById<ImageView>(R.id.imageView)
            val rating = dialog.findViewById<TextView>(R.id.BvotingAverage)
            val releaseDate = dialog.findViewById<TextView>(R.id.BreleaseDate)
            val overview = dialog.findViewById<TextView>(R.id.Boverview)
            val language = dialog.findViewById<TextView>(R.id.Blanguage)

            textView?.text = modelClass.title
            rating?.text =  modelClass.vote_average.toString()
            releaseDate?.text = modelClass.release_date
            overview?.text = modelClass.overview


            val local = Locale(modelClass.original_language)
            language?.text = local.displayLanguage


            Glide.with(holder.itemView.context)
                .load("https://image.tmdb.org/t/p/original/" + modelClass.poster_path)
                .into(imageView!!)

            dialog.show()

        }


    }

}




