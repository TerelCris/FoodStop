package com.mobdeve.s11.group16.foodstop

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mobdeve.s11.group16.foodstop.databinding.LoginLayoutBinding


class LoginActivity : AppCompatActivity() {
    private val databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://foodstop-9c45c-default-rtdb.firebaseio.com")

    var currentUsername: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewBinding : LoginLayoutBinding = LoginLayoutBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.startsingupbtn.setOnClickListener(View.OnClickListener {
            val intent : Intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            this.startActivity(intent)
        })

        viewBinding.loginbtn.setOnClickListener(View.OnClickListener {
            var username = viewBinding.username.text.toString()
            var password = viewBinding.password.text.toString()

            if(username.isEmpty() && password.isEmpty()){
                Toast.makeText(this@LoginActivity, "Please answer all required fields", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            else{
                databaseReference.child("Users").addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if(dataSnapshot.hasChild(username)){
                            val getPassword = dataSnapshot.child(username).child("Password").value // get the actual password from the database

                            if(getPassword != null && getPassword.equals(password)){ // compare the entered password with the password retrieved from the database
                                currentUsername = username // Save the username
                                Toast.makeText(this@LoginActivity, "Login Successful", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@LoginActivity, CreatePostActivity::class.java)
                                intent.putExtra("username", username)
                                intent.putExtra("fromLogin", true)
                                startActivity(intent)
                            }
                            else{
                                Toast.makeText(this@LoginActivity, "Your Username or Password may be incorrect", Toast.LENGTH_SHORT).show()
                            }
                        }
                        else{
                            Toast.makeText(this@LoginActivity, "Your Username or Password may be incorrect", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                    }
                })

            }
        })
    }
}