package com.android.paritosh.libryed;

/**
 * Created by PARITOSH on 12/24/2017.
 */
public class BooksInformation {

    private String bookName;
    private String authorName;
    private String description;
    private int TimeStampStart,TimeStampEnd;

    public BooksInformation() {

    }

    public BooksInformation(String bookName, String authorName, int TimeStamp) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.TimeStampStart = TimeStampStart;
    }
    public BooksInformation(String bookName, String authorName, String description) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.description = description;
    }

    public BooksInformation(String bookName, String authorName) {
        this.bookName = bookName;
        this.authorName = authorName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setbook(String book) {
        this.bookName = book;
    }

    public String getauthor() {
        return authorName;
    }

    public void setauthor(String author) {
        this.authorName = author;
    }

    public int getTimeStampStart() {
        return TimeStampStart;
    }

    public void setTimeStampStart(int TimeStampStart) {
        this.TimeStampStart = TimeStampStart;
    }

    public int getTimeStampEnd() {
        return TimeStampEnd;
    }

    public void setTimeStampEnd(int TimeStampEnd) {
        this.TimeStampEnd = TimeStampEnd;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}