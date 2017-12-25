package com.android.paritosh.libryed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//import static android.support.v7.RecyclerView;

public class BooksActivity extends AppCompatActivity implements BooksDialogue.BooksListener {

    private Button addBooks;
    private RecyclerView booksList;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mFirebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        addBooks = findViewById(R.id.AddBooks);
        //booksList = findViewById(R.id.BooksList);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        //add the ref here...
        mDatabaseReference = mFirebaseDatabase.getReference("userData");
        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

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

        BooksInformation bi = new BooksInformation(bname,aname,desc);

        String uid = mFirebaseUser.getUid();

        //mDatabaseReference.push().setValue(bi);
        mDatabaseReference.child(uid).child("Books").push().setValue(bi);
        //to add history
        //mDatabaseReference.child(uid).child("History").push().setValue(bi);

    }
}
