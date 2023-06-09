package com.example.learningmanagementapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learningmanagementapplication.Model.Course;
import com.example.learningmanagementapplication.databinding.CourseListItemBinding;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder>{

    private onItemClickListener listener;
    private ArrayList<Course> courses = new ArrayList<>();

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CourseListItemBinding courseListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.course_list_item,
                parent,
                false);
        return new CourseViewHolder(courseListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courses.get(position);
        holder.courseListItemBinding.setCourse(course);
    }

    @Override
    public int getItemCount() {
        return null!=courses?courses.size():0;
    }

    class CourseViewHolder extends RecyclerView.ViewHolder{
        private CourseListItemBinding courseListItemBinding;


        public CourseViewHolder(CourseListItemBinding courseListItemBinding) {
            super(courseListItemBinding.getRoot());
            this.courseListItemBinding = courseListItemBinding;

            courseListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickedPosition = getAdapterPosition();
                    if (listener != null && clickedPosition != RecyclerView.NO_POSITION) {
                        listener.onItemClick(courses.get(clickedPosition));
                    }
                }
            });
        }
    }

    public interface onItemClickListener {
        void onItemClick(Course course);
    }

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public void setCourses(ArrayList<Course> newCourses) {
        // this.courses = courses;
        // notifyDataSetChanged();

        final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new CourseDiffCallBack(courses, newCourses), false);

        courses = newCourses;
        result.dispatchUpdatesTo(CourseAdapter.this);
    }
}
