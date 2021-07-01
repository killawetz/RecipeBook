package com.example.recipebook.Activity.ui.SearchRecipe;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SearchRecipeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SearchRecipeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is search fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}