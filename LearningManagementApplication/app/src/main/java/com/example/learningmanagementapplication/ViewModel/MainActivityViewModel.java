package com.example.learningmanagementapplication.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.learningmanagementapplication.Model.Category;
import com.example.learningmanagementapplication.Model.Course;
import com.example.learningmanagementapplication.Model.CourseRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private CourseRepository courseRepository;
    private LiveData<List<Category>> allCategories;
    private LiveData<List<Course>> selectedCourses;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        courseRepository = new CourseRepository(application);
    }

    public LiveData<List<Category>> getAllCategories() {
        allCategories = courseRepository.getCategory();
        return allCategories;
    }

    public LiveData<List<Course>> getSelectedCourses(int categoryId) {
        selectedCourses = courseRepository.getCourse(categoryId);
        return selectedCourses;
    }

    public void addNewCourse(Course course) {
        courseRepository.insertCourse(course);
    }

    public void updateCourse(Course course) {
        courseRepository.updateCourse(course);
    }

    public void deleteCourse(Course course) {
        courseRepository.deleteCourse(course);
    }
}
