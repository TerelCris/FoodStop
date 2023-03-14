package com.mobdeve.s11.group16.foodstop

import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MyViewHolder(itemView : View): ViewHolder(itemView) {
    private val ivCover: ImageView = itemView.findViewById(R.id.iv_cover)
    private val tvCategory: TextView = itemView.findViewById(R.id.tv_category)
    private val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
    private val tvAuthor:TextView = itemView.findViewById(R.id.tv_author)
    private val tvDate:TextView = itemView.findViewById(R.id.tv_date)
    private val fabFav:FloatingActionButton = itemView.findViewById(R.id.fab_fav)


    fun bindData(recipe: Recipe){
        ivCover.setImageResource(recipe.imageId)
        tvCategory.text = recipe.category
        tvTitle.text = recipe.title
        tvAuthor.text = recipe.author
        tvDate.text = recipe.date.toString()
        fabFav.isActivated = recipe.favorite
    }

    fun setFavoriteOnClickListener(onClickListener: OnClickListener){
        fabFav.setOnClickListener(onClickListener)
        if(fabFav.isActivated){
            fabFav.setImageResource(R.mipmap.star)
        }

        else{
            fabFav.setImageResource(R.mipmap.favorite)
        }
    }

    private fun removeNewLineCharacters(input: String): String {
        return input.replace("\n", " ")
    }
}