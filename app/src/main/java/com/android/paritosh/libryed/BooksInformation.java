package com.android.paritosh.libryed;

/**
 * Created by PARITOSH on 12/24/2017.
 */
public class BooksInformation {

    private String bookName;
    private String authorName;
    private String description;
    private long TimeStampStart,TimeStampEnd;

    public int getSubmitFlag() {
        return SubmitFlag;
    }

    public void setSubmitFlag(int submitFlag) {
        this.SubmitFlag = submitFlag;
    }

    private int SubmitFlag;

    public BooksInformation() {

    }

    public BooksInformation(String bookName, String authorName, long TimeStamp) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.TimeStampStart = TimeStampStart;
    }
    public BooksInformation(String bookName, String authorName, String description) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.description = description;
    }

    public BooksInformation(String bookName, String authorName, String description, long timeStampStart, long timeStampEnd) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.description = description;
        TimeStampStart = timeStampStart;
        TimeStampEnd = timeStampEnd;
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

    public long getTimeStampStart() {
        return TimeStampStart;
    }

    public void setTimeStampStart(long TimeStampStart) {
        this.TimeStampStart = TimeStampStart;
    }

    public long getTimeStampEnd() {
        return TimeStampEnd;
    }

    public void setTimeStampEnd(long TimeStampEnd) {
        this.TimeStampEnd = TimeStampEnd;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}