package com.example.recipebook.Activity.ui.RandomRecipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.recipebook.Activity.RecipeDetailsActivity;
import com.example.recipebook.Entity.FullRecipe;
import com.example.recipebook.Entity.Ingredient;
import com.example.recipebook.FragmentsCommonController;
import com.example.recipebook.ParcelFullRecipe;
import com.example.recipebook.RecipeDatabase;
import com.example.recipebook.databinding.RandomRecipeFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class RandomRecipeFragment extends Fragment {

    private RandomRecipeViewModel randomRecipeViewModel;
    private RandomRecipeFragmentBinding binding;
    private RecipeDatabase recipeDatabase;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        randomRecipeViewModel =
                new ViewModelProvider(this).get(RandomRecipeViewModel.class);

        binding = RandomRecipeFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recipeDatabase = RecipeDatabase.getInMemoryDatabase(getContext());

        final TextView textView = binding.getRandomRecipeButton;
        randomRecipeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRandomRecipe(v);
            }
        });
        return root;
    }

    private void getRandomRecipe(View view) {

        FullRecipe randomRecipe = recipeDatabase.recipeDAO().getRandomFullRecipe();

        List<Ingredient> ingredientList =
                recipeDatabase
                        .recipeDAO()
                        .getRecipeWithIngredientsByID(randomRecipe.recipeMain.id)
                        .get(0)
                        .ingredients;

        ArrayList<String> ingredientNameList =
                (ArrayList<String>) FragmentsCommonController.getNameFromIngredientList(ingredientList);
        ArrayList<String> measureList =
                (ArrayList<String>) recipeDatabase
                        .recipeDAO()
                        .getMeasureForRecipe(randomRecipe.recipeMain.id);

        Intent intent = new Intent(requireContext(), RecipeDetailsActivity.class);
        ParcelFullRecipe parcelFullRecipe = new ParcelFullRecipe(randomRecipe.recipeMain.recipeName,
                randomRecipe.area.name,
                randomRecipe.category.name,
                randomRecipe.recipeMain.description,
                randomRecipe.recipeMain.image,
                randomRecipe.recipeMain.youtubeLink);
        intent.putExtra(ParcelFullRecipe.class.getSimpleName(), parcelFullRecipe);
        intent.putStringArrayListExtra("ingredient_list", ingredientNameList);
        intent.putStringArrayListExtra("measure_list", measureList);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}