package com.example.learningmanagementapplication.Model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import androidx.databinding.library.baseAdapters.BR;

@Entity(tableName = "category_table")
public class Category extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "category_id")
    private int categoryId;
    @ColumnInfo(name = "category_name")
    private String categoryName;
    @ColumnInfo(name = "category_description")
    private String categoryDescription;

    @Ignore
    public Category() {}

    public Category(int categoryId, String categoryName, String categoryDescription) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    @Bindable
    public int getId() {
        return categoryId;
    }

    public void setId(int categoryId) {
        this.categoryId = categoryId;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        notifyPropertyChanged(BR.categoryName);
    }

    @Bindable
    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
        notifyPropertyChanged(BR.id);
        notifyPropertyChanged(BR.categoryDescription);
    }
}
