import pandas as pd
from sklearn.neighbors import NearestNeighbors
from sklearn.metrics.pairwise import cosine_similarity
from sklearn.metrics import precision_score, recall_score, f1_score, accuracy_score
from sklearn.naive_bayes import MultinomialNB
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import LabelEncoder

def recommend_training(user_dog_breed, user_dog_age):
    from os.path import dirname, join
    data_path = join(dirname(__file__), "TrainaPawDataSets.csv")
    # data_path = 'D:\\Downloads\\TrainaPawDatasets.csv'

    # Read the CSV file into a DataFrame
    df = pd.read_csv(data_path)

    # Convert categorical columns to numerical for k-NN
    df_encoded = pd.get_dummies(df[['Breed', 'Training', 'Difficulty Level', 'Dog Response', 'Duration Preferences']])

    # Combine numerical and non-numerical data
    df_combined = pd.concat([df[['Users', 'Age', 'Rating']], df_encoded], axis=1)

    # Choose features for k-NN
    features = ['Age', 'Rating'] + list(df_encoded.columns)

    # Create k-NN model with 'euclidean' distance
    knn_model = NearestNeighbors(n_neighbors=50, metric='euclidean')
    knn_model.fit(df_combined[features])

    # Calculate user similarities for collaborative filtering
    user_similarities = cosine_similarity(df_combined[features])

    # Filter DataFrame based on the input desired dog breed and age
    filtered_df = df[(df['Breed'] == user_dog_breed) & (df['Age'] == user_dog_age)]

    if filtered_df.empty:
        filtered_df = df[df['Breed'] == user_dog_breed]

    if filtered_df.empty:
        return set(), set()  # No matching records found

    user_index = filtered_df.iloc[0]['Users']
    user_features = df_combined.loc[user_index - 1, features].values.reshape(1, -1)

    # Train a Naive Bayes classifier to predict user preferences
    label_encoder = LabelEncoder()
    df['Training_Label'] = label_encoder.fit_transform(df['Training'])
    X_train, X_test, y_train, y_test = train_test_split(df_combined[features], df['Training_Label'], test_size=0.2, random_state=42)
    nb_classifier = MultinomialNB()
    nb_classifier.fit(X_train, y_train)


# Predict user preferences using Naive Bayes
    user_preferences = nb_classifier.predict(user_features)

    # Display unique recommendations
    unique_recommendations = set()

    # Collaborative Filtering: Find similar users
    similar_users = user_similarities[user_index - 1].argsort()[-10:-1][::-1]
    for user in similar_users:
        recommendations = df['Training'][df['Users'] == user + 1].values
        unique_recommendations.update(recommendations)

    # k-NN: Find k-NN for the user
    distances, indices = knn_model.kneighbors(user_features)
    for i in indices[0][1:]:
        recommended_training = df['Training'].iloc[i]
        unique_recommendations.add(recommended_training)

    # Content-Based Filtering: Recommend based on Naive Bayes prediction
    predicted_training = label_encoder.inverse_transform(user_preferences)[0]
    unique_recommendations.add(predicted_training)

    # Convert set to list before returning
    return list(unique_recommendations)

def calculate_metrics(ground_truth_ratings, predicted_ratings, threshold=3):
    # Set a threshold for positive recommendations (e.g., ratings above a certain value)
    predicted_labels = (predicted_ratings > threshold).astype(int)

    # Calculate metrics
    precision = precision_score(ground_truth_ratings > threshold, predicted_labels)
    recall = recall_score(ground_truth_ratings > threshold, predicted_labels)
    f1 = f1_score(ground_truth_ratings > threshold, predicted_labels)
    accuracy = accuracy_score(ground_truth_ratings > threshold, predicted_labels)

    # Return the results
    metrics = {"Precision": precision, "Recall": recall, "F1 Score": f1, "Accuracy": accuracy}
    return metrics
