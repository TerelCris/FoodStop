package com.mobdeve.s11.group16.foodstop

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private lateinit var sendBtn : Button
    private lateinit var btn_user : ImageButton

    private var currentUsername: String? = null
    private var currentEmail: String? = null
    private var currentPassword: String? = null
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("username", currentUsername)
    }

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
        sendBtn = findViewById(R.id.sendBtn)
        btn_user = findViewById(R.id.btn_user)
        database = FirebaseDatabase.getInstance()
        ref = database.reference.child("Comments")


        // get the passed currentUsername variable here
        currentUsername = intent.getStringExtra("username")
        currentEmail = intent.getStringExtra("email")
        currentPassword = intent.getStringExtra("password")

        btn_user.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@PostDetailActivity, UserAccountActivity::class.java)
            intent.putExtra("username", currentUsername)
            intent.putExtra("email", currentEmail)
            intent.putExtra("password", currentPassword)
            startActivity(intent)
        })


        Picasso.get().load(intent.getStringExtra("image"))
            .placeholder(R.drawable.katsudonjapanesepork)
            .into(image)

        title.text = intent.getStringExtra("title")
        author.text = intent.getStringExtra("author")
        date.text = intent.getStringExtra("date")
        description.text = intent.getStringExtra("description")

        // Initialize the RecyclerView and the adapter
        recyclerView = findViewById(R.id.commentsRv)
        commentAdapter = CommentAdapter(applicationContext, commentMDList, currentUsername.toString())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = commentAdapter

        // Set the addChildEventListener after setting the adapter
        ref.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val commentModel = snapshot.getValue(CommentModel::class.java)
                if (commentModel?.postTitle == title.text.toString()) {
                    commentModel?.let { commentMDList.add(0, it) }
                    currentUsername?.let { commentAdapter = CommentAdapter(this@PostDetailActivity.applicationContext, commentMDList, it) }
                    recyclerView.adapter = commentAdapter
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                // Do nothing
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                // Do nothing
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                // Do nothing
            }

            override fun onCancelled(error: DatabaseError) {
                // Do nothing
            }
        })


        sendBtn.setOnClickListener(View.OnClickListener {
            val txtComm = comment.text.toString().trim()
            val txtTitle = title.text.toString().trim()

            if (txtComm.isNotEmpty()) {
                // Get the current date
                val currentDate = Date()
                // Format the date using SimpleDateFormat
                val dateFormat = SimpleDateFormat("MM-dd-yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(currentDate)

                val newPost = ref.push()
                newPost.child("PostTitle").setValue(txtTitle)
                newPost.child("Username").setValue(currentUsername.toString())
                newPost.child("Date").setValue(formattedDate)
                newPost.child("Comment").setValue(txtComm)

                Toast.makeText(this@PostDetailActivity, "Comment has been created", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@PostDetailActivity, PostDetailActivity::class.java)
                intent.putExtra("username", currentUsername)
                intent.putExtra("title", title.text.toString())
                intent.putExtra("author", author.text.toString())
                intent.putExtra("date", date.text.toString())
                intent.putExtra("description", description.text.toString())
                startActivity(intent)
            }
            else{
                Toast.makeText(this@PostDetailActivity, "Please enter all fields!", Toast.LENGTH_SHORT).show()
            }

        })



    }
}

