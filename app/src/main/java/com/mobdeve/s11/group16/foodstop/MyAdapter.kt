package com.mobdeve.s11.group16.foodstop

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val data: ArrayList<Recipe>) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.rv_layout, parent, false)

        Log.d("My Adapter", "OnCreateView was called")

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(data[position])

        Log.d("My Adapter", "OnBindView was called; Position $position")
    }

    override fun getItemCount(): Int {
        return data.size
    }
}