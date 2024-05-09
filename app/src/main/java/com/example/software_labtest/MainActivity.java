package com.example.software_labtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Info {
    int units;
    String genre_name;

    // Constructor
    public Info(int units, String genre_name) {
        this.units = units;
        this.genre_name = genre_name;
    }

    // Getter for units
    public int getUnits() {
        return units;
    }

    // Setter for units
    public void setUnits(int units) {
        this.units = units;
    }
}

class Genre {
    Map<String, List<Info>> booksByGenre = new HashMap<>();

    public Genre() {
        booksByGenre.put("mystery", new ArrayList<>());
        booksByGenre.put("fantasy", new ArrayList<>());
        booksByGenre.put("thriller", new ArrayList<>());
    }

    // Method to show books by genre
    public void showBookInfoByGenre(String genre) {
        List<Info> books = booksByGenre.get(genre.toLowerCase());
        if (books == null) {
            System.out.println("Invalid genre");
            return;
        }
        for (Info book : books) {
            System.out.println("Title: " + book.genre_name + ", Units available: " + book.getUnits());
        }
    }
}

// Define the Book class
class Book {
    String title;
    String author;

    // Constructor
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    // Method to add a book to a genre
    public void addBook(String title, String author, String genre, int units, Genre genreClass) {
        Info newBook = new Info(units, title);
        List<Info> books = genreClass.booksByGenre.get(genre.toLowerCase());
        if (books == null) {
            System.out.println("Invalid genre");
            return;
        }
        books.add(newBook);
    }

    // Method to borrow a book
    public void borrowBook(String title, int units, Genre genreClass) {
        for (List<Info> books : genreClass.booksByGenre.values()) {
            for (Info book : books) {
                if (book.genre_name.equals(title) && book.getUnits() >= units) {
                    book.setUnits(book.getUnits() - units);
                    System.out.println("Book borrowed: " + title + ". Units left: " + book.getUnits());
                    return;
                }
            }
        }
        System.out.println("Book not available or insufficient units");
    }
}


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}