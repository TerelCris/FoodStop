package com.mobdeve.s11.group16.foodstop

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val data: ArrayList<Recipe>) : RecyclerView.Adapter<MyViewHolder>() {

    companion object {
        const val KEY_NAME = "KEY_NAME"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.rv_layout, parent, false)

        Log.d("My Adapter", "OnCreateView was called")

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(data[position])

        Log.d("My Adapter", "OnBindView was called; Position $position")

        holder.setFavoriteOnClickListener(View.OnClickListener {
            this.data[position].favorite = !this.data[position].favorite

            notifyDataSetChanged()
        })
    }

    override fun getItemCount(): Int {
        return data.size
    }
}