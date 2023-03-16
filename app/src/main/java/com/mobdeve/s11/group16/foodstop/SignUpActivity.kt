package com.mobdeve.s11.group16.foodstop

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s11.group16.foodstop.databinding.SignupLayoutBinding

class SignUpActivity : AppCompatActivity() {
    companion object{
        const val USERNAME_KEY = "USERNAME_KEY"
        const val EMAIL_KEY = "EMAIL_KEY"
        const val PASSWORD_KEY = "PASSWORD_KEY"
        const val REPASS_KEY = "REPASS_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewBinding: SignupLayoutBinding = SignupLayoutBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.registerbtn.setOnClickListener(View.OnClickListener {
            if (viewBinding.username.toString().isNotEmpty() && viewBinding.email.toString().isNotEmpty() && viewBinding.password.toString().isNotEmpty() && viewBinding.repassword.toString().isNotEmpty()) {
                val intent: Intent = Intent(this@SignUpActivity, LoginActivity::class.java)

                intent.putExtra(SignUpActivity.USERNAME_KEY, viewBinding.username.text.toString())
                intent.putExtra(SignUpActivity.EMAIL_KEY, viewBinding.email.text.toString())
                intent.putExtra(SignUpActivity.PASSWORD_KEY, viewBinding.password.text.toString())
                intent.putExtra(SignUpActivity.REPASS_KEY, viewBinding.repassword.text.toString())

                setResult(Activity.RESULT_OK, intent)

                finish()
            }

            else{
                Toast.makeText(
                    this@SignUpActivity,
                    "Please complete all required fields",
                    Toast.LENGTH_LONG
                ).show()
            }
        })

        viewBinding.startsingupbtn.setOnClickListener(View.OnClickListener {
            val intent : Intent = Intent(this@SignUpActivity, LoginActivity::class.java)
            this.startActivity(intent)
        })
    }
}