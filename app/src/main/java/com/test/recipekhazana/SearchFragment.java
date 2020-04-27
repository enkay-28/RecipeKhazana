package com.test.recipekhazana;

import android.content.Intent;
import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SearchFragment extends Fragment implements RecyclerViewAdapterSearch.OnRecyclerViewClickListener{
    private RecyclerView recyclerView;
    private String[] ingImages;
    private String[]  ingNames;
    RecyclerViewAdapterSearch adapter;
    private ArrayList<Recipe> allData;
    private ArrayList<Recipe> sorted;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View v =  inflater.inflate(R.layout.fragment_search, container, false);
        ingImages = getResources().getStringArray(R.array.ingredient_imgs);
        ingNames =  getResources().getStringArray(R.array.ingredient_names);
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> images = new ArrayList<>();
        for(String r : ingNames){
            names.add(r);
        }
        for(String x : ingImages){
            images.add(x);
        }
       // System.out.println(allData.get(0).getName());
        sorted = new ArrayList<Recipe>();
        allData = getActivity().getIntent().getParcelableArrayListExtra("data");

            recyclerView = v.findViewById(R.id.recycler_view_search);
           adapter = new RecyclerViewAdapterSearch(getContext(), names, images, this);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false));
            recyclerView.setAdapter(adapter);

            SearchView searchView = v.findViewById(R.id.searchBar);
           searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
               @Override
               public boolean onQueryTextSubmit(String s) {
                   return false;
               }

               @Override
               public boolean onQueryTextChange(String s) {
                   adapter.getFilter().filter(s);
                   return false;
               }
           });


       return v;
    }
    @Override
    public void onItemClick(int position) {
        sorted.clear();
        String toFind = adapter.getTitleRecipe().get(position);
        for(Recipe r : allData){
            if(r.getMainIngredient().equalsIgnoreCase(toFind)){
                sorted.add(r);
            }
        }


        Intent intent = new Intent(getActivity(),IngredientDishes.class);
        intent.putParcelableArrayListExtra("data",sorted);
        intent.putExtra("ing",toFind);
        startActivity(intent);
        /*
        for( Recipe obj : sorted){
            dishNames.add(obj.getName());
            imagesUrl.add(obj.getImgURL());
            servings.add(obj.getServings());
            calories.add(obj.getCalories());
            ingredients.add(obj.getIngredients());
            directions.add(obj.getInstructions());
        }*/
        //getFragmentManager().beginTransaction().replace(R.id.frame_layout,new RecipeFragment(sorted)).commit();

    }
}
