package com.example.mylibrary;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private  static final  String ALL_BOOKS = "allBooks";
    private static final String ALREADY_READ_BOOKS ="alreadyReadBooks";
    private static final String CURRENTLY_READING ="currentlyReading";
    private static final String WISHLIST ="wishlist";
    private static final String FAVOURITE ="favourite_books";


    private static Utils instance;
    private static SharedPreferences sharedPreferences;

    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadyReadBook;
    private static ArrayList<Book> currentlyReadingBook;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> favouriteBooks;



    private Utils(Context context) {
        sharedPreferences = context.getSharedPreferences("alternate_db",Context.MODE_PRIVATE);


        if (getAllBooks()==null){

            initData();

        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if (getAlreadyReadBook() == null){
            editor.putString(ALREADY_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (getCurrentlyReadingBook() == null){
            editor.putString(CURRENTLY_READING, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if (getWantToReadBooks() == null){
            editor.putString(WISHLIST, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if (getFavouriteBooks() == null){
            editor.putString(FAVOURITE, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }


    }

    private void initData() {
        //TODO : add Initial data
        ArrayList<Book> books=new ArrayList<>();
        books.add(new Book(1, "1Q84", "Haruki Murakami", 1350, "https://upload.wikimedia.org/wikipedia/en/thumb/5/59/1Q84_%28United_States_edition%29.jpg/220px-1Q84_%28United_States_edition%29.jpg","A work of maddenning brilliance",
                "Long Description"));
        books.add(new Book(2, "The Myth Of Sisyphus", "Albert Camus", 300, "http://ecx.images-amazon.com/images/I/51SM%2BBv%2BWeL._SY445_QL70_.jpg","One of the most influential works of this century, The Myth of Sisyphus and Other Essays is a crucial exposition of existentialist thought. Influenced by works such as Don Juan and the novels of Kafka, these essays begin with a meditation on suicide; the question of living or not living in a universe devoid of order or meaning.",
                "Long Description"));
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS,gson.toJson(books));
        editor.commit();



    }

    public static Utils getInstance(Context context) {
        if (instance != null){
            return instance;
        }else{
            instance = new Utils(context);
            return instance;
        }

    }

    public  ArrayList<Book> getAllBooks() {
    Gson gson = new Gson();
    Type type = new TypeToken<ArrayList<Book>>(){}.getType();
    ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS, null),type);
    return  books;



    }

    public static ArrayList<Book> getAlreadyReadBook() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS, null),type);
        return  books;


    }

    public static ArrayList<Book> getCurrentlyReadingBook() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING, null),type);
        return  books;


    }

    public static ArrayList<Book> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(WISHLIST, null),type);
        return  books;

    }

    public static ArrayList<Book> getFavouriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(FAVOURITE, null),type);
        return  books;

    }

    public  Book getBookById(int id){
        ArrayList<Book> books = getAllBooks();
        if (books != null){
            for (Book b:books
            ) {
                if (b.getId() ==id){
                    return b;
                }

            }
        }

        return null;
    }

    public boolean addToAlreadyRead(Book books){
        ArrayList<Book> book = getAlreadyReadBook();
        if (book !=null){
            if (book.add(books)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOKS);
                editor.putString(ALREADY_READ_BOOKS, gson.toJson(book));
                editor.commit();
                return true;
            }
        }
        return false;


    }

    public boolean addToWantToRead(Book books){
        ArrayList<Book> book = getWantToReadBooks();
        if (book !=null){
            if (book.add(books)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WISHLIST);
                editor.putString(WISHLIST, gson.toJson(book));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToCurrentlyReading(Book books){
        ArrayList<Book> book = getCurrentlyReadingBook();
        if (book !=null){
            if (book.add(books)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING);
                editor.putString(CURRENTLY_READING, gson.toJson(book));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToFav(Book books){
        ArrayList<Book> book = getFavouriteBooks();
        if (book !=null){
            if (book.add(books)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVOURITE);
                editor.putString(FAVOURITE, gson.toJson(book));
                editor.commit();
                return true;
            }
        }
        return false;
    }


    public boolean removeFromAlreadyRead(Book books){
        ArrayList<Book> book = getAlreadyReadBook();
        if(book != null){
            for (Book b: book){
                if (b.getId() == books.getId()){
                    if (book.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALREADY_READ_BOOKS);
                        editor.putString(ALREADY_READ_BOOKS, gson.toJson(book));
                        editor.commit();
                        return true;

                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromCurrentlyReading(Book books){
        ArrayList<Book> book = getCurrentlyReadingBook();
        if(book != null){
            for (Book b: book){
                if (b.getId() == books.getId()){
                    if (book.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING);
                        editor.putString(CURRENTLY_READING, gson.toJson(book));
                        editor.commit();
                        return true;

                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromFav(Book books){
        ArrayList<Book> book = getFavouriteBooks();
        if(book != null){
            for (Book b: book){
                if (b.getId() == books.getId()){
                    if (book.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVOURITE);
                        editor.putString(FAVOURITE, gson.toJson(book));
                        editor.commit();
                        return true;

                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromWishList(Book books){
        ArrayList<Book> book = getAlreadyReadBook();
        if(book != null){
            for (Book b: book){
                if (b.getId() == books.getId()){
                    if (book.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WISHLIST);
                        editor.putString(WISHLIST, gson.toJson(book));
                        editor.commit();
                        return true;

                    }
                }
            }
        }
        return false;
    }

}
