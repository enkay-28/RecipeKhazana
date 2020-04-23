package com.test.recipekhazana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;
    Fragment fragment;
    //private final String url = "https://raw.githubusercontent.com/coolboyhs10/test/master/db-recipes.json";
    //JsonArrayRequest request;
    //private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //firestoreDatabase = FirebaseFirestore.getInstance();
       // requestQueue = Volley.newRequestQueue(this);
        //jsonParse();
        bottomNavigationView = findViewById(R.id.nav_bar);
        frameLayout = findViewById(R.id.frame_layout);



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {

                    case R.id.all_recipe_item:
                        fragment = new RecipeFragment();
                        getSupportFragmentManager().beginTransaction().
                                replace(R.id.frame_layout,fragment).commit();
                        return true;


                    case R.id.search_recipe_item:
                        return true;



                    case R.id.profile_item:
                        return true;

                    default: return false;

                }

            }
        });

        bottomNavigationView.setSelectedItemId(R.id.all_recipe_item);
    }


/*
    private void jsonParse(){
        request = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    JSONObject jsonObject = null;
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                jsonObject = response.getJSONObject(i);
                                String name = jsonObject.getString("name");
                                String instructions = jsonObject.getString("instructions");
                                int calories = jsonObject.getInt("calories");
                                int servings = jsonObject.getInt("servings");
                                JSONArray ingredients = jsonObject.getJSONArray("ingredients");
                                List<String> ingredientsR = new ArrayList<>();
                                for(int j = 0; j < ingredients.length(); j++)
                                    ingredientsR.add(ingredients.getString(j));

                                JSONArray tags = jsonObject.getJSONArray("tags");
                                List<String> tagsR = new ArrayList<>();
                                for(int j = 0; j < tags.length(); j++)
                                    tagsR.add(tags.getString(j));

                                Recipe r = new Recipe(name, instructions, calories,servings, ingredientsR, tagsR);
                                firestoreDatabase.collection("recipe")
                                        .add(r)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(getApplicationContext(),"COULD NOT ADD",Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                //Toast.makeText(getApplicationContext(),jsonObject.getString("name"),Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }*/
}
