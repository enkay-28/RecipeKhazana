package com.test.recipekhazana;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterSearch extends RecyclerView.Adapter<RecyclerViewAdapterSearch.MyViewHolder> implements Filterable {
    public ArrayList<String> titleRecipe;
    private ArrayList<String> images;
    private Context context;
    private OnRecyclerViewClickListener onRecyclerViewClickListener;
    private ArrayList<String> titleRecipeFull;
    private ArrayList<String> imagesFull;
    ArrayList<String> filteredImages2;
    public RecyclerViewAdapterSearch(Context context, ArrayList<String> title, ArrayList<String> images, OnRecyclerViewClickListener onRecyclerViewClickListener){
        this.titleRecipe = title;
        this.images = images;
        this.context = context;
        this.onRecyclerViewClickListener = onRecyclerViewClickListener;
        titleRecipeFull = new ArrayList<>(titleRecipe);
        imagesFull = new ArrayList<>(images);
    }

    public ArrayList<String> getTitleRecipe() {
        return titleRecipe;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate( R.layout.item_recycle_search, parent,false);
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
            titleCard = itemView.findViewById(R.id.ing_title);
            foodImage = itemView.findViewById(R.id.ing_image);
            this.onRecyclerViewClickListener = onRecyclerViewClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onRecyclerViewClickListener.onItemClick(getAdapterPosition());
        }
    }

    private Filter searchFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<String> filteredTitles = new ArrayList<String>();
            ArrayList<String> filteredImages = new ArrayList<String>();

            if(charSequence == null || charSequence.length()==0){
                filteredImages.addAll(imagesFull);
                filteredTitles.addAll(titleRecipeFull);
            }else{
                String fillPattern = charSequence.toString().toLowerCase().trim();
                for(int i = 0;i < titleRecipeFull.size();i++){
                    if(titleRecipeFull.get(i).toLowerCase().contains(fillPattern)){
                        filteredTitles.add(titleRecipeFull.get(i));
                        filteredImages.add(imagesFull.get(i));
                    }
                }
            }
            filteredImages2 = filteredImages;
            FilterResults fr = new FilterResults();
            fr.values = filteredTitles;
            return fr;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            titleRecipe.clear();
            images.clear();
            titleRecipe.addAll((List)filterResults.values);
            images.addAll(filteredImages2);
            notifyDataSetChanged();
        }
    };
    @Override
    public Filter getFilter() {
        return searchFilter;
    }


    public interface OnRecyclerViewClickListener{
        void onItemClick(int position);
    }
}
