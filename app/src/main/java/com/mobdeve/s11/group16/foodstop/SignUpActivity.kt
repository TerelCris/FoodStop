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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mobdeve.s11.group16.foodstop.databinding.SignupLayoutBinding

class SignUpActivity : AppCompatActivity() {
    private val databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://foodstop-9c45c-default-rtdb.firebaseio.com")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewBinding: SignupLayoutBinding = SignupLayoutBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.registerbtn.setOnClickListener(View.OnClickListener {
            var username = viewBinding.username.text.toString()
            var email = viewBinding.email.text.toString()
            var password = viewBinding.password.text.toString()

            if(username.isEmpty() && email.isEmpty() && password.isEmpty()){
                Toast.makeText(this@SignUpActivity, "Please answer all required fields", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            else{
                databaseReference.child("Users").addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if(dataSnapshot.hasChild(username)){
                            Toast.makeText(this@SignUpActivity, "Username is already used", Toast.LENGTH_SHORT).show()
                        } else if (dataSnapshot.children.any { it.child("Email").value == email }) {
                            Toast.makeText(this@SignUpActivity, "Email is already used", Toast.LENGTH_SHORT).show()
                        } else {
                            databaseReference.child("Users").child(username).child("Username").setValue(username)
                            databaseReference.child("Users").child(username).child("Email").setValue(email)
                            databaseReference.child("Users").child(username).child("Password").setValue(password)

                            Toast.makeText(this@SignUpActivity, "Registration Successful", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                    }
                })
            }
        })

        viewBinding.startsingupbtn.setOnClickListener(View.OnClickListener {
            val intent : Intent = Intent(this@SignUpActivity, LoginActivity::class.java)
            this.startActivity(intent)
        })
    }
}