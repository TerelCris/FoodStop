package com.mobdeve.s11.group16.foodstop

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.mobdeve.s11.group16.foodstop.databinding.MyaccountLayoutBinding

class UserAccountActivity : AppCompatActivity() {
    private val databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://foodstop-9c45c-default-rtdb.firebaseio.com")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        val viewBinding : MyaccountLayoutBinding = MyaccountLayoutBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

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