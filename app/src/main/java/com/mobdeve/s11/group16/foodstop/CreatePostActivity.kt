package com.mobdeve.s11.group16.foodstop

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s11.group16.foodstop.databinding.CreatePostLayoutBinding

class CreatePostActivity : AppCompatActivity() {
    companion object{
        const val TITLE_KEY = "TITLE_KEY"
        const val DESCRIPTION_KEY = "DESCRIPTION_KEY"
        const val INGREDIENT_KEY = "INGREDIENT_KEY"
        const val PROCEDURE_KEY = "PROCEDURE_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding : CreatePostLayoutBinding = CreatePostLayoutBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.postBtn.setOnClickListener(View.OnClickListener {
            if(viewBinding.postTitleEt.text.toString().isNotEmpty() && viewBinding.postDescriptionEt.text.toString().isNotEmpty() && viewBinding.postIngredientEt.text.toString().isNotEmpty() && viewBinding.postProcedureEt.text.toString().isNotEmpty()){
                val intent : Intent = Intent()

                intent.putExtra(CreatePostActivity.TITLE_KEY, viewBinding.postTitleEt.text.toString())
                intent.putExtra(CreatePostActivity.DESCRIPTION_KEY, viewBinding.postDescriptionEt.text.toString())
                intent.putExtra(CreatePostActivity.INGREDIENT_KEY, viewBinding.postIngredientEt.text.toString())
                intent.putExtra(CreatePostActivity.PROCEDURE_KEY, viewBinding.postProcedureEt.text.toString())

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