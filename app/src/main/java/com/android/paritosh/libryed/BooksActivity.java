package com.android.paritosh.libryed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class BooksActivity extends AppCompatActivity implements BooksDialogue.BooksListener {

    private Button addBooks;
    private ListView booksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        addBooks = findViewById(R.id.AddBooks);
        booksList = findViewById(R.id.BooksListView);

        addBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBookAdder();
            }
        });
    }

    public void openBookAdder(){
        BooksDialogue booksDialogue = new BooksDialogue();
        booksDialogue.show(getSupportFragmentManager(), "books dialogue");
    }

    @Override
    public void applyText(String bname, String aname, String desc) {
        //use bname , aname, desc to get the values from the dialogue box
        //the above variables contain the value of the book name , auth name, description

        // now open the firebase database object to add it into the database
        //create a custom adapter and a BookInformation class to pass the data into the cloud via the
        //adapter
    }
}
