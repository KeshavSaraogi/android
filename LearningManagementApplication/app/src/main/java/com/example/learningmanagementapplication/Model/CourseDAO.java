package com.example.learningmanagementapplication.Model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CourseDAO {

    @Insert
    void insert(Course course);

    @Update
    void update(Course course);

    @Delete
    void delete(Course course);

    @Query("SELECT * FROM course_table")
    LiveData<List<Course>> getAllCourse();

    @Query("SELECT * FROM course_table WHERE categoryId==:categoryId")
    LiveData<List<Course>> getCourseFromCategory(int categoryId);

}
