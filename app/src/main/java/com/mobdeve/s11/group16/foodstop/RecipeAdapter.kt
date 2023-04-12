package com.mobdeve.s11.group16.foodstop

import android.content.Intent
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso


class RecipeAdapter(private val context: Context, private var recipeModelList: List<RecipeModel>) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.iv_cover)
        var txtTitle: TextView = itemView.findViewById(R.id.tv_title)
        var txtDate: TextView = itemView.findViewById(R.id.tv_date)
        var txtUser: TextView = itemView.findViewById(R.id.tv_author)
        var txtDesc: TextView = itemView.findViewById(R.id.tv_desc)
        var fabFav: FloatingActionButton = itemView.findViewById(R.id.fab_fav)
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
        holder.txtDesc.text = recipeModel.description
        holder.fabFav.isActivated = true

        var imageUri: String? = null
        imageUri = recipeModel.image
        Picasso.get().load(imageUri).into(holder.imageView)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, PostDetailActivity::class.java)
            intent.putExtra("image", recipeModel.image)
            intent.putExtra("title", recipeModel.title)
            intent.putExtra("author", recipeModel.username)
            intent.putExtra("date", recipeModel.date)
            intent.putExtra("description", recipeModel.description)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }


       holder.fabFav.setOnClickListener(View.OnClickListener {
           if(holder.fabFav.isActivated){
               holder.fabFav.setImageResource(R.mipmap.star)
           }
           else{
               holder.fabFav.setImageResource(R.mipmap.favorite)
           }

           holder.fabFav.isActivated = !holder.fabFav.isActivated
       })
    }

    fun filterList(filteredList: List<RecipeModel>) {
        recipeModelList = filteredList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return recipeModelList.size
    }
}

