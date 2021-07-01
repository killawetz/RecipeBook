package com.example.recipebook.Activity.ui.SearchRecipe;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;

import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipebook.Activity.RecipeDetailsActivity;
import com.example.recipebook.Entity.FullRecipe;
import com.example.recipebook.Entity.Ingredient;
import com.example.recipebook.Entity.RecipeWithIngredients;
import com.example.recipebook.ParcelFullRecipe;
import com.example.recipebook.R;
import com.example.recipebook.RecipeDatabase;
import com.example.recipebook.RecyclerAdapter;
import com.example.recipebook.databinding.SearchRecipeFragmentBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchRecipeFragment extends Fragment {

    private SearchRecipeViewModel searchRecipeViewModel;
    private SearchRecipeFragmentBinding binding;
    private RecipeDatabase recipeDatabase;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchRecipeViewModel =
                new ViewModelProvider(this).get(SearchRecipeViewModel.class);

        binding = SearchRecipeFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RecyclerView selectedRecyclerView = binding.selectedRecyclerView;


        recipeDatabase = RecipeDatabase.getInMemoryDatabase(getContext());
        List<String> ingredientList = recipeDatabase.ingredientDAO().getAllNames();

        IngredientMultiAutoCompleteTextView autoCompleteTextView = binding.searchAutoCompleteTextView;
        ArrayAdapter<String> autoCompleteAdapter =
                new ArrayAdapter<String>(
                        getContext(),
                        R.layout.support_simple_spinner_dropdown_item,
                        ingredientList);
        autoCompleteTextView.setAdapter(autoCompleteAdapter);
        autoCompleteTextView.setTokenizer(new CustomTokenizer());

        RecyclerAdapter.OnRecipeClickListener onRecipeClickListener = new RecyclerAdapter.OnRecipeClickListener(){
            @Override
            public void onRecipeClick(FullRecipe recipe, int position) {
                Toast.makeText(requireContext(), "current recipe " + recipe.recipeMain.recipeName, Toast.LENGTH_SHORT).show();
                List<Ingredient> ingredientList =
                        recipeDatabase.recipeDAO().getRecipeWithIngredientsByID(recipe.recipeMain.id).get(0).ingredients;

                ArrayList<String> ingredientNameList = (ArrayList<String>) getNameFromIngredientList(ingredientList);
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

        autoCompleteTextView.setOnEditorActionListener(new MultiAutoCompleteTextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    autoCompleteTextView.clearFocus();
                    List<String> selectedIngredientsArray = new ArrayList<>(
                            Arrays.asList(autoCompleteTextView.getText().toString().split("\\| ")));
                    Set<String> set = new HashSet<>(selectedIngredientsArray);
                    selectedIngredientsArray.clear();
                    selectedIngredientsArray.addAll(set);
                    List<FullRecipe> suitableRecipes = performSearch(selectedIngredientsArray);

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
                    selectedRecyclerView.setLayoutManager(layoutManager);
                    RecyclerAdapter recyclerAdapter = new RecyclerAdapter(suitableRecipes, onRecipeClickListener);
                    selectedRecyclerView.setAdapter(recyclerAdapter);

                    // разделитель для RecyclerView
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                            selectedRecyclerView.getContext(),
                            DividerItemDecoration.VERTICAL
                    );
                    selectedRecyclerView.addItemDecoration(dividerItemDecoration);
                    return true;
                }
                return false;
            }
        });



        return root;
    }

    private List<FullRecipe>  performSearch(List<String> selectedIngredientsArray) {
        System.out.println("SELECTED LIST " + selectedIngredientsArray);
        List<RecipeWithIngredients> allRecipesList = recipeDatabase.recipeDAO().getRecipeWithIngredients();
        System.out.println("ALL RECIPE LIST " + allRecipesList);
        List<FullRecipe> suitableRecipes = new ArrayList<>();
        for (int i = 0; i < allRecipesList.size(); i++) {
            if (allRecipesList.get(i).ingredients.contains(selectedIngredientsArray)) {
                suitableRecipes.add(
                        recipeDatabase
                                .recipeDAO()
                                .getFullRecipeByID(allRecipesList.get(i).recipe.id));
            }
        }
        System.out.println(suitableRecipes);
        return suitableRecipes;
    }

    private List<String> getNameFromIngredientList(List<Ingredient> ingredientList) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < ingredientList.size(); i++) {
            result.add(ingredientList.get(i).name);
        }
        return result;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}