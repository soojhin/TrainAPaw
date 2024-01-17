/*package com.example.trainapaw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TrainingAdapter extends ArrayAdapter<TrainingStep> {

    private Context context;
    private List<TrainingStep> trainingStepsList;

    public TrainingAdapter(Context context, int resource, List<TrainingStep> trainingStepsList) {
        super(context, resource, trainingStepsList);
        this.context = context;
        this.trainingStepsList = trainingStepsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.training_step_item, parent, false);
        }

        TrainingStep currentStep = trainingStepsList.get(position);

        TextView nameTextView = listItemView.findViewById(R.id.stepNameTextView);
        TextView descriptionTextView = listItemView.findViewById(R.id.stepDescriptionTextView);
        TextView statusTextView = listItemView.findViewById(R.id.statusTextView);

        nameTextView.setText(currentStep.getName());
        descriptionTextView.setText(currentStep.getDescription());
        statusTextView.setText(getStatusLabel(currentStep.getStatus()));

        return listItemView;
    }

    private String getStatusLabel(Status status) {
        switch (status) {
            case NOT_STARTED:
                return "Not Yet Started";
            case IN_PROGRESS:
                return "In Progress";
            case COMPLETED:
                return "Completed";
            default:
                return "Unknown";
        }
    }
}
*/