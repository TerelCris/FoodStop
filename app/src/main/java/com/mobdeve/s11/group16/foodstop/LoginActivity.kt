package com.mobdeve.s11.group16.foodstop

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.mobdeve.s11.group16.foodstop.databinding.LoginLayoutBinding

class LoginActivity : AppCompatActivity() {
    companion object{
        const val USERNAME_KEY = "USERNAME_KEY"
        const val PASSWORD_KEY = "PASSWORD_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewBinding : LoginLayoutBinding = LoginLayoutBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.loginbtn.setOnClickListener(View.OnClickListener {
            if(viewBinding.username.toString().isNotEmpty() && viewBinding.password.toString().isNotEmpty()){
                val intent : Intent = Intent(this@LoginActivity, MainActivity::class.java)
                this.startActivity(intent)

                intent.putExtra(Keys.USERNAME_KEY.name, viewBinding.username.text.toString())
                intent.putExtra(Keys.PASSWORD_KEY.name, viewBinding.password.text.toString())

                setResult(Activity.RESULT_OK, intent)

                finish()
            }
            else{
                Toast.makeText(
                    this@LoginActivity,
                    "Please complete all of the required fields",
                    Toast.LENGTH_LONG
                ).show()
            }
        })

        viewBinding.startsingupbtn.setOnClickListener(View.OnClickListener {
            val intent : Intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            this.startActivity(intent)
        })

    }
}