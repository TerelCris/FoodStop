package com.mobdeve.s11.group16.foodstop

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class PostDetailActivity : AppCompatActivity() {

    private lateinit var title: TextView
    private lateinit var author: TextView
    private lateinit var date: TextView
    private lateinit var description: TextView
    private lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_layout)

        title = findViewById(R.id.titleTv)
        author = findViewById(R.id.userTv)
        date = findViewById(R.id.datePostTv)
        description = findViewById(R.id.bodyTv)
        image = findViewById(R.id.postIv)

        Picasso.get().load(intent.getStringExtra("image"))
            .placeholder(R.drawable.katsudonjapanesepork)
            .into(image)

        title.text = intent.getStringExtra("title")
        author.text = intent.getStringExtra("author")
        date.text = intent.getStringExtra("date")
        description.text = intent.getStringExtra("description")
    }
}
