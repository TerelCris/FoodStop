package com.mobdeve.s11.group16.foodstop

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.mobdeve.s11.group16.foodstop.databinding.PostLayoutBinding
import org.w3c.dom.Text

class PostActivity : AppCompatActivity() {
    private val commentList = ArrayList<Comment>()

    private lateinit var database: FirebaseDatabase
    private lateinit var ref: DatabaseReference
    private lateinit var storage: FirebaseStorage
    private lateinit var image : ImageView
    private lateinit var title : TextView
    private lateinit var date : TextView
    private lateinit var body : TextView
    var currentUsername: String? = null
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewBinding : PostLayoutBinding = PostLayoutBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        image = findViewById(R.id.postIv)
        title = findViewById(R.id.titleTv)
        body = findViewById(R.id.bodyTv)
        date = findViewById(R.id.datePostTv)
        database = FirebaseDatabase.getInstance()
        ref = database.reference.child("Posts")
        storage = FirebaseStorage.getInstance()

        // get the passed currentUsername variable here
        currentUsername = intent.getStringExtra("username")


        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(viewBinding.commentsRv)

        this.recyclerView = viewBinding.commentsRv
        this.recyclerView.adapter = MyAdapterComment(this.commentList)
        this.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}