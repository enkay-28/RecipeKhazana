package com.test.recipekhazana;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OnboardAdapter extends RecyclerView.Adapter<OnboardAdapter.OnboardViewHolder> {
    private List<OnboardItem> onboardItemList;

    public OnboardAdapter(List<OnboardItem> items){
        this.onboardItemList = items;
    }

    @NonNull
    @Override
    public OnboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboardViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_onboarding
                ,parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardViewHolder holder, int position) {
        holder.setOnBoardingData(onboardItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return onboardItemList.size();
    }

    class OnboardViewHolder extends RecyclerView.ViewHolder{
        private TextView textTitle;
        private TextView textDesc;
        private ImageView imageOnboard;

        OnboardViewHolder(@NonNull View itemView){

            super(itemView);
            textTitle  = itemView.findViewById(R.id.textTitle);
            textDesc  = itemView.findViewById(R.id.textDesc);
            imageOnboard  = itemView.findViewById(R.id.imageOnboarding);
        }

        void setOnBoardingData(OnboardItem ob){

            textTitle.setText(ob.getTitle());
            textDesc.setText(ob.getDescription());
            imageOnboard.setImageResource(ob.getImage());
        }

    }
}
