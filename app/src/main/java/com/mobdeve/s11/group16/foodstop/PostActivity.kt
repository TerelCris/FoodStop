package com.mobdeve.s11.group16.foodstop

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s11.group16.foodstop.databinding.PostLayoutBinding

class PostActivity : AppCompatActivity() {
    companion object{
        const val IMAGE_KEY = "IMAGE_KEY"
        const val TITLE_KEY = "TITLE_KEY"
        const val USER_KEY = "USER_KEY"
        const val DATE_KEY = "DATE_KEY"
        const val BODY_KEY = "BODY_KEY"
        const val POSITION_KEY = "POSITION_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewBinding : PostLayoutBinding = PostLayoutBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.postIv.setImageResource(intent.getIntExtra(PostActivity.IMAGE_KEY, 0))
        viewBinding.titleTv.text = intent.getStringExtra(PostActivity.TITLE_KEY)
        viewBinding.userTv.text = intent.getStringExtra(PostActivity.USER_KEY)
        viewBinding.datePostTv.text = intent.getStringExtra(PostActivity.DATE_KEY)
        viewBinding.bodyTv.text = intent.getStringExtra(PostActivity.BODY_KEY)

        val position = intent.getIntExtra(PostActivity.POSITION_KEY, 0)

        val intent : Intent = Intent()
        intent.putExtra(PostActivity.POSITION_KEY, position)

    }
}