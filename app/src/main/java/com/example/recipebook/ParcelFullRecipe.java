package com.example.recipebook;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.recipebook.Entity.FullRecipe;
import com.example.recipebook.Entity.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class ParcelFullRecipe implements Parcelable {

    private String recipeName;
    private String areaName;
    private String categoryName;
    private String description;
    private String image;
    private String youtubeLink;


    public ParcelFullRecipe(String recipeName,
                            String areaName,
                            String categoryName,
                            String description,
                            String image,
                            String youtubeLink) {
        this.recipeName = recipeName;
        this.areaName = areaName;
        this.categoryName = categoryName;
        this.description = description;
        this.image = image;
        this.youtubeLink = youtubeLink;
    }

    public static final Creator<ParcelFullRecipe> CREATOR = new Creator<ParcelFullRecipe>() {
        @Override
        public ParcelFullRecipe createFromParcel(Parcel source) {
            String recipeName = source.readString();
            String areaName = source.readString();
            String categoryName = source.readString();
            String description = source.readString();
            String image = source.readString();
            String youtubeLink = source.readString();

            return new ParcelFullRecipe(recipeName,
                    areaName,
                    categoryName,
                    description,
                    image,
                    youtubeLink);
        }

        @Override
        public ParcelFullRecipe[] newArray(int size) {
            return new ParcelFullRecipe[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(recipeName);
        dest.writeString(areaName);
        dest.writeString(categoryName);
        dest.writeString(description);
        dest.writeString(image);
        dest.writeString(youtubeLink);
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getAreaName() {
        return areaName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

}
