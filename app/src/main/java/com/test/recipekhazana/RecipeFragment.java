package com.test.recipekhazana;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.zip.Inflater;


public class RecipeFragment extends Fragment implements RecyclerViewAdapter.OnRecyclerViewClickListener {
    private RecyclerView recyclerView;
    private ViewPager viewPager;

    private String[] viewPagerTitles;
    private String[] viewPagerImages;

    private ArrayList<Recipe> dataList;

    private ArrayList<String> dishNames;
    private ArrayList<String> imagesUrl;
    private ArrayList<Integer> calories;
    private ArrayList<Integer> servings ;
    private ArrayList<List<String>> ingredients;
    private ArrayList<String> directions;

    public RecipeFragment(ArrayList<Recipe> dataList) {
        this.dataList = dataList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //dishNames = getActivity().getIntent().getStringArrayExtra("names");
        //imagesUrl = getActivity().getIntent().getStringArrayExtra("pics");
        dishNames = new ArrayList<>();
        imagesUrl = new ArrayList<>();
        servings = new ArrayList<>();
        calories = new ArrayList<>();
        ingredients = new ArrayList<>();
        directions = new ArrayList<>();

        //dataList = getActivity().getIntent().getParcelableArrayListExtra("data");
        for( Recipe obj : dataList){
            dishNames.add(obj.getName());
            imagesUrl.add(obj.getImgURL());
            servings.add(obj.getServings());
            calories.add(obj.getCalories());
            ingredients.add(obj.getIngredients());
            directions.add(obj.getInstructions());
        }





        View v = inflater.inflate(R.layout.fragment_recipe, container, false);
        recyclerView = v.findViewById(R.id.recycler_view);
        viewPager = v.findViewById(R.id.view_pager_recipes);


        viewPagerTitles = new String[]{"Hottest Recipes!", "Choose yourself!", "Quick Hunger Crunch", "All Ingredients"};
        viewPagerImages = new String[4];
        /*
        String a= SimpleDateFormat.getDateInstance().format(new Date());
        String date = a.substring(0,2);*/
        int i = 13;
        int k = 0;
        while(k < 4){
            viewPagerImages[k] = imagesUrl.get(i);
            i++;
            k++;
        }
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(viewPagerTitles,viewPagerImages, getContext());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setPadding(130,0,130,0);
        //viewPagerListener(viewPager);


        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(),dishNames ,imagesUrl ,this);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2,RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(adapter);


        return v;
    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getContext(),RecipeDetail.class);
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
