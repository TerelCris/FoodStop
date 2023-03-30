package com.mobdeve.s11.group16.foodstop

import android.app.Activity
import android.app.ProgressDialog
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.IOException;
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mobdeve.s11.group16.foodstop.databinding.CreatePostLayoutBinding
import com.mobdeve.s11.group16.foodstop.databinding.PostLayoutBinding
import com.mobdeve.s11.group16.foodstop.databinding.RvLayoutBinding
import com.squareup.picasso.Picasso

class CreatePostActivity : AppCompatActivity() {
    private lateinit var rvBinding : RvLayoutBinding
    private lateinit var postBinding : PostLayoutBinding
    private var addImage: Uri? = null
    private var btnAdd: Button = findViewById(R.id.ib_add)
    private var btnPost: Button = findViewById(R.id.postBtn)
    private var txtTitle: EditText = findViewById(R.id.postTitleEt)
    private var txtDesc: EditText = findViewById(R.id.postDescriptionEt)
    private var txtIng: EditText = findViewById(R.id.postIngredientEt)
    private var txtProc: EditText = findViewById(R.id.postProcedureEt)
    private var imgAdd: ImageView = findViewById(R.id.post_img)
    private var filePathUri: Uri? = null
    private var storageReference: StorageReference = FirebaseStorage.getInstance().reference
    private var databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference
    private val image_Request_Code = 7
    private var progressDialog: ProgressDialog = ProgressDialog(this)

    private val createPostResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            try {
                if (result.data != null) {
                    addImage = result.data!!.data
                    Picasso.get().load(addImage).into(rvBinding.ivCover)
                    Picasso.get().load(addImage).into(postBinding.postIv)
                }
            } catch (exception: Exception) {
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding : CreatePostLayoutBinding = CreatePostLayoutBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val userBinding : PostLayoutBinding = PostLayoutBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        storageReference = FirebaseStorage.getInstance().getReference("Images")
        databaseReference = FirebaseDatabase.getInstance().getReference("https://foodstop-9c45c-default-rtdb.firebaseio.com/")

        viewBinding.ibAdd.setOnClickListener(View.OnClickListener {
            val i = Intent()
            i.type = "Post Image/*"
            i.action = Intent.ACTION_GET_CONTENT
            createPostResultLauncher.launch(Intent.createChooser(i, "Select Image"))
        })

        btnPost.setOnClickListener {
            uploadImage()
        }

        viewBinding.postBtn.setOnClickListener(View.OnClickListener {
            var image = viewBinding.ibAdd.text.toString()
            var title = viewBinding.postTitleEt.text.toString()
            var description = viewBinding.postDescriptionEt.text.toString()
            var ingredient = viewBinding.postIngredientEt.text.toString()
            var procedure = viewBinding.postProcedureEt.text.toString()
            var username = userBinding.userTv.text.toString()


            if(image.isEmpty() && title.isEmpty() && description.isEmpty() && ingredient.isEmpty() && procedure.isEmpty() && username.isEmpty()){
                Toast.makeText(this@CreatePostActivity, "Please answer all required fields", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            else{
                databaseReference.child("Users").addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        databaseReference.child("Posts").child(username).child("Image").setValue(image)
                        databaseReference.child("Posts").child(username).child("Title").setValue(title)
                        databaseReference.child("Posts").child(username).child("Description").setValue(description)
                        databaseReference.child("Posts").child(username).child("Ingredient").setValue(ingredient)
                        databaseReference.child("Posts").child(username).child("Procedure").setValue(procedure)
                        finish()
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                    }
                })
                finish()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == image_Request_Code && resultCode == RESULT_OK && data != null && data.data != null) {
            filePathUri = data.data

            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePathUri)
                imgAdd.setImageBitmap(bitmap)
            } catch (e: IOException) {e.printStackTrace()
            }
        }
    }

    private fun getFileExtension(uri: Uri): String? {
        val contentResolver = contentResolver
        val mimeTypeMap = MimeTypeMap.getSingleton()
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri))
    }

    fun uploadImage() {
        if (filePathUri != null) {
            progressDialog.setTitle("Image is Uploading...")
            progressDialog.show()
            val storageReference2 = storageReference.child(System.currentTimeMillis().toString() + "." + getFileExtension(
                filePathUri!!
            )!!)
            storageReference2.putFile(filePathUri!!)
                .addOnSuccessListener { taskSnapshot ->
                    val tempImageName = txtTitle.text.toString().trim()
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, "Image Uploaded Successfully ", Toast.LENGTH_LONG).show()
                    @Suppress("VisibleForTests")
                    val imageUploadInfo = UploadInfo(tempImageName, taskSnapshot.uploadSessionUri.toString())
                    val imageUploadId = databaseReference.push().key
                    databaseReference.child(imageUploadId!!).setValue(imageUploadInfo)
                }
        } else {
            Toast.makeText(this@CreatePostActivity, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show()
        }
    }

}
