package com.example.news_java.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class EBookShopRepository {
    private BookDao bookDao;
    private CategoryDAO categoryDAO;
    private LiveData<List<Category>> categories;
    private LiveData<List<Book>> book;

    public EBookShopRepository(Application application) {
        BookDatabase bookDB = BookDatabase.getInstance(application);
        bookDao = bookDB.bookDao();
        categoryDAO = bookDB.categoryDAO();
    }

    public LiveData<List<Category>> getCategories() {
        return categoryDAO.SelectAllCategories();
    }

    public LiveData<List<Book>> getBook(int categoryID) {
        return bookDao.getBooks(categoryID);
    }

    public void insertCategory(Category category) {
        new InsertCategoryAsyncTask(categoryDAO).execute(category);
    }

    private static class InsertCategoryAsyncTask extends AsyncTask<Category, Void, Void> {
        private CategoryDAO categoryDAO;

        public InsertCategoryAsyncTask(CategoryDAO categoryDAO) {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDAO.insert(categories[0]);
            return null;
        }
    }

    public void insertBook(Book book){
        new InsertBookAsyncTask(bookDao).execute(book);
    }
    private static class InsertBookAsyncTask extends AsyncTask<Book, Void, Void> {
        private BookDao bookDao;

        public InsertBookAsyncTask(BookDao bookDao) {
            this.bookDao = bookDao;
        }

        @Override
        protected Void doInBackground(Book... books) {
            bookDao.insert(books[0]);
            return null;
        }
    }

    public void deleteCategory(Category category) {
        new InsertCategoryAsyncTask(categoryDAO).execute(category);
    }

    private static class DeleteCategoryAsyncTask extends AsyncTask<Category, Void, Void> {
        private CategoryDAO categoryDAO;

        public DeleteCategoryAsyncTask(CategoryDAO categoryDAO) {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDAO.insert(categories[0]);
            return null;
        }
    }

    public void deleteBook(Book book){
        new InsertBookAsyncTask(bookDao).execute(book);
    }
    private static class DeleteBookAsyncTask extends AsyncTask<Book, Void, Void> {
        private BookDao bookDao;

        public DeleteBookAsyncTask(BookDao bookDao) {
            this.bookDao = bookDao;
        }

        @Override
        protected Void doInBackground(Book... books) {
            bookDao.delete(books[0]);
            return null;
        }
    }
    public void updateCategory(Category category) {
        new InsertCategoryAsyncTask(categoryDAO).execute(category);
    }

    private static class UpdateCategoryAsyncTask extends AsyncTask<Category, Void, Void> {
        private CategoryDAO categoryDAO;

        public UpdateCategoryAsyncTask(CategoryDAO categoryDAO) {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDAO.update(categories[0]);
            return null;
        }
    }

    public void updateBook(Book book){
        new InsertBookAsyncTask(bookDao).execute(book);
    }
    private static class UpdateBookAsyncTask extends AsyncTask<Book, Void, Void> {
        private BookDao bookDao;

        public UpdateBookAsyncTask(BookDao bookDao) {
            this.bookDao = bookDao;
        }

        @Override
        protected Void doInBackground(Book... books) {
            bookDao.update(books[0]);
            return null;
        }
    }
}
