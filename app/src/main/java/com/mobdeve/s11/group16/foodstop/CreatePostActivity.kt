package com.mobdeve.s11.group16.foodstop

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s11.group16.foodstop.databinding.CreatePostLayoutBinding

class CreatePostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding : CreatePostLayoutBinding = CreatePostLayoutBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

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