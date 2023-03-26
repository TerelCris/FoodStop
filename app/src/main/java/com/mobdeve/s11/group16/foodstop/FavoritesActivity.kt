package com.mobdeve.s11.group16.foodstop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.mobdeve.s11.group16.foodstop.databinding.FavoritesBinding

class FavoritesActivity : AppCompatActivity() {

    private val recipeList: ArrayList<Recipe> = DataHelper.initializeData()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter

    private val postActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result: androidx.activity.result.ActivityResult ->

        if(result.resultCode == RESULT_OK){
            val position = result.data?.getIntExtra(PostActivity.POSITION_KEY, 0)!!
            this.adapter.notifyDataSetChanged()
        }
    }
    private val createPostActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result: androidx.activity.result.ActivityResult ->

        if(result.resultCode == RESULT_OK){
            val title : String = result.data?.getStringExtra(CreatePostActivity.TITLE_KEY)!!
            val description : String = result.data?.getStringExtra(CreatePostActivity.DESCRIPTION_KEY)!!
            val ingredient : String = result.data?.getStringExtra(CreatePostActivity.INGREDIENT_KEY)!!
            val procedure : String = result.data?.getStringExtra(CreatePostActivity.PROCEDURE_KEY)!!

            this.adapter.notifyDataSetChanged()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewBinding : FavoritesBinding = FavoritesBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.recyclerView.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@FavoritesActivity, PostActivity::class.java)
            this.startActivity(intent)
        })

        viewBinding.ibCreate.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@FavoritesActivity, CreatePostActivity::class.java)
            this.startActivity(intent)
        })

        viewBinding.btnAll.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@FavoritesActivity, MainActivity::class.java)
            this.startActivity(intent)
        })

        viewBinding.ibUser.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@FavoritesActivity, UserAccountActivity::class.java)
            this.startActivity(intent)
        })

        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(viewBinding.recyclerView)

        this.recyclerView = viewBinding.recyclerView
        this.adapter = MyAdapter(this.recipeList, postActivityLauncher)
        this.adapter = MyAdapter(this.recipeList, createPostActivityLauncher)
        this.recyclerView.adapter = adapter
        this.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}