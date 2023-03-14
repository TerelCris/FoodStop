package com.mobdeve.s11.group16.foodstop

import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MyViewHolder(itemView : View): ViewHolder(itemView) {
    //Variables for Card Recyclerview
    private val iv_cover: ImageView = itemView.findViewById(R.id.iv_cover)
    private val tv_category: TextView = itemView.findViewById(R.id.tv_category)
    private val tv_title: TextView = itemView.findViewById(R.id.tv_title)
    private val tv_author:TextView = itemView.findViewById(R.id.tv_author)
    private val tv_date:TextView = itemView.findViewById(R.id.tv_date)
    private val fab_fav:FloatingActionButton = itemView.findViewById(R.id.fab_fav)

    //Variables for Comment Recyclerview
    private val iv_cPicture: ImageView = itemView.findViewById(R.id.iv_cPicture)
    private val tv_cName: TextView = itemView.findViewById(R.id.tv_cName)
    private val tv_cDuration: TextView = itemView.findViewById(R.id.tv_cDuration)
    private val tv_cBody: TextView = itemView.findViewById(R.id.tv_cBody)

    fun bindData(recipe: Recipe){
        iv_cover.setImageResource(recipe.imageId)
        tv_category.text = recipe.category
        tv_title.text = recipe.title
        tv_author.text = recipe.author
        tv_date.text = recipe.date.toString()
        fab_fav.isActivated = recipe.favorite
    }

    fun bindCommentdata(comment: Comment){
        iv_cPicture.setImageResource(comment.imageId)
        tv_cName.text = comment.name
        tv_cDuration.text = comment.duration
        tv_cBody.text = comment.body
    }

    fun setFavoriteOnClickListener(onClickListener: OnClickListener){
        fab_fav.setOnClickListener(onClickListener)
        if(fab_fav.isActivated){
            fab_fav.setImageResource(R.mipmap.star)
        }

        else{
            fab_fav.setImageResource(R.mipmap.favorite)
        }
    }
}