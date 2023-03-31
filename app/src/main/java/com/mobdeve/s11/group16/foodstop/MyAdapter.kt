package com.mobdeve.s11.group16.foodstop

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s11.group16.foodstop.databinding.PostLayoutBinding
import com.mobdeve.s11.group16.foodstop.databinding.RvLayoutBinding

class MyAdapter(
    private val recipe: List<Recipe>
) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemViewBinding : RvLayoutBinding = RvLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        return MyViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(recipe[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, PostActivity::class.java)
            intent.putExtra(Keys.IMAGE_KEY.name, this.recipe[position].imageUrl)
            intent.putExtra(Keys.USERNAME_KEY.name, this.recipe[position].username)
            intent.putExtra(Keys.TITLE_KEY.name, this.recipe[position].title)
            intent.putExtra(Keys.DATE_KEY.name, this.recipe[position].date)
            intent.putExtra(Keys.BODY_KEY.name, this.recipe[position].body)
            holder.itemView.context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return recipe.size
    }
}
