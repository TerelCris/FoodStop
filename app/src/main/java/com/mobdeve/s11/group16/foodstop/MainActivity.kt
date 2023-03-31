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
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.mobdeve.s11.group16.foodstop.databinding.ActivityMainBinding
import com.mobdeve.s11.group16.foodstop.databinding.PostLayoutBinding

class MainActivity : AppCompatActivity() {

    var currentUsername: String? = null

    companion object{
        private val data = ArrayList<Recipe>()
    }

    private val recipeList: ArrayList<Recipe> = DataHelper.initializeData()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter

    private val postActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result: androidx.activity.result.ActivityResult ->

        val viewBinding : PostLayoutBinding = PostLayoutBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        if(result.resultCode == RESULT_OK){
            val position = result.data?.getIntExtra(Keys.POSITION_KEY.name, 0)!!
            this.adapter.notifyDataSetChanged()
        }
    }
    private val createPostActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result: androidx.activity.result.ActivityResult ->

        if(result.resultCode == RESULT_OK){
            val image : Int = result.data?.getIntExtra(Keys.IMAGE_KEY.name, 0)!!
            val author : String = result.data?.getStringExtra(Keys.USERNAME_KEY.name)!!
            val date : String = result.data?.getStringExtra(Keys.DATE_KEY.name)!!
            val favorite : Boolean = result.data?.getBooleanExtra(Keys.FAVORITE_KEY.name, false)!!
            val title : String = result.data?.getStringExtra(Keys.TITLE_KEY.name)!!
            val description : String = result.data?.getStringExtra(Keys.DESCRIPTION_KEY.name)!!
            val ingredient : String = result.data?.getStringExtra(Keys.INGREDIENT_KEY.name)!!
            val procedure : String = result.data?.getStringExtra(Keys.PROCEDURE_KEY.name)!!

            val body = title + description + ingredient + procedure

            val recipe = Recipe(image, title, author.toString(), date.toString(), favorite, body)

            MainActivity.data.add(recipe)

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

        // get the passed currentUsername variable here
        currentUsername = intent.getStringExtra("username")


        viewBinding.ibCreate.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, CreatePostActivity::class.java)
            intent.putExtra("username", currentUsername)
            startActivity(intent)
        })

        viewBinding.btnFaved.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, FavoritesActivity::class.java)
            this.startActivity(intent)
        })

        viewBinding.ibUser.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, UserAccountActivity::class.java)
            startActivity(intent)
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