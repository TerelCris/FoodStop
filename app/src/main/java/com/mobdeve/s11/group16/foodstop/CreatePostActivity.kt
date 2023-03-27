package com.mobdeve.s11.group16.foodstop

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s11.group16.foodstop.databinding.CreatePostLayoutBinding
import com.mobdeve.s11.group16.foodstop.databinding.PostLayoutBinding
import com.mobdeve.s11.group16.foodstop.databinding.RvLayoutBinding
import com.squareup.picasso.Picasso

class CreatePostActivity : AppCompatActivity() {
    private lateinit var viewBinding : RvLayoutBinding
    private lateinit var postBinding : PostLayoutBinding
    private var addImage: Uri? = null

    private val createPostResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            try {
                if (result.data != null) {
                    addImage = result.data!!.data
                    Picasso.get().load(addImage).into(viewBinding.ivCover)
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

        viewBinding.ibAdd.setOnClickListener(View.OnClickListener {
            val i = Intent()
            i.type = "Post Image/*"
            i.action = Intent.ACTION_OPEN_DOCUMENT
            createPostResultLauncher.launch(Intent.createChooser(i, "Select Picture"))
        })

        viewBinding.postBtn.setOnClickListener(View.OnClickListener {
            if(viewBinding.postTitleEt.text.toString().isNotEmpty() && viewBinding.postDescriptionEt.text.toString().isNotEmpty() && viewBinding.postIngredientEt.text.toString().isNotEmpty() && viewBinding.postProcedureEt.text.toString().isNotEmpty()){
                val intent : Intent = Intent()

                intent.putExtra(Keys.TITLE_KEY.name, viewBinding.postTitleEt.text.toString())
                intent.putExtra(Keys.DESCRIPTION_KEY.name, viewBinding.postDescriptionEt.text.toString())
                intent.putExtra(Keys.INGREDIENT_KEY.name, viewBinding.postIngredientEt.text.toString())
                intent.putExtra(Keys.PROCEDURE_KEY.name, viewBinding.postProcedureEt.text.toString())

                setResult(Activity.RESULT_OK, intent)

                finish()
            }

            else{
                Toast.makeText(
                    this@CreatePostActivity,
                    "Please complete all of the required fields",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}
