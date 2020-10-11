package com.example.mylibrary;

import java.util.ArrayList;

public class Utils {

    private static Utils instance;

    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadyReadBook;
    private static ArrayList<Book> currentlyReadingBook;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> favouriteBooks;



    private Utils() {
        if (allBooks==null){
            allBooks = new ArrayList<>();
            initData();
        }
        if (alreadyReadBook == null){
            alreadyReadBook = new ArrayList<>();
        }
        if (currentlyReadingBook == null){
            currentlyReadingBook = new ArrayList<>();
        }

        if (wantToReadBooks == null){
            wantToReadBooks = new ArrayList<>();
        }

        if (favouriteBooks == null){
            favouriteBooks = new ArrayList<>();
        }


    }

    private void initData() {
        //TODO : add Initial data
        ArrayList<Book> books=new ArrayList<>();
        allBooks.add(new Book(1, "1Q84", "Haruki Murakami", 1350, "https://upload.wikimedia.org/wikipedia/en/thumb/5/59/1Q84_%28United_States_edition%29.jpg/220px-1Q84_%28United_States_edition%29.jpg","A work of maddenning brilliance",
                "Long Description"));
        allBooks.add(new Book(2, "The Myth Of Sisyphus", "Albert Camus", 300, "http://ecx.images-amazon.com/images/I/51SM%2BBv%2BWeL._SY445_QL70_.jpg","One of the most influential works of this century, The Myth of Sisyphus and Other Essays is a crucial exposition of existentialist thought. Influenced by works such as Don Juan and the novels of Kafka, these essays begin with a meditation on suicide; the question of living or not living in a universe devoid of order or meaning.",
                "Long Description"));
    }

    public static Utils getInstance() {
        if (instance != null){
            return instance;
        }else{
            instance = new Utils();
            return instance;
        }

    }

    public static ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Book> getAlreadyReadBook() {
        return alreadyReadBook;
    }

    public static ArrayList<Book> getCurrentlyReadingBook() {
        return currentlyReadingBook;
    }

    public static ArrayList<Book> getWantToReadBooks() {
        return wantToReadBooks;
    }

    public static ArrayList<Book> getFavouriteBooks() {
        return favouriteBooks;
    }
    public  Book getBookById(int id){
        for (Book b:allBooks
             ) {
            if (b.getId() ==id){
                return b;
            }

        }
        return null;
    }

}
