package com.example.myapplication.appFragments.json

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class JsonAdapter: RecyclerView.Adapter<JsonAdapter.ViewHolder>() {

    var data =  listOf<JsonModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return  data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }


    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.json_name)
        val jsonId: TextView = itemView.findViewById(R.id.json_id)
        val postId: TextView = itemView.findViewById(R.id.post_id)
        val email: TextView = itemView.findViewById(R.id.json_email)
        val body: TextView = itemView.findViewById(R.id.json_body)

        fun bind(item: JsonModel) {
            name.text="Name: "+item.name
            jsonId.text="Id: "+item.id.toString()
            postId.text="Post Id: "+item.postId.toString()
            email.text="email: "+item.email
            body.text="body: "+item.body

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.json_list, parent, false)

                return ViewHolder(view)
            }
        }
    }
}