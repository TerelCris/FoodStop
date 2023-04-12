package com.mobdeve.s11.group16.foodstop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.mobdeve.s11.group16.foodstop.databinding.ActivityMainBinding
import java.util.*
import com.mobdeve.s11.group16.foodstop.RecipeAdapter as recipeAdapter

class MainActivity(private val recipeList: MutableList<Recipe> = mutableListOf()) : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var ref: DatabaseReference
    private lateinit var storage: FirebaseStorage
    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeAdapter: recipeAdapter
    private lateinit var searchView: SearchView
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

        searchView = findViewById(R.id.searchView)
        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredList = mutableListOf<RecipeModel>()
                for (recipeModel in recipeMDList) {
                    if (recipeModel.title.lowercase(Locale.getDefault())
                            .contains(newText.toString().lowercase(Locale.getDefault()))) {
                        filteredList.add(recipeModel)
                    }
                }
                recipeAdapter.filterList(filteredList)
                return true
            }
        })

        // get the passed currentUsername variable here
        currentUsername = intent.getStringExtra("username")
        currentEmail = intent.getStringExtra("email")
        currentPassword = intent.getStringExtra("password")

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
        this.recipeAdapter = recipeAdapter(this.applicationContext, recipeMDList)
        this.recyclerView.adapter = recipeAdapter
        this.recyclerView.layoutManager = LinearLayoutManager(this)


        ref.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val recipeModel = snapshot.getValue(RecipeModel::class.java)
                recipeModel?.let { recipeMDList.add(0, it) }
                recipeAdapter.notifyDataSetChanged()
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onChildRemoved(snapshot: DataSnapshot) {}
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onCancelled(error: DatabaseError) {}
        })
    }
}