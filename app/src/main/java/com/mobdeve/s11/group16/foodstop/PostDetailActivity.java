package com.mobdeve.s11.group16.foodstop;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class PostDetailActivity extends AppCompatActivity {

    TextView title, date, author, description;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_layout);

        title = findViewById(R.id.titleTv);
        author = findViewById(R.id.userTv);
        date = findViewById(R.id.datePostTv);
        description = findViewById(R.id.bodyTv);

        image = findViewById(R.id.postIv);

        Picasso.get().load(getIntent().getStringExtra("image"))
                .placeholder(R.drawable.katsudonjapanesepork)
                .into(image);

        title.setText(getIntent().getStringExtra("title"));
        author.setText(getIntent().getStringExtra("author"));
        date.setText(getIntent().getStringExtra("date"));
        description.setText(getIntent().getStringExtra("description"));

    }
}
