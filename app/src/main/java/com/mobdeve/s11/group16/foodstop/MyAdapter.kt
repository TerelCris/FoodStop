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

class MyAdapter(private val data: ArrayList<Recipe>, private val myActivityResultLauncher: ActivityResultLauncher<Intent>) : RecyclerView.Adapter<MyViewHolder>() {
    

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemViewBinding : RvLayoutBinding = RvLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        val myViewHolder =MyViewHolder(itemViewBinding)

        myViewHolder.itemView.setOnClickListener {
            val intent = Intent(myViewHolder.itemView.context, PostActivity::class.java)

            intent.putExtra(PostActivity.IMAGE_KEY, R.drawable.onepotchickenrice)
            intent.putExtra(PostActivity.USER_KEY, itemViewBinding.tvAuthor.text.toString())
            intent.putExtra(PostActivity.TITLE_KEY, itemViewBinding.tvTitle.text.toString())
            intent.putExtra(PostActivity.DATE_KEY, itemViewBinding.tvDate.text.toString())
            intent.putExtra(PostActivity.POSITION_KEY, myViewHolder.adapterPosition)

            this.myActivityResultLauncher.launch(intent)
        }

        Log.d("My Adapter", "OnCreateView was called")

        return myViewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(data[position])

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