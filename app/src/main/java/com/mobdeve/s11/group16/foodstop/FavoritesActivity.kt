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
import com.mobdeve.s11.group16.foodstop.databinding.FavoritesBinding

class FavoritesActivity : AppCompatActivity() {
    private lateinit var database: FirebaseDatabase
    private lateinit var ref: DatabaseReference
    private lateinit var storage: FirebaseStorage
    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter
    private var recipeMDList = mutableListOf<RecipeModel>()

    private var currentUsername: String? = null
    private var currentEmail: String? = null
    private var currentPassword: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding: FavoritesBinding = FavoritesBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // Get the current user's username from the intent
        currentUsername = intent.getStringExtra("username")
        currentEmail = intent.getStringExtra("email")
        currentPassword = intent.getStringExtra("password")

        database = FirebaseDatabase.getInstance()
        storage = FirebaseStorage.getInstance()
        ref = database.getReference("recipes")


        viewBinding.ibCreate.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@FavoritesActivity, CreatePostActivity::class.java)
            intent.putExtra("username", currentUsername)
            startActivity(intent)
        })

        viewBinding.btnAll.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@FavoritesActivity, MainActivity::class.java)
            intent.putExtra("username", currentUsername)
            this.startActivity(intent)
        })

        viewBinding.ibUser.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@FavoritesActivity, UserAccountActivity::class.java)
            intent.putExtra("username", currentUsername)
            intent.putExtra("email", currentEmail)
            intent.putExtra("password", currentPassword)
            startActivity(intent)
        })

        this.recyclerView = viewBinding.recyclerView
        this.recipeAdapter = RecipeAdapter(this, recipeMDList, currentUsername.toString())
        this.recyclerView.adapter = recipeAdapter
        this.recyclerView.layoutManager = LinearLayoutManager(this)

        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(viewBinding.recyclerView)

        ref.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val recipeModel = snapshot.getValue(RecipeModel::class.java)
                if (recipeModel?.isBooleanValue == true) {
                    recipeModel?.let { recipeMDList.add(0, it) }
                    currentUsername?.let { recipeAdapter = RecipeAdapter(this@FavoritesActivity.applicationContext, recipeMDList, it) }
                    recyclerView.adapter = recipeAdapter
                    recipeAdapter.notifyDataSetChanged()
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

    }

}
