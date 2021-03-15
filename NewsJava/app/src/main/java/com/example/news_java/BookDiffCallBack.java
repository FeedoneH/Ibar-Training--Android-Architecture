package com.example.news_java;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.example.news_java.model.Book;

import java.util.ArrayList;

public class BookDiffCallBack extends DiffUtil.Callback {
    ArrayList<Book> oldBookList;
    ArrayList<Book> newBookList;

    public BookDiffCallBack(ArrayList<Book> oldBookList, ArrayList<Book> newBookList) {
        this.oldBookList = oldBookList;
        this.newBookList = newBookList;
    }

    @Override
    public int getOldListSize() {
        return oldBookList.size();
    }

    @Override
    public int getNewListSize() {
        return newBookList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldBookPosition, int newBookPosition) {
        return oldBookList.get(oldBookPosition).getBookID() == newBookList.get(newBookPosition).getBookID();
    }

    @Override
    public boolean areContentsTheSame(int oldBookPosition, int newBookPosition) {
        return oldBookList.get(oldBookPosition).equals(newBookList.get(newBookPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldBookPosition, int newBookPosition) {
        return super.getChangePayload(oldBookPosition, newBookPosition);
    }
}
