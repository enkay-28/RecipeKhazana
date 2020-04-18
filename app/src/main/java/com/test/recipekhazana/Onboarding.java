package com.test.recipekhazana;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class Onboarding extends AppCompatActivity {
    private OnboardAdapter adapter;
    private LinearLayout layoutOnboardingInterface;
    private MaterialButton buttonOnboardingAction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);



        layoutOnboardingInterface =findViewById(R.id.layoutOnboardingIndicator);
        buttonOnboardingAction = findViewById(R.id.buttonOnboardAction);
        setUpOnboardItems();

        final ViewPager2 viewPager2 = findViewById(R.id.onboardingViewpager);
        viewPager2.setAdapter(adapter);
        setUpIndicators();
        setCurrentIndicator(0);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position);
            }
        });
        buttonOnboardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewPager2.getCurrentItem() + 1 < adapter.getItemCount()){
                    viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
                }else{
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                    finish();
                }
            }
        });
    }

    private void setUpOnboardItems(){
        List<OnboardItem> onboardItemList = new ArrayList<>();
        OnboardItem ingredients = new OnboardItem();
        ingredients.setTitle("Choose any ingredients ");
        ingredients.setDescription("Try its recipes");
        ingredients.setImage(R.drawable.ingredient);

        OnboardItem family = new OnboardItem();
        family.setTitle("Hassle free cooking ");
        family.setDescription("Get cuisine suggestions");
        family.setImage(R.drawable.family);


        OnboardItem tips = new OnboardItem();
        tips.setTitle("Cooking tips");
        tips.setDescription("Tips based on your previous dishes");
        tips.setImage(R.drawable.tips);


        onboardItemList.add(ingredients);
        onboardItemList.add(family);
        onboardItemList.add(tips);

        adapter = new OnboardAdapter(onboardItemList);


    }

    private void setUpIndicators(){
        ImageView[] indicators = new ImageView[adapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT

        );
        layoutParams.setMargins(0,0,5,0);


        for ( int i = 0; i < indicators.length; i++){

            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                    R.drawable.onboard_indicator_inactive));
            indicators[i].setLayoutParams(layoutParams);

            layoutOnboardingInterface.addView(indicators[i]);


        }
    }

    private void setCurrentIndicator(int index){
        int childCount = layoutOnboardingInterface.getChildCount();
        for (int i = 0; i < childCount; i++){
            ImageView imageView = (ImageView) layoutOnboardingInterface.getChildAt(i);
            if (i==index ){
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                        R.drawable.onboard_indicator_active));

            }else{

                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                        R.drawable.onboard_indicator_inactive));
            }


        }
        if ( index == adapter.getItemCount()-1)
            buttonOnboardingAction.setText("Start");
        else
            buttonOnboardingAction.setText("Next");
    }
}
