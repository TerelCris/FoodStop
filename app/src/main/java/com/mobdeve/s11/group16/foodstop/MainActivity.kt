package com.mobdeve.s11.group16.foodstop

import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s11.group16.foodstop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

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

        val viewBinding : ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.recyclerView.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, PostActivity::class.java)
            this.startActivity(intent)
        })

        viewBinding.ibCreate.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, CreatePostActivity::class.java)
            this.startActivity(intent)
        })

        viewBinding.btnFaved.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, FavoritesActivity::class.java)
            this.startActivity(intent)
        })

        this.recyclerView = viewBinding.recyclerView
        this.adapter = MyAdapter(this.recipeList, postActivityLauncher)
        this.adapter = MyAdapter(this.recipeList, createPostActivityLauncher)
        this.recyclerView.adapter = adapter
        this.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}