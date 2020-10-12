package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {

    private RecyclerView booksRecyclerView;
    private BooksRecyclerViewAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);
        overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

        booksRecyclerView = findViewById(R.id.booksRecyclerView);
        adapter = new BooksRecyclerViewAdapter(this,"allBooks");

        booksRecyclerView.setAdapter(adapter);
        booksRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setBooks(Utils.getInstance().getAllBooks());


    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_out,R.anim.slide_in);
    }
}