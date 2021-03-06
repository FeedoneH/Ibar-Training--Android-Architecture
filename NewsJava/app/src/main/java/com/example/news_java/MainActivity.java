package com.example.news_java;

import android.content.Intent;
import android.os.Bundle;

import com.example.news_java.databinding.ActivityMainBinding;
import com.example.news_java.model.Book;
import com.example.news_java.model.Category;
import com.example.news_java.viewmodel.MainActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;
    MainActivityViewModel mainActivityViewModel;
    private ArrayList<Category> categoriesList;
    private ArrayList<Book> booksList;
    private Category selectedCategory;
    private RecyclerView booksRecyclerView;
    private BooksAdapter booksAdapter;
    private int selectedBookId;
    public static final int ADD_BOOK_REQUEST_CODE = 1;
    public static final int EDIT_BOOK_REQUEST_CODE = 2;
    private MainActivityOnClickHandlers handlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        handlers = new MainActivityOnClickHandlers();
        activityMainBinding.setClickHandler(handlers);

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        mainActivityViewModel.getAllcategories().observe(this, categories -> {

            categoriesList = (ArrayList<Category>) categories;
            for (Category c : categories) {

                Log.i("MyTAG", c.getCategoryName());
            }
            showOnSpinner();
        });

    }

    private void showOnSpinner() {

        ArrayAdapter<Category> categoryArrayAdapter = new ArrayAdapter<Category>(this, R.layout.spinner_item, categoriesList);
        categoryArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        activityMainBinding.setSpinnerAdapter(categoryArrayAdapter);


    }

    private void loadBooksArrayList(int categoryId) {
        mainActivityViewModel.getBooks(categoryId).observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(@Nullable List<Book> books) {
                booksList = (ArrayList<Book>) books;
                loadRecyclerView();
            }
        });

    }

    private void loadRecyclerView() {

        booksRecyclerView = activityMainBinding.secondaryLayout.recyclerView;
        booksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        booksRecyclerView.setHasFixedSize(true);

        booksAdapter = new BooksAdapter();
        booksRecyclerView.setAdapter(booksAdapter);

        booksAdapter.setBooks(booksList);
        booksAdapter.setListener(book -> {
            selectedBookId = book.getBookID();

            Intent intent = new Intent(MainActivity.this, AddAndEditActivity.class);
            intent.putExtra(AddAndEditActivity.BOOK_ID, selectedBookId);
            intent.putExtra(AddAndEditActivity.BOOK_NAME, book.getBookName());
            intent.putExtra(AddAndEditActivity.UNIT_PRICE, book.getUnitPrice());
            startActivityForResult(intent, EDIT_BOOK_REQUEST_CODE);
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                Book bookToDelete = booksList.get(viewHolder.getAdapterPosition());
                mainActivityViewModel.deleteBook(bookToDelete);
            }
        }).attachToRecyclerView(booksRecyclerView);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class MainActivityOnClickHandlers {
        public void fabBtnHandler(View view) {
            Log.i("mainn", "Hello world");

            Intent intent = new Intent(MainActivity.this, AddAndEditActivity.class);
            startActivityForResult(intent, ADD_BOOK_REQUEST_CODE);

        }


        public void onSelectItem(AdapterView<?> parent, View view, int pos, long id) {

            selectedCategory = (Category) parent.getItemAtPosition(pos);

            String message = " id is " + selectedCategory.getId() + "\n name is " + selectedCategory.getCategoryName() + "\n email is " + selectedCategory.getCategoryDescription();
            loadBooksArrayList(selectedCategory.getId());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         int selectedCategoryId = selectedCategory.getId();
        Log.i("addedit", "onActivityResult: activity result");
         if(requestCode==ADD_BOOK_REQUEST_CODE && resultCode==RESULT_OK){
             Book book=new Book();
             book.setCategoryId(selectedCategoryId);
             book.setBookName(data.getStringExtra(AddAndEditActivity.BOOK_NAME));
             book.setUnitPrice(data.getStringExtra(AddAndEditActivity.UNIT_PRICE));
             mainActivityViewModel.insertBook(book);

         }
         else if(requestCode==EDIT_BOOK_REQUEST_CODE && resultCode==RESULT_OK){
             Book book=new Book();
             book.setCategoryId(selectedCategoryId);
             book.setBookID(selectedBookId);
             book.setBookName(data.getStringExtra(AddAndEditActivity.BOOK_NAME));
             book.setUnitPrice(data.getStringExtra(AddAndEditActivity.UNIT_PRICE));
             mainActivityViewModel.updateBook(book);
         }
    }

}