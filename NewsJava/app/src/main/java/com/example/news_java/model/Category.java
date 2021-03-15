package com.example.news_java.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "category_of_books_table")
public class Category extends BaseObservable {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "category_name")
    private String categoryName;
    @ColumnInfo(name = "category_description")
    private String categoryDescription;

    public Category() {
    }

    public Category(String categoryName, String categoryDescription) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        notifyPropertyChanged(BR.categoryDescription);
    }

    @Override
    public String toString() {
        return categoryName;
    }
}
