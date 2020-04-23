package com.test.recipekhazana;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private List<String> titleRecipe;
    private List<String> images;
    private Context context;
    OnRecyclerViewClickListener onRecyclerViewClickListener;
    public RecyclerViewAdapter(Context context, List<String> title, List<String> images, OnRecyclerViewClickListener onRecyclerViewClickListener){
        this.titleRecipe = title;
        this.images = images;
        this.context = context;
        this.onRecyclerViewClickListener = onRecyclerViewClickListener;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate( R.layout.item_recycle, parent,false);
        return new MyViewHolder(v,onRecyclerViewClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titleCard.setText(titleRecipe.get(position));
        Picasso.get().load(images.get(position)).into(holder.foodImage);
    }

    @Override
    public int getItemCount() {
        return titleRecipe.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        ImageView foodImage;
        TextView titleCard;
        OnRecyclerViewClickListener onRecyclerViewClickListener;
        public MyViewHolder(@NonNull View itemView ,OnRecyclerViewClickListener onRecyclerViewClickListener) {
            super(itemView);
            titleCard = itemView.findViewById(R.id.recipe_card_title);
            foodImage = itemView.findViewById(R.id.food_image);
            this.onRecyclerViewClickListener = onRecyclerViewClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onRecyclerViewClickListener.onItemClick(getAdapterPosition());
        }
    }


    public interface OnRecyclerViewClickListener{
        void onItemClick(int position);
    }
}
