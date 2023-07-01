package com.example.learningmanagementapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.learningmanagementapplication.Model.Category;
import com.example.learningmanagementapplication.Model.Course;
import com.example.learningmanagementapplication.ViewModel.MainActivityViewModel;
import com.example.learningmanagementapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private ArrayList<Category> categoriesList;
    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandler clickHandler;
    private Category selectedCategory;
    private RecyclerView courseRecyclerView;
    private CourseAdapter courseAdapter;
    private ArrayList<Course> courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        clickHandler = new MainActivityClickHandler();
        activityMainBinding.setClickHandlers(clickHandler);

        mainActivityViewModel.getAllCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                categoriesList = (ArrayList<Category>) categories;
                for (Category c: categories) {
                    Log.i("TAG", c.getCategoryName());
                }
                showOnSpinner();
            }
        });

        mainActivityViewModel.getSelectedCourses(1).observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                for (Course c: courses) {
                    Log.v("TAG", c.getCourseName());
                }
            }
        });
    }

    public void showOnSpinner() {
        ArrayAdapter<Category> categoryArrayAdapter = new ArrayAdapter<>(this,R.layout.spinner_item,categoriesList);
        categoryArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        activityMainBinding.setSpinnerAdapter(categoryArrayAdapter);
    }

    public void loadCoursesList(int categoryId) {
        mainActivityViewModel.getSelectedCourses(categoryId).observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                courseList = (ArrayList<Course>) courses;
                loadRecyclerView();
            }
        });
    }

    public void loadRecyclerView() {
        courseRecyclerView = activityMainBinding.secondaryLayout.recyclerView;
        courseRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseRecyclerView.setHasFixedSize(true);

        courseAdapter = new CourseAdapter();
        courseRecyclerView.setAdapter(courseAdapter);
        courseAdapter.setCourses(courseList);
    }

    public class MainActivityClickHandler {

        public void onFabClicked(View view) {
            Toast.makeText(getApplicationContext(), "FAB CLICKED", Toast.LENGTH_SHORT).show();
        }

        public void onSelectedItem(AdapterView<?> parent, View view, int position, long id) {
            selectedCategory = (Category) parent.getItemAtPosition(position);
            String message = "ID is: " + selectedCategory.getId() + "\n Name Is: " + selectedCategory.getCategoryName();
            Toast.makeText(parent.getContext(), "" + message, Toast.LENGTH_SHORT).show();
            loadCoursesList(selectedCategory.getId());
        }
    }
}
