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
    val btnAdd: Button by lazy { findViewById(R.id.ib_add) }
    val btnPost: Button by lazy { findViewById(R.id.postBtn) }
    val txtTitle: EditText by lazy { findViewById(R.id.postTitleEt) }
    val txtDesc: EditText by lazy { findViewById(R.id.postDescriptionEt) }
    val txtIng: EditText by lazy { findViewById(R.id.postIngredientEt) }
    val imgAdd: ImageView by lazy { findViewById(R.id.post_img) }
    var FilePathUri: Uri? = null
    val storageReference: StorageReference by lazy { FirebaseStorage.getInstance().reference }
    val databaseReference: DatabaseReference by lazy { FirebaseDatabase.getInstance().reference }
    val Image_Request_Code = 7
    val progressDialog: ProgressDialog by lazy { ProgressDialog(this) }

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

        viewBinding.ibAdd.setOnClickListener(View.OnClickListener {
            val i = Intent()
            i.type = "Post Image/*"
            i.action = Intent.ACTION_OPEN_DOCUMENT
            createPostResultLauncher.launch(Intent.createChooser(i, "Select Picture"))
        })

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
}
