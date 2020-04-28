package com.test.recipekhazana;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;


public class SpinFragment extends Fragment {

    Random random;
    int lastDir = 0;
    boolean spinning;
    ImageView bottleImage;
    Dialog dialog;
    ArrayList<Recipe> dataList;
    Recipe r;
    public SpinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_spin, container, false);
        bottleImage = view.findViewById(R.id.spinBottle);
        dataList = new ArrayList<>();
        dialog = new Dialog(getContext());


        random = new Random();
        spinning = false;
        bottleImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(!spinning) {
                    final MediaPlayer mp = MediaPlayer.create(getContext(),R.raw.fizz);
                    mp.start();
                    int newDir = lastDir + random.nextInt(3000) +1000;
                    float pivotX = (float) bottleImage.getWidth() / 2;
                    float pivotY = (float) bottleImage.getHeight() / 2;
                    Animation rotate = new RotateAnimation(lastDir, newDir, pivotX, pivotY);
                    rotate.setDuration(2500);
                    rotate.setFillAfter(true);
                    dialog.setContentView(R.layout.custom_dialog);
                    ImageView closeImg = dialog.findViewById(R.id.cross);
                    CircleImageView recImg = dialog.findViewById(R.id.dialog_image);
                    TextView dialogText = dialog.findViewById(R.id.dialog_text);
                    Button dialogbtn = dialog.findViewById(R.id.dialogbtn);
                    int rand = random.nextInt(36);

                    dataList = getActivity().getIntent().getParcelableArrayListExtra("data");
                    r = dataList.get(rand);
                    final String dishName = r.getName();
                    final String img =r.getImgURL();
                    Picasso.get().load(img).into(recImg);
                    dialogText.setText(dishName);
                    dialogbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getContext(),RecipeDetail.class);
                            intent.putExtra("name",dishName);
                            intent.putExtra("image",img);
                            intent.putExtra("calorie",r.getCalories());
                            intent.putExtra("serving",r.getServings());

                            StringBuilder ingredient = new StringBuilder();
                            for(String l : r.getIngredients()){
                                ingredient.append("• ").append(l).append("\n");
                            }
                            intent.putExtra("ingredient", ingredient.toString());
                            intent.putExtra("directions",r.getInstructions());
                            startActivity(intent);
                            dialog.dismiss();
                        }
                    });

                    closeImg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    rotate.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            spinning = true;

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            spinning = false;
                            mp.stop();
                            dialog.show();

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    lastDir = newDir;
                    bottleImage.startAnimation(rotate);




                }
            }
        });




        return view;

    }
}
