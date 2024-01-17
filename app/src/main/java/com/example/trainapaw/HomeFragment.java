package com.example.trainapaw;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    ImageSlider mainslider, officialSlider;
    Button btnStart;

    TextView seeTrainers, tvHomeDog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mainslider = view.findViewById(R.id.officialfragmentSlider);
        btnStart = view.findViewById(R.id.btnStart);
        seeTrainers = view.findViewById(R.id.tvSeeTrainers);
        //officialSlider = view.findViewById(R.id.officialSlider);
        tvHomeDog = view.findViewById(R.id.tvHomeDog);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("dog_info", getActivity().MODE_PRIVATE);
        String dogName = sharedPreferences.getString("NAME", "Default Name");


        tvHomeDog.setText(dogName);

        seeTrainers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog();
            }
        });

        view.findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressFragment progressFragment = new ProgressFragment();
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, progressFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });



        final List<SlideModel> remoteimages = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("OfficialSlider").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data:snapshot.getChildren())

                    remoteimages.add(new SlideModel(data.child("link").getValue().toString(), ScaleTypes.FIT));
                mainslider.setImageList(remoteimages,ScaleTypes.FIT);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return view;

    }
    private void showdialog() {
        final Dialog dialog = new Dialog(getContext());
        final List<SlideModel> remoteimages = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("OfficialSlider").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data:snapshot.getChildren())

                    remoteimages.add(new SlideModel(data.child("url").getValue().toString(), ScaleTypes.FIT));
                // Use findViewById() to get a reference to officialSlider
                officialSlider = dialog.findViewById(R.id.officialSlider);
                officialSlider.setImageList(remoteimages,ScaleTypes.FIT);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.officialsheetlayout);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();

    }
}