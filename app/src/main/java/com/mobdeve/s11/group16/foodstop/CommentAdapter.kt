package com.mobdeve.s11.group16.foodstop

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CommentAdapter(private val context: Context, private var commentModelList: MutableList<CommentModel>, private val currentUsername: String, private val postTitle: String) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var commentUser : TextView = itemView.findViewById(R.id.tv_commentName)
        var commentDate : TextView = itemView.findViewById(R.id.tv_commentDuration)
        var commentBody : TextView = itemView.findViewById(R.id.tv_commentBody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.comment_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: CommentAdapter.ViewHolder, position: Int) {
        val commentModel = commentModelList[position]

        holder.commentUser.text = commentModel.username
        holder.commentDate.text = commentModel.date
        holder.commentBody.text = commentModel.body
    }

    override fun getItemCount(): Int {
        return commentModelList.size
    }
}