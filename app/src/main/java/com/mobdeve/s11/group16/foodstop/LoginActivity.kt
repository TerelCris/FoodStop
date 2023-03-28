package com.mobdeve.s11.group16.foodstop

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.mobdeve.s11.group16.foodstop.databinding.LoginLayoutBinding


class LoginActivity : AppCompatActivity() {
    private val auth = FirebaseAuth.getInstance()

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser: FirebaseUser? = auth.currentUser
        if (currentUser != null) {
            val intent : Intent = Intent(this@LoginActivity, MainActivity::class.java)
            this.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewBinding : LoginLayoutBinding = LoginLayoutBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        /*viewBinding.loginbtn.setOnClickListener(View.OnClickListener {
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
        })*/

        viewBinding.startsingupbtn.setOnClickListener(View.OnClickListener {
            val intent : Intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            this.startActivity(intent)
        })

        viewBinding.loginbtn.setOnClickListener(View.OnClickListener {
            var username = viewBinding.username.text.toString()
            var password = viewBinding.password.text.toString()

            if(TextUtils.isEmpty(username)){
                Toast.makeText(this@LoginActivity, "Enter Your Email", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            if(TextUtils.isEmpty(password)){
                Toast.makeText(this@LoginActivity, "Enter Your Username", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            auth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val intent : Intent = Intent(this@LoginActivity, MainActivity::class.java)
                        this.startActivity(intent)
                        Toast.makeText(baseContext, "Login Successful",
                            Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(baseContext, "Account cannot log in",
                            Toast.LENGTH_SHORT).show()

                    }
                }

        })



    }
}