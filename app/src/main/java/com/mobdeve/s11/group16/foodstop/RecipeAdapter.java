package com.mobdeve.s11.group16.foodstop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    Context context;
    List<RecipeModel>  recipeModelList;

    public RecipeAdapter(Context context, List<RecipeModel> recipeModelList) {
        this.context = context;
        this.recipeModelList = recipeModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_layout, parent, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.ViewHolder holder, int position) {

        RecipeModel recipeModel=recipeModelList.get(position);
        holder.txtDate.setText(recipeModel.getDate());
        holder.txtTitle.setText(recipeModel.getTitle());
        holder.txtUser.setText(recipeModel.getUsername());

        String imageUri=null;
        imageUri=recipeModel.getImage();
        Picasso.get().load(imageUri).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return recipeModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView txtTitle,  txtDate, txtUser;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtDate = itemView.findViewById(R.id.tv_date);
            imageView = itemView.findViewById(R.id.iv_cover);
            txtTitle = itemView.findViewById(R.id.tv_title);
            txtUser = itemView.findViewById(R.id.tv_author);



        }
    }
}
