package com.mobdeve.s11.group16.foodstop

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.mobdeve.s11.group16.foodstop.databinding.MyaccountLayoutBinding

class UserAccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        val viewBinding : MyaccountLayoutBinding = MyaccountLayoutBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        if(user == null){
            val intent : Intent = Intent(this@UserAccountActivity, LoginActivity::class.java)
            this.startActivity(intent)
        }

        viewBinding.btnLogout.setOnClickListener(View.OnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent : Intent = Intent(this@UserAccountActivity, LoginActivity::class.java)
            this.startActivity(intent)
        })

        viewBinding.btnSave.setOnClickListener(View.OnClickListener {
            val intent : Intent = Intent()

            intent.putExtra(Keys.USERNAME_KEY.name, viewBinding.tvEditName.editText.toString())
            intent.putExtra(Keys.EMAIL_KEY.name, viewBinding.tvEditEmail.editText.toString())
            intent.putExtra(Keys.PASSWORD_KEY.name, viewBinding.tvEditPass.editText.toString())

            setResult(Activity.RESULT_OK, intent)

            finish()
        })

        viewBinding.btnLogout.setOnClickListener(View.OnClickListener {
            val intent : Intent = Intent(this@UserAccountActivity, LoginActivity::class.java)
            this.startActivity(intent)
        })
    }
}