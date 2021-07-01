package com.example.recipebook;

import com.example.recipebook.Entity.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class FragmentsCommonController {

    static public List<String> getNameFromIngredientList(List<Ingredient> ingredientList) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < ingredientList.size(); i++) {
            result.add(ingredientList.get(i).name);
        }
        return result;
    }
}
