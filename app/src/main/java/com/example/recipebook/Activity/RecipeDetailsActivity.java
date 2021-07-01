package com.example.recipebook.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipebook.ParcelFullRecipe;
import com.example.recipebook.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecipeDetailsActivity extends AppCompatActivity {

    TextView titleDetailsTextView;
    ImageView imageDetailsView;
    TextView areaDetailsTextView;
    TextView categoryDetailsTextView;
    TextView descriptionDetailsText;
    TextView ingredientListTextView;
    private ParcelFullRecipe parcelFullRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        parcelFullRecipe = getIntent().getParcelableExtra(ParcelFullRecipe.class.getSimpleName());

        titleDetailsTextView = findViewById(R.id.detailsTitleText);
        imageDetailsView = findViewById(R.id.detailsImageView);
        areaDetailsTextView = findViewById(R.id.detailsAreaText);
        categoryDetailsTextView = findViewById(R.id.detailsCategoryText);
        descriptionDetailsText = findViewById(R.id.detailsDescriptionText);
        ingredientListTextView = findViewById(R.id.ingredientListTextView);

        titleDetailsTextView.setText(parcelFullRecipe.getRecipeName());
        areaDetailsTextView.setText(getString(R.string.area_details_text, parcelFullRecipe.getAreaName()));
        categoryDetailsTextView.setText(getString(R.string.category_details_text, parcelFullRecipe.getCategoryName()));
        descriptionDetailsText.setText(getString(R.string.description_details_text, parcelFullRecipe.getDescription()));
        Picasso
                .get()
                .load(parcelFullRecipe.getImage())
                .placeholder(R.mipmap.ic_launcher)
                .into(imageDetailsView);

        Intent intent = getIntent();
        ArrayList<String> ingredientNameList = intent.getStringArrayListExtra("ingredient_list");
        ArrayList<String> measureList = intent.getStringArrayListExtra("measure_list");

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < ingredientNameList.size(); i++) {
            stringBuilder.append(ingredientNameList.get(i) + "....................." + measureList.get(i));
            stringBuilder.append("\n");

        }
        ingredientListTextView.setText(stringBuilder.toString());

    }

    public void goToYouTube(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(parcelFullRecipe.getYoutubeLink())));
    }
}