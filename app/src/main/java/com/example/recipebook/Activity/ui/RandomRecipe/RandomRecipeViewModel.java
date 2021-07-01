package com.example.recipebook.Activity.ui.RandomRecipe;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RandomRecipeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RandomRecipeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("GET RANDOM RECIPE");
    }

    public LiveData<String> getText() {
        return mText;
    }
}