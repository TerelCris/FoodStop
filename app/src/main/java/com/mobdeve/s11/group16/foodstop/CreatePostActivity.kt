package com.mobdeve.s11.group16.foodstop

import android.app.Activity
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.database.*
import com.mobdeve.s11.group16.foodstop.databinding.CreatePostLayoutBinding
import java.text.SimpleDateFormat
import java.util.*


class CreatePostActivity : AppCompatActivity() {
    private lateinit var database: FirebaseDatabase
    private lateinit var ref: DatabaseReference
    private lateinit var storage: FirebaseStorage
    private lateinit var imageButton: ImageButton
    private lateinit var title: EditText
    private lateinit var description: EditText
    private lateinit var postBtn: Button
    private val galleryCode = 1
    private var imageUrl: Uri? = null
    private var currentUsername: String? = null
    private val recipeList = ArrayList<Recipe>()
    private lateinit var adapter: RecipeAdapter

    private val createPostResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            if (data != null) {
                imageUrl = data.data
                if (imageUrl != null) {
                    imageButton.setImageURI(imageUrl)
                }
            }
        }
        postBtn.setOnClickListener(View.OnClickListener {
            val txtTitle = title.text.toString().trim()
            val txtDesc = description.text.toString().trim()

            if (txtTitle.isNotEmpty() && txtDesc.isNotEmpty() && imageUrl!=null) {
                val filePath: StorageReference = storage.reference.child("imagePost").child(imageUrl!!.lastPathSegment.toString())
                filePath.putFile(imageUrl!!).addOnSuccessListener { taskSnapshot ->
                    val downloadUrl: Task<Uri> = taskSnapshot.storage.downloadUrl.addOnCompleteListener { task ->
                        val t = task.result.toString()

                        // Get the current date
                        val currentDate = Date()
                        // Format the date using SimpleDateFormat
                        val dateFormat = SimpleDateFormat("MM-dd-yyyy", Locale.getDefault())
                        val formattedDate = dateFormat.format(currentDate)

                        val newPost = ref.push()
                        newPost.child("postId").setValue(newPost.key)
                        newPost.child("Title").setValue(txtTitle)
                        newPost.child("Description").setValue(txtDesc)
                        newPost.child("Username").setValue(currentUsername)
                        newPost.child("Image").setValue(task.result.toString())
                        // Set the date
                        newPost.child("Date").setValue(formattedDate)


                        Toast.makeText(this@CreatePostActivity, "Post has been created", Toast.LENGTH_SHORT).show()


                        val intent = Intent(this@CreatePostActivity, MainActivity::class.java)
                        intent.putExtra("username", currentUsername)
                        startActivity(intent)
                    }

                }
            }

            else{
                Toast.makeText(this@CreatePostActivity, "Please enter all fields!", Toast.LENGTH_SHORT).show()
            }
        })

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("username", currentUsername)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewBinding : CreatePostLayoutBinding = CreatePostLayoutBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        imageButton = findViewById(R.id.ib_add)
        title = findViewById(R.id.postTitleEt)
        description = findViewById(R.id.postDescriptionEt)
        postBtn = findViewById(R.id.postBtn)
        database = FirebaseDatabase.getInstance()
        ref = database.reference.child("Posts")
        storage = FirebaseStorage.getInstance()

        // get the passed currentUsername variable here
        currentUsername = intent.getStringExtra("username")


        imageButton.setOnClickListener(View.OnClickListener {
            val i = Intent(Intent.ACTION_PICK)
            i.type = "image/*"
            i.action = Intent.ACTION_GET_CONTENT
            createPostResultLauncher.launch(Intent.createChooser(i, "Select Image"))
        })
    }

}




