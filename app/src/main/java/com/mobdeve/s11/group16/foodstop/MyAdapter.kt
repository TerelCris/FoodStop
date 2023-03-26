package com.mobdeve.s11.group16.foodstop

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.core.graphics.convertTo
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s11.group16.foodstop.databinding.PostLayoutBinding
import com.mobdeve.s11.group16.foodstop.databinding.RvLayoutBinding

class MyAdapter(private val data: ArrayList<Recipe>, private val myActivityResultLauncher: ActivityResultLauncher<Intent>) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemViewBinding : RvLayoutBinding = RvLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        val postViewBinding : PostLayoutBinding = PostLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        val myViewHolder = MyViewHolder(itemViewBinding, postViewBinding)

        Log.d("My Adapter", "OnCreateView was called")

        return myViewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(data[position])

      holder.itemView.setOnClickListener {

            val intent = Intent(holder.itemView.context, PostActivity::class.java)

            intent.putExtra(PostActivity.IMAGE_KEY, this.data[position].imageId)
            intent.putExtra(PostActivity.USER_KEY, this.data[position].author)
            intent.putExtra(PostActivity.TITLE_KEY, this.data[position].title)
            intent.putExtra(PostActivity.DATE_KEY, this.data[position].date)
            intent.putExtra(PostActivity.BODY_KEY, this.data[position].body)

            this.myActivityResultLauncher.launch(intent)
        }

        Log.d("My Adapter", "OnBindView was called; Position $position")

        holder.setFavoriteOnClickListener(View.OnClickListener {
            this.data[position].favorite = !this.data[position].favorite

            notifyDataSetChanged()
        })
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
