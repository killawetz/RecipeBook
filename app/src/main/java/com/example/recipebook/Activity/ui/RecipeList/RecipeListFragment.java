package com.example.recipebook.Activity.ui.RecipeList;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.recipebook.Activity.RecipeDetailsActivity;
import com.example.recipebook.Entity.FullRecipe;
import com.example.recipebook.Entity.Ingredient;
import com.example.recipebook.FragmentsCommonController;
import com.example.recipebook.ParcelFullRecipe;
import com.example.recipebook.RecipeDatabase;
import com.example.recipebook.RecyclerAdapter;
import com.example.recipebook.databinding.RecipeListFragmentBinding;

import java.util.ArrayList;
import java.util.List;


public class RecipeListFragment extends Fragment {

    private RecipeListViewModel homeViewModel;
    private RecipeListFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(RecipeListViewModel.class);

        binding = RecipeListFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RecyclerView recyclerView = binding.recyclerView;

        RecipeDatabase recipeDatabase = RecipeDatabase.getInMemoryDatabase(getContext());


        List<FullRecipe> recipeList = recipeDatabase.recipeDAO().getFullRecipes();
        RecyclerAdapter.OnRecipeClickListener onRecipeClickListener = new RecyclerAdapter.OnRecipeClickListener(){
            @Override
            public void onRecipeClick(FullRecipe recipe, int position) {
                Toast.makeText(requireContext(), "current recipe " + recipe.recipeMain.recipeName, Toast.LENGTH_SHORT).show();
                List<Ingredient> ingredientList =
                        recipeDatabase.recipeDAO().getRecipeWithIngredientsByID(recipe.recipeMain.id).get(0).ingredients;

                ArrayList<String> ingredientNameList =
                        (ArrayList<String>) FragmentsCommonController
                                .getNameFromIngredientList(ingredientList);
                ArrayList<String> measureList = (ArrayList<String>) recipeDatabase.recipeDAO().getMeasureForRecipe(recipe.recipeMain.id);

                Intent intent = new Intent(requireContext(), RecipeDetailsActivity.class);
                ParcelFullRecipe parcelFullRecipe = new ParcelFullRecipe(recipe.recipeMain.recipeName,
                        recipe.area.name,
                        recipe.category.name,
                        recipe.recipeMain.description,
                        recipe.recipeMain.image,
                        recipe.recipeMain.youtubeLink);
                intent.putExtra(ParcelFullRecipe.class.getSimpleName(), parcelFullRecipe);
                intent.putStringArrayListExtra("ingredient_list", ingredientNameList);
                intent.putStringArrayListExtra("measure_list", measureList);
                startActivity(intent);

            }
        };

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(recipeList, onRecipeClickListener);
        recyclerView.setAdapter(recyclerAdapter);

        // разделитель для RecyclerView
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                recyclerView.getContext(),
                DividerItemDecoration.VERTICAL
        );
        recyclerView.addItemDecoration(dividerItemDecoration);

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}