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
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.mobdeve.s11.group16.foodstop.databinding.ActivityMainBinding
import com.mobdeve.s11.group16.foodstop.databinding.PostLayoutBinding

class MainActivity(private val recipeList: MutableList<Recipe> = mutableListOf()) : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var ref: DatabaseReference
    private lateinit var storage: FirebaseStorage
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private lateinit var recipeAdapter: RecipeAdapter
    private var recipeMDList = mutableListOf<RecipeModel>()

    private var currentUsername: String? = null
    private var currentEmail: String? = null
    private var currentPassword: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = FirebaseDatabase.getInstance()
        ref = database.reference.child("Posts")
        storage = FirebaseStorage.getInstance()

        val viewBinding : ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // get the passed currentUsername variable here
        currentUsername = intent.getStringExtra("username")
        currentEmail = intent.getStringExtra("email")
        currentPassword = intent.getStringExtra("password")

        viewBinding.recyclerView.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, PostActivity::class.java)
            intent.putExtra("username", currentUsername)
            startActivity(intent)
        })

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
            intent.putExtra("username", currentUsername)
            intent.putExtra("email", currentEmail)
            intent.putExtra("password", currentPassword)
            startActivity(intent)
        })

        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(viewBinding.recyclerView)

        this.recyclerView = viewBinding.recyclerView
        this.adapter = MyAdapter(this.recipeList)
        this.recyclerView.adapter = adapter
        this.recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView = viewBinding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        recipeAdapter = RecipeAdapter(this, recipeMDList)
        recyclerView.adapter = recipeAdapter

        ref.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val recipeModel = snapshot.getValue(RecipeModel::class.java)
                recipeModel?.let { recipeMDList.add(it) }
                recipeAdapter.notifyDataSetChanged()
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onChildRemoved(snapshot: DataSnapshot) {}
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onCancelled(error: DatabaseError) {}
        })


    }

}