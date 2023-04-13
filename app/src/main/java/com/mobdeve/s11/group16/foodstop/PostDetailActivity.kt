package com.mobdeve.s11.group16.foodstop

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.mobdeve.s11.group16.foodstop.databinding.PostLayoutBinding
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class PostDetailActivity(private val commentList : MutableList<Comment> = mutableListOf()) : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var ref: DatabaseReference
    private lateinit var storage: FirebaseStorage
    private lateinit var recyclerView: RecyclerView
    private lateinit var commentAdapter: CommentAdapter
    private var commentMDList = mutableListOf<CommentModel>()

    private lateinit var title: TextView
    private lateinit var author: TextView
    private lateinit var date: TextView
    private lateinit var description: TextView
    private lateinit var image: ImageView
    private lateinit var comment : EditText
    var currentUsername: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_layout)

        val viewBinding : PostLayoutBinding = PostLayoutBinding.inflate(layoutInflater)

        title = findViewById(R.id.titleTv)
        author = findViewById(R.id.userTv)
        date = findViewById(R.id.datePostTv)
        description = findViewById(R.id.bodyTv)
        image = findViewById(R.id.postIv)
        comment = findViewById(R.id.commentBox)
        database = FirebaseDatabase.getInstance()
        ref = database.reference.child("Comments")
        storage = FirebaseStorage.getInstance()

        // get the passed currentUsername variable here
        currentUsername = intent.getStringExtra("username")

        Picasso.get().load(intent.getStringExtra("image"))
            .placeholder(R.drawable.katsudonjapanesepork)
            .into(image)

        title.text = intent.getStringExtra("title")
        author.text = intent.getStringExtra("author")
        date.text = intent.getStringExtra("date")
        description.text = intent.getStringExtra("description")

        viewBinding.sendBtn.setOnClickListener(View.OnClickListener {
            val username = title.text.toString().trim()
            val body = comment.text.toString().trim()

            if(body.isNotEmpty()){
                val currentDate = Date()
                // Format the date using SimpleDateFormat
                val dateFormat = SimpleDateFormat("MM-dd-yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(currentDate)

                val newComment = ref.push()
                newComment.child("commentId").setValue(newComment.key)
                newComment.child("Username").setValue(currentUsername)
                newComment.child("Title").setValue(body)
                newComment.child("Date").setValue(formattedDate)

                // Set the date
                newComment.child("Date").setValue(formattedDate)

                val intent = Intent(this@PostDetailActivity, PostDetailActivity::class.java)
                startActivity(intent)
            }

            else{
                Toast.makeText(this@PostDetailActivity, "You have not entered any comment", Toast.LENGTH_SHORT).show()
            }
        })

        this.recyclerView = viewBinding.commentsRv
        this.commentAdapter = CommentAdapter(this.applicationContext, commentMDList)
        this.recyclerView.adapter = commentAdapter
        this.recyclerView.layoutManager = LinearLayoutManager(this)

        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(viewBinding.commentsRv)

        ref.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val recipeModel = snapshot.getValue(CommentModel::class.java)
                recipeModel?.let { commentMDList.add(0, it) }
                commentAdapter.notifyDataSetChanged()
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onChildRemoved(snapshot: DataSnapshot) {}
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onCancelled(error: DatabaseError) {}
        })
    }
}
