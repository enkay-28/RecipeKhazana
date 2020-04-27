package com.test.recipekhazana;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class IngredientDishes extends AppCompatActivity implements RecyclerViewAdapter.OnRecyclerViewClickListener {
    ArrayList<Recipe> allData;
    RecyclerView recyclerView;
    TextView heading;
    ImageView back;

    private ArrayList<String> dishNames;
    private ArrayList<String> imagesUrl;
    private ArrayList<Integer> calories;
    private ArrayList<Integer> servings ;
    private ArrayList<List<String>> ingredients;
    private ArrayList<String> directions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_dishes);
        allData = getIntent().getParcelableArrayListExtra("data");
        heading = findViewById(R.id.ing_heading);
        heading.setText(getIntent().getStringExtra("ing")+" Dishes");
        back = findViewById(R.id.backToMainButton2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        dishNames = new ArrayList<>();
        imagesUrl = new ArrayList<>();
        servings = new ArrayList<>();
        calories = new ArrayList<>();
        ingredients = new ArrayList<>();
        directions = new ArrayList<>();

        //dataList = getActivity().getIntent().getParcelableArrayListExtra("data");
        for( Recipe obj : allData){
            dishNames.add(obj.getName());
            imagesUrl.add(obj.getImgURL());
            servings.add(obj.getServings());
            calories.add(obj.getCalories());
            ingredients.add(obj.getIngredients());
            directions.add(obj.getInstructions());
        }
        recyclerView = findViewById(R.id.recycler_view_ing);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, dishNames, imagesUrl,this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2,RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(adapter);

    }
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getApplicationContext(),RecipeDetail.class);
        intent.putExtra("name",dishNames.get(position));
        intent.putExtra("image",imagesUrl.get(position));
        intent.putExtra("calorie",calories.get(position));
        intent.putExtra("serving",servings.get(position));

        StringBuilder ingredient = new StringBuilder();
        for(String l : ingredients.get(position)){
            ingredient.append("• ").append(l).append("\n");
        }
        intent.putExtra("ingredient", ingredient.toString());
        intent.putExtra("directions",directions.get(position));
        startActivity(intent);
    }
}
