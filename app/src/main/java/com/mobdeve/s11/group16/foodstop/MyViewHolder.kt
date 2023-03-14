package com.mobdeve.s11.group16.foodstop

import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MyViewHolder(itemView : View): ViewHolder(itemView) {
    private val iv_cover: ImageView = itemView.findViewById(R.id.iv_cover)
    private val tv_category: TextView = itemView.findViewById(R.id.tv_category)
    private val tv_title: TextView = itemView.findViewById(R.id.tv_title)
    private val tv_author:TextView = itemView.findViewById(R.id.tv_author)
    private val tv_date:TextView = itemView.findViewById(R.id.tv_date)
    private val fab_fav:FloatingActionButton = itemView.findViewById(R.id.fab_fav)

    fun bindData(recipe: Recipe){
        iv_cover.setImageResource(recipe.imageId)
        tv_category.text = recipe.category
        tv_title.text = recipe.title
        tv_author.text = recipe.author
        tv_date.text = recipe.date.toString()
        fab_fav.isActivated = recipe.favorite
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

    private fun removeNewLineCharacters(input: String): String {
        return input.replace("\n", " ")
    }
}