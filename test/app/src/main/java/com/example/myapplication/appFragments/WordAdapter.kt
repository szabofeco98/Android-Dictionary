package com.example.myapplication.appFragments

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.database.model.Word
import com.example.myapplication.databinding.WordListBinding

class WordAdapter : ListAdapter<Word,WordAdapter.ViewHolder>(WordDiffCallBack()) {

    var words =  listOf<Word>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return words.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder  private constructor(val binding: WordListBinding) : RecyclerView.ViewHolder(binding.root){
        val hun: TextView = itemView.findViewById(R.id.hun)
        val eng: TextView = itemView.findViewById(R.id.eng)

        fun bind(item: Word) {
            Log.e("e",item.hun)
           // hun.text =item.hun
           // eng.text = item.eng

            binding.word=item
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding=
                    WordListBinding.inflate(layoutInflater,parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class WordDiffCallBack : DiffUtil.ItemCallback<Word>(){
    override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem==newItem
    }
}