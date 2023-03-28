package com.mobdeve.s11.group16.foodstop

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.mobdeve.s11.group16.foodstop.databinding.SignupLayoutBinding

class SignUpActivity : AppCompatActivity() {
    private val auth = FirebaseAuth.getInstance()

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser: FirebaseUser? = auth.currentUser
        if (currentUser != null) {
            val intent : Intent = Intent(this@SignUpActivity, MainActivity::class.java)
            this.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewBinding: SignupLayoutBinding = SignupLayoutBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.registerbtn.setOnClickListener(View.OnClickListener {
            if (viewBinding.email.toString().isNotEmpty() && viewBinding.password.toString().isNotEmpty()) {
                val intent: Intent = Intent(this@SignUpActivity, LoginActivity::class.java)

                intent.putExtra(Keys.EMAIL_KEY.name, viewBinding.email.text.toString())
                intent.putExtra(Keys.PASSWORD_KEY.name, viewBinding.password.text.toString())

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

        viewBinding.registerbtn.setOnClickListener(View.OnClickListener {
            var email = viewBinding.email.text.toString()
            var password = viewBinding.password.text.toString()

            if(TextUtils.isEmpty(email)){
                Toast.makeText(this@SignUpActivity, "Enter Your Email", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            if(TextUtils.isEmpty(password)){
                Toast.makeText(this@SignUpActivity, "Enter Your Password", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }


            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this@SignUpActivity , "Registration Successful",
                            Toast.LENGTH_SHORT).show()
                    }

                    else {
                        Toast.makeText(this@SignUpActivity , "Registration Failed",
                            Toast.LENGTH_SHORT).show()
                    }
                }


        })

        viewBinding.startsingupbtn.setOnClickListener(View.OnClickListener {
            val intent : Intent = Intent(this@SignUpActivity, LoginActivity::class.java)
            this.startActivity(intent)
        })
    }
}