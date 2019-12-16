package com.example.myapplication.appFragments

import android.annotation.SuppressLint
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

private const val ITEM_VIEW_TYPE_HEADER = 0
private const val ITEM_VIEW_TYPE_ITEM = 1
class WordAdapter(val clickListener:WordListener) : ListAdapter<DataItem,RecyclerView.ViewHolder>(WordDiffCallBack()) {


    var words =  listOf<Word>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun addHeaderAndSubmitList(list: List<Word>?) {
        val items = when (list) {
            null -> listOf(DataItem.Header)
            else -> listOf(DataItem.Header) + list.map { DataItem.WordItem(it) }
        }
        submitList(items)
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> TextViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> ViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }
    /*
    override fun getItemCount(): Int {
        return words.size
    }
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val worditem = getItem(position) as DataItem.WordItem
                holder.bind(worditem.word, clickListener)
            }
        }
    }

    class ViewHolder  private constructor(val binding: WordListBinding) : RecyclerView.ViewHolder(binding.root){
        val hun: TextView = itemView.findViewById(R.id.hun)
        val eng: TextView = itemView.findViewById(R.id.eng)


        fun bind(item: Word, clickListener: WordListener) {
            Log.e("e",item.hun)
           // hun.text =item.hun
           // eng.text = item.eng
            binding.clickListener=clickListener

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


    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.WordItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    class TextViewHolder(view: View): RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup): TextViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.header, parent, false)
                return TextViewHolder(view)
            }
        }
    }


}

class WordDiffCallBack : DiffUtil.ItemCallback<DataItem>(){
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id==newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }
}

class WordListener(val clickListener: (wordId: Long) -> Unit) {
    fun onClick(word: Word) = clickListener(word.id)
}

sealed class DataItem {
    data class WordItem(val word: Word): DataItem() {
        override val id = word.id
    }

    object Header: DataItem() {
        override val id = Long.MIN_VALUE
    }

    abstract val id: Long
}