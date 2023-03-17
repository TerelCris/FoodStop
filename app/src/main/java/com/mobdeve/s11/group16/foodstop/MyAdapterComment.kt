package com.mobdeve.s11.group16.foodstop

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mobdeve.s11.group16.foodstop.databinding.CommentLayoutBinding

class MyAdapterComment(private val data: ArrayList<Comment>) : RecyclerView.Adapter<MyViewHolderComment>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderComment {
        val itemViewBinding : CommentLayoutBinding = CommentLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        val myViewHolder = MyViewHolderComment(itemViewBinding)

        Log.d("My Adapter Comment", "OnCreateView was called")

        return myViewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolderComment, position: Int) {
        holder.bindData(data[position])

        Log.d("My Adapter Comment", "OnBindView was called; Position $position")
    }

    override fun getItemCount(): Int {
        return data.size
    }
}