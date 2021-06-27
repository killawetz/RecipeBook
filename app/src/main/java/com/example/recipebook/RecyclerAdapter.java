package com.example.recipebook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.recipebook.Entity.FullRecipe;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecipeViewHolder> {
    List<FullRecipe> recipeList;
    private final OnRecipeClickListener onRecipeClickListener;

    public RecyclerAdapter(List<FullRecipe> recipeList, OnRecipeClickListener onRecipeClickListener) {
        this.recipeList = recipeList;
        this.onRecipeClickListener = onRecipeClickListener;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new RecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.RecipeViewHolder holder, int position) {
        FullRecipe currentRecipe = recipeList.get(position);
        holder.recipeNameTextView.setText(currentRecipe.recipeMain.recipeName);
        holder.recipeAreaTextView.setText(String.valueOf(currentRecipe.area.name));
        holder.recipeCategoryTextView.setText(String.valueOf(currentRecipe.category.name));
        Picasso.get().load(currentRecipe.recipeMain.image).placeholder(R.mipmap.ic_launcher).into(holder.recipeImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecipeClickListener.onRecipeClick(currentRecipe, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {
        private final TextView recipeNameTextView;
        private final TextView recipeAreaTextView;
        private final TextView recipeCategoryTextView;
        private final ImageView recipeImageView;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            recipeNameTextView = itemView.findViewById(R.id.textRecipeName);
            recipeAreaTextView = itemView.findViewById(R.id.textRecipeArea);
            recipeCategoryTextView = itemView.findViewById(R.id.textRecipeCategory);
            recipeImageView = itemView.findViewById(R.id.imageRecipe);
        }
    }


    public interface OnRecipeClickListener{
        void onRecipeClick(FullRecipe recipe, int position);
    }
}
