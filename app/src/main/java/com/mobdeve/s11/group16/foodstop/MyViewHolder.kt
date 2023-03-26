package com.mobdeve.s11.group16.foodstop

import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mobdeve.s11.group16.foodstop.databinding.PostLayoutBinding
import com.mobdeve.s11.group16.foodstop.databinding.RvLayoutBinding

class MyViewHolder(private val viewBinding: RvLayoutBinding, private val postBinding: PostLayoutBinding): RecyclerView.ViewHolder(viewBinding.root) {
    private val ivCover: ImageView = itemView.findViewById(R.id.iv_cover)
    private val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
    private val tvAuthor:TextView = itemView.findViewById(R.id.tv_author)
    private val tvDate:TextView = itemView.findViewById(R.id.tv_date)
    private val fabFav:FloatingActionButton = itemView.findViewById(R.id.fab_fav)
    private val btnListener:Button = itemView.findViewById(R.id.btn_listener)
    private val tvBody: TextView = itemView.findViewById(R.id.tv_body)


    fun bindData(recipe: Recipe){
        ivCover.setImageResource(recipe.imageId)
        tvTitle.text = recipe.title
        tvAuthor.text = recipe.author
        tvDate.text = recipe.date.toString()
        fabFav.isActivated = recipe.favorite
        tvBody.text = recipe.body
    }


    fun getBtnListener(): Button {
        return this.btnListener
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