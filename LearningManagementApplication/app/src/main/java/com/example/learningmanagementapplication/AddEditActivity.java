package com.example.learningmanagementapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.learningmanagementapplication.Model.Course;
import com.example.learningmanagementapplication.databinding.ActivityAddEditBinding;

public class AddEditActivity extends AppCompatActivity {

    public static final String COURSE_ID              = "courseId";
    public static final String COURSE_NAME            = "courseName";
    public static final String COURSE_DESCRIPTION     = "courseDescription";

    private Course course;
    private ActivityAddEditBinding activityAddEditBinding;
    private AddAndEditActivityClickHandler clickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        course = new Course();
        activityAddEditBinding = DataBindingUtil.setContentView(this,R.layout.activity_add_edit);
        activityAddEditBinding.setCourse(course);

        clickHandler = new AddAndEditActivityClickHandler(this);
        activityAddEditBinding.setClickHandler(clickHandler);

        Intent intent = getIntent();
        if (intent.hasExtra(COURSE_ID)) {
            setTitle("EDIT COURSE");
            course.setCourseName(intent.getStringExtra(COURSE_NAME));
            course.setCourseDescription(intent.getStringExtra(COURSE_DESCRIPTION));
        } else {
            setTitle("CREATE NEW COURSE");
        }
    }

    public class AddAndEditActivityClickHandler {
        Context context;

        public AddAndEditActivityClickHandler(Context context) {
            this.context = context;
        }

        public void onSubmitButtonClicked(View view) {
            if (course.getCourseName() == null) {
                Toast.makeText(context, "Course Name Is Required", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent();
                intent.putExtra(COURSE_NAME, course.getCourseName());
                intent.putExtra(COURSE_DESCRIPTION, course.getCourseDescription());
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }
}
