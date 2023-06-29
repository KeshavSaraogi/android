package com.example.learningmanagementapplication.Model;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CourseRepository {

    private CategoryDAO categoryDAO;
    private CourseDAO courseDAO;

    private LiveData<List<Category>> category;
    private LiveData<List<Course>> course;

    public CourseRepository(Application application) {
        CourseDB courseDB = CourseDB.getInstance(application);
        categoryDAO = courseDB.categoryDAO();
        courseDAO = courseDB.courseDAO();
    }

    public LiveData<List<Category>> getCategory() {
        return categoryDAO.getAllCategory();
    }

    public LiveData<List<Course>> getCourse(int categoryId) {
        return courseDAO.getCourseFromCategory(categoryId);
    }

    private void insertCategory(Category category) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //insert new category
                categoryDAO.insert(category);
            }
        });
    }

    private void insertCourse(Course course) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //insert new course
                courseDAO.insert(course);
            }
        });
    }

    private void deleteCategory(Category category) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //delete category
                categoryDAO.delete(category);
            }
        });
    }

    private void deleteCourse(Course course) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //delete course
                courseDAO.delete(course);
            }
        });
    }

    private void updateCategory(Category category) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //update category
                categoryDAO.update(category);
            }
        });
    }

    private void updateCourse(Course course) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //update course
                courseDAO.update(course);
            }
        });
    }
}
