package com.mobdeve.s11.group16.foodstop

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.mobdeve.s11.group16.foodstop.databinding.MyaccountLayoutBinding

class UserAccountActivity : AppCompatActivity() {
    private val databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://foodstop-9c45c-default-rtdb.firebaseio.com")
    private lateinit var usernameEt : TextInputLayout
    private lateinit var emailEt : TextInputLayout
    private lateinit var passwordEt : TextInputLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewBinding : MyaccountLayoutBinding = MyaccountLayoutBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        var username = viewBinding.tvEditName.editText?.text.toString()
        var email = viewBinding.tvEditEmail.editText?.text.toString()
        var password = viewBinding.tvEditPass.editText?.text.toString()

        this.usernameEt = findViewById(R.id.tv_editName)
        this.emailEt = findViewById(R.id.tv_editEmail)
        this.passwordEt = findViewById(R.id.tv_editPass)

        databaseReference.child("Users").addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.hasChild(username)) {
                    username = dataSnapshot.child("Users").child(username).child(username).value.toString()
                    email = dataSnapshot.child("Users").child(username).child(email).value.toString()
                    password = dataSnapshot.child("Users").child(username).child(password).value.toString()

                    usernameEt.editText!!.setText(username)
                    emailEt.editText!!.setText(email)
                    passwordEt.editText!!.setText(password)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        viewBinding.btnLogout.setOnClickListener(View.OnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent : Intent = Intent(this@UserAccountActivity, LoginActivity::class.java)
            this.startActivity(intent)
        })

        viewBinding.btnSave.setOnClickListener(View.OnClickListener {
            var username = viewBinding.tvEditName.toString()
            var email = viewBinding.tvEditEmail.toString()
            var password = viewBinding.tvEditPass.toString()

        })
    }
}