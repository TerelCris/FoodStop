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
    var currentUsername: String? = null
    var currentEmail: String? = null
    var currentPassword: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        currentUsername = intent.getStringExtra("username")

        val viewBinding: MyaccountLayoutBinding = MyaccountLayoutBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        this.usernameEt = findViewById(R.id.tv_editName)
        this.emailEt = findViewById(R.id.tv_editEmail)
        this.passwordEt = findViewById(R.id.tv_editPass)

        // Query the user's data using the currentUsername variable as the key
        databaseReference.child("Users").child(currentUsername!!).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                currentEmail = dataSnapshot.child("Email").value.toString()
                currentPassword = dataSnapshot.child("Password").value.toString()

                // Set the text of the EditText fields to the user's data
                usernameEt.editText!!.setText(currentUsername)
                usernameEt.isEnabled = false // Disable editing on the username field
                emailEt.editText!!.setText(currentEmail)
                passwordEt.editText!!.setText(currentPassword)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle errors
            }
        })

        viewBinding.btnLogout.setOnClickListener(View.OnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent: Intent = Intent(this@UserAccountActivity, LoginActivity::class.java)
            this.startActivity(intent)
        })

        viewBinding.btnSave.setOnClickListener(View.OnClickListener {
            // Get the updated user data from the EditText fields
            var email = emailEt.editText!!.text.toString()
            var password = passwordEt.editText!!.text.toString()

            // Update the user's data in the database
            databaseReference.child("Users").child(currentUsername!!).child("Email").setValue(email)
            databaseReference.child("Users").child(currentUsername!!).child("Password").setValue(password)

            Toast.makeText(this@UserAccountActivity, "Changes saved successfully", Toast.LENGTH_SHORT).show()
        })
    }
}