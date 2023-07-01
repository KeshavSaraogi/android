package com.example.learningmanagementapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

    private static final int ADD_COURSE_REQUESTED_CODE    = 1;
    private static final int EDIT_COURSE_REQUESTED_CODE   = 2;

    private MainActivityViewModel mainActivityViewModel;
    private ArrayList<Category> categoriesList;
    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandler clickHandler;
    private Category selectedCategory;
    private RecyclerView courseRecyclerView;
    private CourseAdapter courseAdapter;
    private ArrayList<Course> courseList;

    public int selectedCourseId;

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

        courseAdapter.setListener(new CourseAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Course course) {
                selectedCourseId = course.getCourseId();

                Intent intent = new Intent(MainActivity.this, AddEditActivity.class);
                intent.putExtra(AddEditActivity.COURSE_ID, selectedCourseId);
                intent.putExtra(AddEditActivity.COURSE_NAME, course.getCourseName());
                intent.putExtra(AddEditActivity.COURSE_DESCRIPTION, course.getCourseDescription());
                startActivityForResult(intent, EDIT_COURSE_REQUESTED_CODE);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Course courseToDelete = courseList.get(viewHolder.getAdapterPosition());
                mainActivityViewModel.deleteCourse(courseToDelete);
            }
        }).attachToRecyclerView(courseRecyclerView);

        class MainActivityClickHandler {

            public void onFabClicked(View view) {
                Intent intent = new Intent(MainActivity.this, AddEditActivity.class);
                startActivityForResult(intent, ADD_COURSE_REQUESTED_CODE);
            }

            public void onSelectedItem(AdapterView<?> parent, View view, int position, long id) {
                selectedCategory = (Category) parent.getItemAtPosition(position);
                String message = "ID is: " + selectedCategory.getId() + "\n Name Is: " + selectedCategory.getCategoryName();
                Toast.makeText(parent.getContext(), "" + message, Toast.LENGTH_SHORT).show();
                loadCoursesList(selectedCategory.getId());
            }
        }

        @Override
        protected void onActivityResult (int requestCode, int resultCode, @Nullable Intent data){
            super.onActivityResult(requestCode, resultCode, data);

            int selectedCategoryId = selectedCategory.getId();

            if (requestCode == ADD_COURSE_REQUESTED_CODE && resultCode == RESULT_OK){

                Course course = new Course();
                course.setCategoryId(selectedCategoryId);
                course.setCourseName(data.getStringExtra(AddEditActivity.COURSE_NAME));
                course.setCourseDescription(data.getStringExtra(AddEditActivity.COURSE_DESCRIPTION));
                mainActivityViewModel.addNewCourse(course);
            }
            else if (requestCode == EDIT_COURSE_REQUESTED_CODE && resultCode == RESULT_OK) {

                Course course = new Course();
                course.setCategoryId(selectedCategoryId);
                course.setCourseName(data.getStringExtra(AddEditActivity.COURSE_NAME));
                course.setCourseDescription(data.getStringExtra(AddEditActivity.COURSE_DESCRIPTION));
                course.setCourseId(selectedCourseId);
                mainActivityViewModel.updateCourse(course);
            }
        }
    }
}
