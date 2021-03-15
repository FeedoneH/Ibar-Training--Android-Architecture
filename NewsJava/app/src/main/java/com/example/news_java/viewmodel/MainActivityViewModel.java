package com.example.news_java.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.news_java.model.Book;
import com.example.news_java.model.BookDao;
import com.example.news_java.model.Category;
import com.example.news_java.model.CategoryDAO;
import com.example.news_java.model.EBookShopRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    EBookShopRepository eBookShopRepository;
    private LiveData<List<Category>> allcategories;
    private LiveData<List<Book>> books;

    public LiveData<List<Category>> getAllcategories() {
        return eBookShopRepository.getCategories();
    }

    public LiveData<List<Book>> getBooks(int categoryID) {
        return eBookShopRepository.getBook(categoryID);
    }


    public void insertBook(Book book){
        eBookShopRepository.insertBook(book);
    }
    public void deleteBook(Book book){
        eBookShopRepository.deleteBook(book);
    }
    public void updateBook(Book book){
         eBookShopRepository.updateBook(book);
    }
    public void insertCategory(Category category){
        eBookShopRepository.insertCategory(category);
    }
    public void deleteCategory(Category category){
        eBookShopRepository.deleteCategory(category);
    }
    public void updateCategory(Category category){
        eBookShopRepository.updateCategory(category);
    }
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        eBookShopRepository = new EBookShopRepository(application);
    }
}
