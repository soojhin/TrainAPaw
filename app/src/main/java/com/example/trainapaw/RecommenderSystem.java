package com.example.trainapaw;


import java.util.ArrayList;
import java.util.List;

public class RecommenderSystem {

    // Sample data representing dog breeds, ages, and training methods
    private List<DogData> dogDataList;
    public static final String NOT_STARTED = "Not yet started";
    public static final String IN_PROGRESS = "In progress";
    public static final String COMPLETED = "Completed";




    public String getTrainingStatus(String trainingMethod) {
        // You can implement your logic to determine the status based on some criteria
        // For now, let's assume all training methods start as "Not yet started"
        return NOT_STARTED;
    }
    public RecommenderSystem() {
        // Initialize your dataset (you can replace this with your data retrieval logic)
        dogDataList = new ArrayList<>();
        dogDataList.add(new DogData("Labrador Retriever", 3, "Lesson: Clicker Training"));
        dogDataList.add(new DogData("Aspin", 3, "Lesson: Clicker Training"));
        dogDataList.add(new DogData("Golden Retriever", 3, "Lesson: Clicker Training"));
        dogDataList.add(new DogData("Shih Tzu", 3, "Lesson: Clicker Training"));
        dogDataList.add(new DogData("Chihuahua", 3, "Lesson: Clicker Training"));
        dogDataList.add(new DogData("Dalmatian", 3, "Lesson: Clicker Training"));
        dogDataList.add(new DogData("Doberman Pinscher", 3, "Lesson: Clicker Training"));
        dogDataList.add(new DogData("Labrador Retriever", 3, "Lesson: Clicker Training"));
        dogDataList.add(new DogData("Pomeranian", 3, "Lesson: Clicker Training"));
        dogDataList.add(new DogData("Poodle", 3, "Lesson: Clicker Training"));
        dogDataList.add(new DogData("Pug", 3, "Lesson: Clicker Training"));
        dogDataList.add(new DogData("Rottweiler", 3, "Lesson: Clicker Training"));
        dogDataList.add(new DogData("Siberian Husky", 3, "Lesson: Clicker Training"));
        dogDataList.add(new DogData("Labrador Retriever", 3, "Lesson: Basic Obedience"));
        dogDataList.add(new DogData("German Shepherd", 5, "Lesson: Clicker Training"));
        dogDataList.add(new DogData("Beagle", 2, "Lesson: Model-Rival Training"));
        dogDataList.add(new DogData("Bulldog", 4, "Lesson: Clicker Training"));
    }

    // Define a class to represent dog data
    private class DogData {
        String breed;
        int age;
        String trainingMethod;

        DogData(String breed, int age, String trainingMethod) {
            this.breed = breed;
            this.age = age;
            this.trainingMethod = trainingMethod;
        }
    }

    // Recommendation function that combines collaborative and content-based filtering
    public List<String> recommendTrainingMethod(String dogBreed, int dogAge) {
        List<String> recommendations = new ArrayList<>();

        // Collaborative Filtering based on breed and age
        for (DogData dog : dogDataList) {
            if (dog.breed.equals(dogBreed) && Math.abs(dog.age - dogAge) <= 1) {
                // Check if the training method is not already in recommendations
                if (!recommendations.contains(dog.trainingMethod)) {
                    recommendations.add(dog.trainingMethod);
                }
            }
        }

        // Content-Based Filtering (basic example)
        if (dogAge <= 6) {
            addIfNotPresent(recommendations, "Lesson: Clicker Training");
            addIfNotPresent(recommendations, "Lesson: on leash training");
            addIfNotPresent(recommendations, "Lesson: Sit");
            addIfNotPresent(recommendations, "Lesson: Down Training");
            addIfNotPresent(recommendations, "Lesson: Wait Training");
            addIfNotPresent(recommendations, "Lesson: Heel Training");
            addIfNotPresent(recommendations, "Lesson: Watch Me Training");
            addIfNotPresent(recommendations, "Lesson: Socialization Training");
        } else if (dogAge <= 12) {
            addIfNotPresent(recommendations, "Lesson: loose leash commands Training");
            addIfNotPresent(recommendations, "Lesson: Play Dead Training");
            addIfNotPresent(recommendations, "Lesson: Shake Hands Training");
            addIfNotPresent(recommendations, "Lesson: Fetch Training");
            addIfNotPresent(recommendations, "Lesson: Roll Over Training");
            addIfNotPresent(recommendations, "Lesson: Loose Leash Walking Training");
            addIfNotPresent(recommendations, "Lesson: Loose leash heeling Training");
        } else {
            addIfNotPresent(recommendations, "Lesson: Off Leash Sit Training");
            addIfNotPresent(recommendations, "Lesson: Off Leash Sit Stay Training");
            addIfNotPresent(recommendations, "Lesson: Off Leash Down Training");
            addIfNotPresent(recommendations, "Lesson: Off Leash Down Stay Training");
            addIfNotPresent(recommendations, "Lesson: Long Stay off Leash Training");
            addIfNotPresent(recommendations, "Lesson: On and Off Sight stay Training");

        }
        return recommendations;
    }

    // Helper method to add an item to the list if it's not already present
    private void addIfNotPresent(List<String> list, String item) {
        if (!list.contains(item)) {
            list.add(item);
        }
    }

    // Update the status of a training method
    public void updateTrainingStatus(String trainingMethod, String newStatus) {
        // Implement your logic to update the status (e.g., store it in SharedPreferences)
        // This method should be called when the training is marked as completed
    }
}



