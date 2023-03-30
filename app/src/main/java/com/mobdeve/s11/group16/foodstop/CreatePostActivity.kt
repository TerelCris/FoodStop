package com.mobdeve.s11.group16.foodstop

import android.app.Activity
import android.app.ProgressDialog
import android.content.ContentResolver
import android.graphics.Bitmap
import android.provider.MediaStore
import android.support.annotation.NonNull
import android.webkit.MimeTypeMap
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.OnProgressListener
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.io.IOException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.annotation.Nullable
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mobdeve.s11.group16.foodstop.databinding.CreatePostLayoutBinding
import com.mobdeve.s11.group16.foodstop.databinding.MyaccountLayoutBinding
import com.mobdeve.s11.group16.foodstop.databinding.PostLayoutBinding
import com.mobdeve.s11.group16.foodstop.databinding.RvLayoutBinding
import com.squareup.picasso.Picasso


class CreatePostActivity : AppCompatActivity() {
    //var addImage: Uri? = null
    //var storageReference: StorageReference = FirebaseStorage.getInstance().reference
    //var databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference
    //private val viewBinding : RvLayoutBinding = RvLayoutBinding.inflate(layoutInflater)
    //private val createBinding : CreatePostLayoutBinding = CreatePostLayoutBinding.inflate(layoutInflater)
    //private val postBinding : PostLayoutBinding = PostLayoutBinding.inflate(layoutInflater)
    private lateinit var mDatabase: FirebaseDatabase
    private lateinit var mRef: DatabaseReference
    private lateinit var mStorage: FirebaseStorage
    private lateinit var imageButton: ImageButton
    private lateinit var title: EditText
    private lateinit var description: EditText
    private lateinit var ingredient: EditText
    private lateinit var procedure: EditText
    private lateinit var postBtn: Button
    private val galleryCode = 1
    private var imageUrl: Uri? = null
    //var progressDialog: ProgressDialog = ProgressDialog(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewBinding : CreatePostLayoutBinding = CreatePostLayoutBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        imageButton=findViewById(R.id.ib_add)
        title=findViewById(R.id.postTitleEt)
        description=findViewById(R.id.postDescriptionEt)
        ingredient=findViewById(R.id.postIngredientEt)
        procedure=findViewById(R.id.postProcedureEt)
        postBtn=findViewById(R.id.postBtn)
        mDatabase = FirebaseDatabase.getInstance()
        mRef = mDatabase.reference.child("Posts")
        mStorage = FirebaseStorage.getInstance()


        imageButton.setOnClickListener(View.OnClickListener {
            val i = Intent(Intent.ACTION_PICK)
            i.type = "image/*"
            i.action = Intent.ACTION_GET_CONTENT
            createPostResultLauncher.launch(Intent.createChooser(i, "Select Image"))
        })

//       createBinding.postBtn.setOnClickListener(View.OnClickListener {
//            var image = createBinding.ibAdd.text.toString()
//            var title = createBinding.postTitleEt.text.toString()
//            var description = createBinding.postDescriptionEt.text.toString()
//            var ingredient = createBinding.postIngredientEt.text.toString()
//            var procedure = createBinding.postProcedureEt.text.toString()
//            var username = postBinding.userTv.text.toString()
//
//
//            if(image.isEmpty() && title.isEmpty() && description.isEmpty() && ingredient.isEmpty() && procedure.isEmpty() && username.isEmpty()){
//                Toast.makeText(this@CreatePostActivity, "Please answer all required fields", Toast.LENGTH_SHORT).show()
//                return@OnClickListener
//            }
//
//            else{
//                databaseReference.child("Users").addListenerForSingleValueEvent(object : ValueEventListener {
//                    override fun onDataChange(dataSnapshot: DataSnapshot) {
//                        databaseReference.child("Posts").child(username).child("Image").setValue(image)
//                        databaseReference.child("Posts").child(username).child("Title").setValue(title)
//                        databaseReference.child("Posts").child(username).child("Description").setValue(description)
//                        databaseReference.child("Posts").child(username).child("Ingredient").setValue(ingredient)
//                        databaseReference.child("Posts").child(username).child("Procedure").setValue(procedure)
//                        finish()
//                    }
//
//                    override fun onCancelled(databaseError: DatabaseError) {
//                    }
//                })
//                finish()
//            }
//        })

    }

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
            val txtIng = ingredient.text.toString().trim()
            val txtProc = procedure.text.toString().trim()

            if (txtTitle.isNotEmpty() && txtDesc.isNotEmpty() && txtIng.isNotEmpty() && txtProc.isNotEmpty() && imageUrl!=null) {
                val filePath: StorageReference = mStorage.reference.child("imagePost").child(imageUrl!!.lastPathSegment.toString())
                filePath.putFile(imageUrl!!).addOnSuccessListener { taskSnapshot ->
                    val downloadUrl: Task<Uri> = taskSnapshot.storage.downloadUrl.addOnCompleteListener { task ->
                        val t = task.result.toString()

                        val newPost = mRef.push()
                        newPost.child("Title").setValue(txtTitle)
                        newPost.child("Description").setValue(txtDesc)
                        newPost.child("Ingredient").setValue(txtIng)
                        newPost.child("Procedure").setValue(txtProc)
                        newPost.child("image").setValue(task.result.toString())
                    }
                    finish()
                }
            }
        })
    }




}



