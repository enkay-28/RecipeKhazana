package com.test.recipekhazana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

public class SplashScreen extends AppCompatActivity {
   // private final String url = "https://raw.githubusercontent.com/coolboyhs10/test/master/db-recipes.json";
    //JsonArrayRequest request;
   // private RequestQueue requestQueue;
    Animation topAnim, bottomAnim;
    ImageView logo;
    ArrayList<Recipe> allRecipes;
    TextView appName, tagLine;
    FirebaseFirestore firestoreDatabase;
    String[] dishNames, imagesUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        firestoreDatabase = FirebaseFirestore.getInstance();
        allRecipes = new ArrayList<Recipe>();
        dishNames = new String[50];
        imagesUrl = new String[50];
       // requestQueue = Volley.newRequestQueue(this);

        getfromDataBase();
        //jsonParse();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        logo = findViewById(R.id.logo);
        appName = findViewById(R.id.app_name_main);
        tagLine = findViewById(R.id.tag_line);

        logo.setAnimation(topAnim);
        appName.setAnimation(bottomAnim);
        tagLine.setAnimation(bottomAnim);






    }
    private void getfromDataBase(){

        firestoreDatabase = FirebaseFirestore.getInstance();
        firestoreDatabase.collection("recipe")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        int i = 0;
                        for(DocumentSnapshot d : list){

                            Recipe obj = d.toObject(Recipe.class);
                            allRecipes.add(obj);
                            dishNames[i] = (obj.getName());
                            imagesUrl[i] = (obj.getImgURL());
                            i++;

                        }
                        Intent intent = new Intent(getApplicationContext(),Onboarding.class);
                        intent.putExtra("names",dishNames);
                        intent.putExtra("pics",imagesUrl);
                        intent.putParcelableArrayListExtra("data",allRecipes);

                        startActivity(intent);
                        finish();



                    }
                });
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
                                Recipe r = new Recipe();
                                jsonObject = response.getJSONObject(i);

                                String name = jsonObject.getString("name");
                                r.setName(name);
                                String instructions = jsonObject.getString("instructions");
                                r.setInstructions(instructions);
                                int calories = jsonObject.getInt("calories");
                                r.setCalories(calories);
                                int servings = jsonObject.getInt("servings");
                                r.setServings(servings);
                                String mainIngredient = jsonObject.getString("mainIngredient");
                                r.setMainIngredient(mainIngredient);
                                String imageUrl = jsonObject.getString("image");
                                r.setImgURL(imageUrl);
                                JSONArray ingredients = jsonObject.getJSONArray("ingredients");
                                List<String> ingredientsR = new ArrayList<>();
                                for(int j = 0; j < ingredients.length(); j++)
                                    ingredientsR.add(ingredients.getString(j));
                                r.setIngredients(ingredientsR);

                                JSONArray tags = jsonObject.getJSONArray("tags");
                                List<String> tagsR = new ArrayList<>();
                                for(int j = 0; j < tags.length(); j++)
                                    tagsR.add(tags.getString(j));
                                r.setTags(tagsR);

                                //Recipe r = new Recipe(name, instructions, calories, servings, ingredientsR, tagsR);
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
    }
*/

}




