package com.test.recipekhazana;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;


public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_profile, container, false);
        MaterialButton btn = v.findViewById(R.id.logoutBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getContext(),LoginActivity.class);
                intent.putParcelableArrayListExtra("data",getActivity().getIntent().getParcelableArrayListExtra("data"));

                startActivity(intent);
                getActivity().finishAffinity();
            }
        });

        return v;
    }
}
