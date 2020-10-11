package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.opengl.ETC1Util;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {
    private TextView txtBookName, txtAuthorName,txtPages,txtDescription;
    private Button btnFav,btnCurrentlyReading,btnWantToRead,btnAlreadyRead;
    private ImageView imgBook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        txtBookName = findViewById(R.id.txtBookName);
        txtAuthorName = findViewById(R.id.txtAuthorName);
        txtPages = findViewById(R.id.txtPages);
        txtDescription= findViewById(R.id.longDesc1);

        btnFav = findViewById(R.id.btnFav);
        btnAlreadyRead = findViewById(R.id.btnAlreadyRead);
        btnCurrentlyReading = findViewById(R.id.btnCurrentlyReading);
        btnWantToRead = findViewById(R.id.btnWantToRead);
        imgBook =findViewById(R.id.imgBook);

//        String des = "A young woman named Aomame follows a taxi driver’s enigmatic suggestion and begins to notice puzzling discrepancies in the world around her. She has entered, she realizes, a parallel existence, which she calls 1Q84 —“Q is for ‘question mark.’ A world that bears a question.” Meanwhile, an aspiring writer named Tengo takes on a suspect ghostwriting project. He becomes so wrapped up with the work and its unusual author that, soon, his previously placid life begins to come unraveled.\n" +
//                "\n" +
//                "As Aomame’s and Tengo’s narratives converge over the course of this single year, we learn of the profound and tangled connections that bind them ever closer: a beautiful, dyslexic teenage girl with a unique vision; a mysterious religious cult that instigated a shoot-out with the metropolitan police; a reclusive, wealthy dowager who runs a shelter for abused women; a hideously ugly private investigator; a mild-mannered yet ruthlessly efficient bodyguard; and a peculiarly insistent television-fee collector.\n" +
//                "\n" +
//                "A love story, a mystery, a fantasy, a novel of self-discovery, a dystopia to rival George Orwell’s — 1Q84 is Haruki Murakami’s most ambitious undertaking yet: an instant best seller in his native Japan, and a tremendous feat of imagination from one of our most revered contemporary writers";
//        // TODO: Get the data from recyclerView In here
//        Book book = new Book(1, "1Q84", "Haruki Murakami", 1350, "https://upload.wikimedia.org/wikipedia/en/thumb/5/59/1Q84_%28United_States_edition%29.jpg/220px-1Q84_%28United_States_edition%29.jpg","A work of maddenning brilliance",
//                des);
        Intent intent =getIntent();
        if (intent!= null){
            int bookId = intent.getIntExtra("book_id",-1);
            if (bookId != -1){
                Book incomingBook = Utils.getInstance().getBookById(bookId);
                if (incomingBook!= null){
                    setData(incomingBook);
                    handleAlreadyReadbtn(incomingBook);
                    handleWishlist(incomingBook);
                    handleCurrentlyReading(incomingBook);
                    handleFav(incomingBook);
                }

            }
        }




    }

    private void handleWishlist(final Book incomingBook) {
        ArrayList<Book> wishlist = Utils.getInstance().getWantToReadBooks();

        boolean isWished = false;

        for (Book b: wishlist){
            if (b.getId() == incomingBook.getId())
                isWished =true;
        }
        if (isWished){
            btnWantToRead.setEnabled(false);
        }else{
            btnWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance().addToWantToRead(incomingBook)){
                        Toast.makeText(BookActivity.this, "Book Added To WishList", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, Wishlist.class);
                        startActivity(intent);

                    }else {
                        Toast.makeText(BookActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleCurrentlyReading(final Book incomingBook) {
        ArrayList<Book> currBooks = Utils.getInstance().getCurrentlyReadingBook();

        boolean isCurrRead = false;

        for (Book b: currBooks){
            if (b.getId() == incomingBook.getId())
                isCurrRead =true;
        }
        if (isCurrRead){
            btnCurrentlyReading.setEnabled(false);
        }else{
            btnCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance().addToCurrentlyReading(incomingBook)){
                        Toast.makeText(BookActivity.this, "Book Added To Currently Reading", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, CurrentlyReading.class);
                        startActivity(intent);

                    }else {
                        Toast.makeText(BookActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleFav(final Book incomingBook) {
        ArrayList<Book> favBooks = Utils.getInstance().getFavouriteBooks();

        boolean isFav = false;

        for (Book b: favBooks){
            if (b.getId() == incomingBook.getId())
                isFav =true;
        }
        if (isFav){
            btnFav.setEnabled(false);
        }else{
            btnFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance().addToFav(incomingBook)){
                        Toast.makeText(BookActivity.this, "Book Added To Favourites", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, Favourites.class);
                        startActivity(intent);

                    }else {
                        Toast.makeText(BookActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleAlreadyReadbtn(final Book incomingBook) {
        ArrayList<Book> alreadyRead = Utils.getInstance().getAlreadyReadBook();

        boolean isAlreadyRead = false;

        for (Book b: alreadyRead){
            if (b.getId() == incomingBook.getId())
                isAlreadyRead =true;
        }
        if (isAlreadyRead){
            btnAlreadyRead.setEnabled(false);
        }else{
            btnAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance().addToAlreadyRead(incomingBook)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, AlreadyReadActivity.class);
                        startActivity(intent);

                    }else {
                        Toast.makeText(BookActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }


    private void setData(Book book) {

        txtBookName.setText(book.getName());
        txtAuthorName.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap()
                .load(book.getImageUrl())
                .into(imgBook);


    }
}
