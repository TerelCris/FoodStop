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
import com.google.firebase.database.FirebaseDatabase
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

        var imageUri: String? = null
        imageUri = recipeModel.image
        Picasso.get().load(imageUri).into(holder.imageView)

        holder.fabFav.setOnClickListener(View.OnClickListener {
            val postRef = FirebaseDatabase.getInstance().getReference("Posts")
                .child(recipeModel.postId)
            postRef.child("BooleanValue").setValue(!recipeModel.isBooleanValue) // update the BooleanValue field

            recipeModel.isBooleanValue = !recipeModel.isBooleanValue // update the local model

            if (recipeModel.isBooleanValue) {
                holder.fabFav.setImageResource(R.mipmap.star)
            } else {
                holder.fabFav.setImageResource(R.mipmap.favorite)
            }
        })


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
    }

    fun filterList(filteredList: List<RecipeModel>) {
        recipeModelList = filteredList
        notifyDataSetChanged()
    }

    fun setData(recipeModelList: List<RecipeModel>) {
        this.recipeModelList = recipeModelList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return recipeModelList.size
    }

}


