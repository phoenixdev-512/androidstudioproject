package com.example.qrattendance;

import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;

public class FakeDatabase {
    // This static LiveData will be shared across the entire application.
    public static MutableLiveData<ArrayList<String>> studentNames = new MutableLiveData<>(new ArrayList<>());

    /**
     * Adds a student to the list.
     * Ensures no duplicates are added.
     * @param name The name of the student to add.
     */
    public static void addStudent(String name) {
        ArrayList<String> currentList = studentNames.getValue();
        if (currentList != null && !currentList.contains(name)) {
            currentList.add(name);
            // Post the updated list to observers
            studentNames.postValue(currentList);
        }
    }
}
