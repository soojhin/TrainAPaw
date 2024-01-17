package com.example.trainapaw;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ProfileFragment extends Fragment {

    private Button goToUploadActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewProfile = inflater.inflate(R.layout.fragment_profile, container, false);


        goToUploadActivity = viewProfile.findViewById(R.id.goToUploadActivity);

        // Set click listener for button
        goToUploadActivity.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), UploadVideo.class);
            startActivity(intent);
        });

        TextView tvName = viewProfile.findViewById(R.id.tvName);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("dog_info", getActivity().MODE_PRIVATE);
        boolean teachClickerTraining = sharedPreferences.getBoolean("TEACH_CLICKER_TRAINING", false);
        boolean teachSocializationTraining = sharedPreferences.getBoolean("TEACH_SOCIALIZATION_TRAINING", false);
        boolean teachBasicCommands = sharedPreferences.getBoolean("TEACH_BASIC_COMMANDS", false);
        String dogName = sharedPreferences.getString("NAME", "Default Name");
        String dogBreed = sharedPreferences.getString("BREED", "Default Breed");
        int dogAge = sharedPreferences.getInt("AGE", 0);

        tvName.setText(dogName);


        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(getContext()));
        }

        Python python = Python.getInstance();
        PyObject pyObject = python.getModule("script");
        PyObject result = pyObject.callAttr("recommend_training", dogBreed, dogAge);

        // Access the result as needed (update UI, display recommendations, etc.)
        Set<String> recommendedTraining = new HashSet<>();
        for (PyObject item : result.asList()) {
            recommendedTraining.add(item.toString());
        }

        if (teachClickerTraining) {
            recommendedTraining.add("Clicker Training");
        }
        if (teachSocializationTraining) {
            recommendedTraining.add("Socialization Training");
        }
        if (teachBasicCommands) {
            recommendedTraining.add("Basic Commands");
        }

        List<String> recommendedTrainingList = new ArrayList<>(recommendedTraining);

        // Display recommended training in the ListView
        ListView listView = viewProfile.findViewById(R.id.proflistView);

        // Create an ArrayAdapter to populate the ListView with the recommended training
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, recommendedTrainingList);
        listView.setAdapter(adapter);


        return viewProfile;
    }

    private void finish() {
    }
}