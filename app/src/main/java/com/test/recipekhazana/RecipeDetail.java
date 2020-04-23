package com.test.recipekhazana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecipeDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        ImageView foodImage = findViewById(R.id.circleRecipe);
        TextView dishNameTextView = findViewById(R.id.dishName);
        TextView servingText = findViewById(R.id.serveText);
        TextView calorieText = findViewById(R.id.calText);
        TextView ingredientText = findViewById(R.id.ingText);
        TextView directionsText = findViewById(R.id.dirText);
        ImageView back = findViewById(R.id.backToMainButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent receivedIntent = getIntent();
        Picasso.get().load(receivedIntent.getStringExtra("image")).into(foodImage);
        dishNameTextView.setText(receivedIntent.getStringExtra("name"));


        servingText.setText("Serving : "+receivedIntent.getIntExtra(("serving"),0));
        calorieText.setText("Calories : "+receivedIntent.getIntExtra("calorie",0));
        ingredientText.setText(receivedIntent.getStringExtra("ingredient"));
        directionsText.setText(receivedIntent.getStringExtra("directions"));


    }
}
