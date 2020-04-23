package com.test.recipekhazana;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.firebase.firestore.Exclude;
import com.google.gson.annotations.SerializedName;

import java.util.List;

    public class Recipe implements Parcelable {
        public String documentId;
        public String name;
        public String mainIngredient;
        public String imgURL;
        public String instructions;
        public int calories;
        int servings;
        List<String> ingredients;
        List<String> tags;

        public Recipe() {
        }

        public Recipe(String name, String instructions, int calories, int servings, List<String> ingredients, List<String> tags,
                      String mainIngredient, String imgURL) {
            this.name = name;
            this.instructions = instructions;
            this.calories = calories;
            this.servings = servings;
            this.ingredients = ingredients;
            this.tags = tags;
            this.imgURL = imgURL;
            this.mainIngredient = mainIngredient;
        }

        protected Recipe(Parcel in) {
            documentId = in.readString();
            name = in.readString();
            mainIngredient = in.readString();
            imgURL = in.readString();
            instructions = in.readString();
            calories = in.readInt();
            servings = in.readInt();
            ingredients = in.createStringArrayList();
            tags = in.createStringArrayList();
        }

        public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
            @Override
            public Recipe createFromParcel(Parcel in) {
                return new Recipe(in);
            }

            @Override
            public Recipe[] newArray(int size) {
                return new Recipe[size];
            }
        };

        public String getMainIngredient() {
            return mainIngredient;
        }

        public void setMainIngredient(String mainIngredient) {
            this.mainIngredient = mainIngredient;
        }

        public String getImgURL() {
            return imgURL;
        }

        public void setImgURL(String imgURL) {
            this.imgURL = imgURL;
        }

        @Exclude
        public String getDocumentId() {
            return documentId;
        }

        public void setDocumentId(String documentId) {
            this.documentId = documentId;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getInstructions() {
            return instructions;
        }

        public void setInstructions(String instructions) {
            this.instructions = instructions;
        }

        public int getCalories() {
            return calories;
        }

        public void setCalories(int calories) {
            this.calories = calories;
        }

        public int getServings() {
            return servings;
        }

        public void setServings(int servings) {
            this.servings = servings;
        }

        public List<String> getIngredients() {
            return ingredients;
        }

        public void setIngredients(List<String> ingredients) {
            this.ingredients = ingredients;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(documentId);
            parcel.writeString(name);
            parcel.writeString(mainIngredient);
            parcel.writeString(imgURL);
            parcel.writeString(instructions);
            parcel.writeInt(calories);
            parcel.writeInt(servings);
            parcel.writeStringList(ingredients);
            parcel.writeStringList(tags);
        }
    }


