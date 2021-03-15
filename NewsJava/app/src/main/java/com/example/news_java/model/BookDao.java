package com.example.news_java.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookDao {
    @Insert
    void insert(Book book);

    @Update
    void update(Book book);

    @Delete
    void delete(Book book);


    @Query("Select*from books_table")
    LiveData<List<Book>> SelectAllBooks();
    @Query("Select * from books_table WHERE category_id=:categoryID")
    LiveData<List<Book>> getBooks(int categoryID);
}
