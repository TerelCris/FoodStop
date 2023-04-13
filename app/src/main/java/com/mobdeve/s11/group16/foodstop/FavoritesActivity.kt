package com.mobdeve.s11.group16.foodstop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.mobdeve.s11.group16.foodstop.databinding.ActivityMainBinding
import com.mobdeve.s11.group16.foodstop.databinding.FavoritesBinding

class FavoritesActivity : AppCompatActivity() {
    private lateinit var database: FirebaseDatabase
    private lateinit var ref: DatabaseReference
    private lateinit var storage: FirebaseStorage
    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter
    private var recipeMDList = mutableListOf<RecipeModel>()

    private var currentUsername: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding: FavoritesBinding = FavoritesBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // Get the current user's username from the intent
        currentUsername = intent.getStringExtra("username")

        database = FirebaseDatabase.getInstance()
        ref = database.reference.child("Posts")
        storage = FirebaseStorage.getInstance()

        viewBinding.ibCreate.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@FavoritesActivity, CreatePostActivity::class.java)
            intent.putExtra("username", currentUsername)
            startActivity(intent)
        })

        viewBinding.btnAll.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@FavoritesActivity, MainActivity::class.java)
            this.startActivity(intent)
        })

        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(viewBinding.recyclerView)

        this.recyclerView = viewBinding.recyclerView
        this.recipeAdapter = RecipeAdapter(this, recipeMDList, currentUsername.toString())
        this.recyclerView.adapter = recipeAdapter
        this.recyclerView.layoutManager = LinearLayoutManager(this)

        ref.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val recipeModel = snapshot.getValue(RecipeModel::class.java)
                if (recipeModel?.isBooleanValue == true && snapshot.child("Favorites").hasChild(
                        currentUsername.toString()
                    )) {
                    // filter based on BooleanValue and Favorites node
                    recipeModel.let { recipeMDList.add(it) }
                    recipeAdapter.notifyDataSetChanged()
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                // if a recipe's favorite status is changed, update the list
                val recipeModel = snapshot.getValue(RecipeModel::class.java)
                if (recipeModel?.isBooleanValue == true && snapshot.child("Favorites").hasChild(
                        currentUsername.toString()
                    )) {
                    val index = recipeMDList.indexOfFirst { it.postId == recipeModel.postId }
                    if (index != -1) {
                        recipeMDList[index] = recipeModel
                        recipeAdapter.notifyItemChanged(index)
                    }
                }
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                // if a recipe is unfavorited, remove it from the list
                val recipeModel = snapshot.getValue(RecipeModel::class.java)
                if (recipeModel?.isBooleanValue == true && snapshot.child("Favorites").hasChild(
                        currentUsername.toString()
                    )) {
                    val index = recipeMDList.indexOfFirst { it.postId == recipeModel.postId }
                    if (index != -1) {
                        recipeMDList.removeAt(index)
                        recipeAdapter.notifyItemRemoved(index)
                    }
                }
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onCancelled(error: DatabaseError) {}
        })

    }
}
