package com.test.recipekhazana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

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
    ArrayList<Recipe> allData;
    FrameLayout frameLayout;
    Fragment fragment;
    //private final String url = "https://raw.githubusercontent.com/coolboyhs10/test/master/db-recipes.json";
    //JsonArrayRequest request;
    //private FirebaseAuth firebaseAuth;
    Fragment fragment1 ;
    Fragment fragment2 ;
    Fragment fragment3;
    Fragment fragment4;

    FragmentManager fragmentManager;
    Fragment activeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        allData = getIntent().getParcelableArrayListExtra("data");
        //firestoreDatabase = FirebaseFirestore.getInstance();
       // requestQueue = Volley.newRequestQueue(this);
        //jsonParse();
        fragmentManager = getSupportFragmentManager();
        frameLayout = findViewById(R.id.frame_layout);
        fragment1 = new RecipeFragment(allData);
        fragment2 = new SearchFragment();
        fragment3 = new SpinFragment();
        fragment4 = new ProfileFragment();
        activeFragment = fragment1;
        fragmentManager.beginTransaction().add(R.id.frame_layout, fragment4, "4").hide(fragment4).commit();
        fragmentManager.beginTransaction().add(R.id.frame_layout, fragment3, "3").hide(fragment3).commit();
        fragmentManager.beginTransaction().add(R.id.frame_layout, fragment2, "2").hide(fragment2).commit();
        fragmentManager.beginTransaction().add(R.id.frame_layout, fragment1, "1").commit();

        bottomNavigationView = findViewById(R.id.nav_bar);




        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {

                    case R.id.all_recipe_item:
                        fragmentManager.beginTransaction().hide(activeFragment).show(fragment1).commit();
                        activeFragment = fragment1;
                        break;

                    case R.id.search_recipe_item:
                        fragmentManager.beginTransaction().hide(activeFragment).show(fragment2).commit();
                        activeFragment = fragment2;
                        break;




                    case R.id.spin_item:
                        fragmentManager.beginTransaction().hide(activeFragment).show(fragment3).commit();
                        activeFragment = fragment3;
                        break;


                    case R.id.profile_item:
                        fragmentManager.beginTransaction().hide(activeFragment).show(fragment4).commit();
                        activeFragment = fragment4;
                        break;


                }

                return true;

            }
        });

        bottomNavigationView.setSelectedItemId(R.id.all_recipe_item);
    }





}
