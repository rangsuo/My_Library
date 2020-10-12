package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class CurrentlyReading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_reading);
        RecyclerView recView = findViewById(R.id.CurrRecView);
        BooksRecyclerViewAdapter adap = new BooksRecyclerViewAdapter(this ,"currentlyRead");
        recView.setAdapter(adap);
        recView.setLayoutManager(new LinearLayoutManager(this));

        adap.setBooks(Utils.getInstance().getCurrentlyReadingBook());
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}