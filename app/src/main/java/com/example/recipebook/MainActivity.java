package com.example.recipebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.room.Room;

public class MainActivity extends AppCompatActivity {

    TextView mainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainText = findViewById(R.id.mainText);

        RecipeDatabase recipeDatabase =
                Room.databaseBuilder(this, RecipeDatabase.class, "recipebook.db")
                        .allowMainThreadQueries()
                        .createFromAsset("databases/recipebook.db")
                        .build();


        mainText.setText(recipeDatabase.areaDAO().getAllNames().get(0).name);
    }
}