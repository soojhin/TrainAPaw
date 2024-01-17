package com.example.trainapaw;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProgressFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(getContext()));
        }

        View view = inflater.inflate(R.layout.fragment_progress, container, false);

        // Retrieve user inputs from SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("dog_info", getActivity().MODE_PRIVATE);
        boolean teachClickerTraining = sharedPreferences.getBoolean("TEACH_CLICKER_TRAINING", false);
        boolean teachSocializationTraining = sharedPreferences.getBoolean("TEACH_SOCIALIZATION_TRAINING", false);
        boolean teachBasicCommands = sharedPreferences.getBoolean("TEACH_BASIC_COMMANDS", false);
        String dogBreed = sharedPreferences.getString("BREED", "Default Breed");
        int dogAge = sharedPreferences.getInt("AGE", 0);

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
        ListView listView = view.findViewById(R.id.listView);

        // Create an ArrayAdapter to populate the ListView with the recommended training
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, recommendedTrainingList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, itemview, position, id) -> {
            String selectedTrainingMethod = recommendedTrainingList.get(position);

            if (selectedTrainingMethod.equals("Clicker Training")) {
                Intent intent = new Intent(getActivity(), TrainingDetail.class);
                intent.putExtra("training_method", selectedTrainingMethod);
                startActivity(intent);
            }

            if (selectedTrainingMethod.equals("Socialization Training")) {
                Intent intent = new Intent(getActivity(), Socialization.class);
                intent.putExtra("training_method", selectedTrainingMethod);
                startActivity(intent);
            }

            if (selectedTrainingMethod.equals("Basic Commands")) {
                Intent intent = new Intent(getActivity(), BasicCommands.class);
                intent.putExtra("training_method", selectedTrainingMethod);
                startActivity(intent);
            }

            if (selectedTrainingMethod.equals("Off Leash Down Stay")) {
                Intent intent = new Intent(getActivity(), offleash.class);
                intent.putExtra("training_method", selectedTrainingMethod);
                startActivity(intent);
            }

            if (selectedTrainingMethod.equals("Down Stay")) {
                Intent intent = new Intent(getActivity(), downstay.class);
                intent.putExtra("training_method", selectedTrainingMethod);
                startActivity(intent);
            }

            if (selectedTrainingMethod.equals("Off Leash Sit")) {
                Intent intent = new Intent(getActivity(), offleashsit.class);
                intent.putExtra("training_method", selectedTrainingMethod);
                startActivity(intent);
            }
        });

        return view;
    }
}
