package com.mobdeve.s11.group16.foodstop

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s11.group16.foodstop.databinding.MyaccountLayoutBinding

class UserAccountActivity : AppCompatActivity() {
    companion object{
        const val NAME_KEY = "NAME_KEY"
        const val EMAIL_KEY = "EMAIL_KEY"
        const val PASSWORD_KEY = "PASSWORD_KEY"
        const val POSITION_KEY = "POSITION_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewBinding : MyaccountLayoutBinding = MyaccountLayoutBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnSave.setOnClickListener(View.OnClickListener {
            val intent : Intent = Intent()

            intent.putExtra(UserAccountActivity.NAME_KEY, viewBinding.tvEditName.editText.toString())
            intent.putExtra(UserAccountActivity.EMAIL_KEY, viewBinding.tvEditEmail.editText.toString())
            intent.putExtra(UserAccountActivity.PASSWORD_KEY, viewBinding.tvEditPass.editText.toString())

            setResult(Activity.RESULT_OK, intent)

            finish()
        })
    }
}