package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class AlreadyReadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_already_read);
        RecyclerView recView = findViewById(R.id.alreadyReadRecView);
        BooksRecyclerViewAdapter adap = new BooksRecyclerViewAdapter(this,"alreadyRead");
        recView.setAdapter(adap);
        recView.setLayoutManager(new LinearLayoutManager(this));

        adap.setBooks(Utils.getInstance().getAlreadyReadBook());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}