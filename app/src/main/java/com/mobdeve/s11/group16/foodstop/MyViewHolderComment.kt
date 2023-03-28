package com.mobdeve.s11.group16.foodstop


import android.widget.ImageView
import android.widget.TextView
import com.mobdeve.s11.group16.foodstop.databinding.CommentLayoutBinding
import androidx.recyclerview.widget.RecyclerView

class MyViewHolderComment(private val viewBinding : CommentLayoutBinding): RecyclerView.ViewHolder(viewBinding.root) {
    private val tvCommentName: TextView = itemView.findViewById(R.id.tv_commentName)
    private val tvCommentDuration: TextView = itemView.findViewById(R.id.tv_commentDuration)
    private val tvCommentBody: TextView = itemView.findViewById(R.id.tv_commentBody)

    fun bindData(comment: Comment){
        tvCommentName.text = comment.name
        tvCommentDuration.text = comment.duration
        tvCommentBody.text = comment.body
    }
}