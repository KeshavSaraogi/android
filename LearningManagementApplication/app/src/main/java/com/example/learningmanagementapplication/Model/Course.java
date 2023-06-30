package com.example.learningmanagementapplication.Model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import static androidx.room.ForeignKey.CASCADE;

import androidx.databinding.library.baseAdapters.BR;

@Entity(tableName = "course_table",
        foreignKeys = @ForeignKey(entity = Category.class,
                parentColumns = "id",
                childColumns = "category_id",
                onDelete = CASCADE))
public class Course extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "course_id")
    private int courseId;
    @ColumnInfo(name = "category_id")
    private int categoryId;
    @ColumnInfo(name = "course_name")
    private String courseName;
    @ColumnInfo(name = "course_description")
    private String courseDescription;

    @Ignore
    public Course() {}

    public Course(int courseId, int categoryId, String courseName, String courseDescription) {
        this.courseId = courseId;
        this.categoryId = categoryId;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
    }

    @Bindable
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
        notifyPropertyChanged(BR.categoryId);
    }

    @Bindable
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
        notifyPropertyChanged(BR.courseName);
    }

    @Bindable
    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
        notifyPropertyChanged(BR.courseDescription);
    }
}
