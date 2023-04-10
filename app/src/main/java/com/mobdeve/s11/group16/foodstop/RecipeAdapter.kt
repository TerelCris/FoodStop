package com.mobdeve.s11.group16.foodstop

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class RecipeAdapter(private val context: Context, private val recipeModelList: List<RecipeModel>) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.iv_cover)
        var txtTitle: TextView = itemView.findViewById(R.id.tv_title)
        var txtDate: TextView = itemView.findViewById(R.id.tv_date)
        var txtUser: TextView = itemView.findViewById(R.id.tv_author)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rv_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipeModel = recipeModelList[position]
        holder.txtDate.text = recipeModel.date
        holder.txtTitle.text = recipeModel.title
        holder.txtUser.text = recipeModel.username

        var imageUri: String? = null
        imageUri = recipeModel.image
        Picasso.get().load(imageUri).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return recipeModelList.size
    }
}

