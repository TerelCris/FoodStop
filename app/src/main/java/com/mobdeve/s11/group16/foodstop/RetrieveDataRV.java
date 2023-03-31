package com.mobdeve.s11.group16.foodstop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.mobdeve.s11.group16.foodstop.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class RetrieveDataRV extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference ref;
    private FirebaseStorage storage;
    RecyclerView recyclerView;
    RecipeAdapter recipeAdapter;
    List<RecipeModel> recipeMDList;

    private String currentUsername = null;
    private String currentEmail = null;
    private String currentPassword = null;
   
    
    


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        
        ActivityMainBinding viewBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot()); // set the root view of the inflated layout as content view

        database=FirebaseDatabase.getInstance();
        ref=database.getReference().child("Posts");
        storage=FirebaseStorage.getInstance();
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recipeMDList=new ArrayList<RecipeModel>();
        recipeAdapter=new RecipeAdapter(RetrieveDataRV.this, recipeMDList);

        recyclerView.setAdapter(recipeAdapter); // set the adapter here

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                RecipeModel recipeModel=snapshot.getValue(RecipeModel.class);
                recipeMDList.add(recipeModel);
                recipeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            currentUsername = intent.getStringExtra("username");
            currentEmail = intent.getStringExtra("email");
            currentPassword = intent.getStringExtra("password");
        }


        viewBinding.ibCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RetrieveDataRV.this, CreatePostActivity.class);
                intent.putExtra("username", currentUsername);
                startActivity(intent);
            }
        });


        viewBinding.btnFaved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RetrieveDataRV.this, FavoritesActivity.class);
                startActivity(intent);
            }
        });

        viewBinding.ibUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RetrieveDataRV.this, UserAccountActivity.class);
                intent.putExtra("username", currentUsername);
                intent.putExtra("email", currentEmail);
                intent.putExtra("password", currentPassword);
                startActivity(intent);
            }
        });

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(viewBinding.recyclerView);
    }
}

