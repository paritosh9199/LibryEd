package com.android.paritosh.libryed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

//import static android.support.v7.RecyclerView;

public class BooksActivity extends AppCompatActivity implements BooksDialogue.BooksListener, BooksItemDialogue.BooksInfoListener {

    private Button addBooks;
    private ListView booksList;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mFirebaseUser;
    private BooksInformation mBookInformation;
    private int index, indexParent;

    private ArrayList<BooksInformation> booksArrayList;
    private ArrayList<String> parentKey;
    private BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        addBooks = findViewById(R.id.AddBooks);
        booksList = findViewById(R.id.BooksList);

        booksArrayList = new ArrayList<>();
        parentKey = new ArrayList<>();
        bookAdapter = new BookAdapter(this, booksArrayList);
        booksList.setAdapter(bookAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mBookInformation = new BooksInformation();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        //add the ref here...
        mDatabaseReference = mFirebaseDatabase.getReference("userData");
        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        mDatabaseReference.child(mFirebaseUser.getUid()).child("Books")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        BooksInformation booksInformation = dataSnapshot.getValue(BooksInformation.class);
                        booksArrayList.add(booksInformation);
                        parentKey.add(dataSnapshot.getKey());
                        bookAdapter.notifyDataSetChanged();

                        Log.i("info", "" + booksInformation.getBookName() + booksInformation.getauthor());

                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


        addBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBookAdder();
            }
        });
        booksList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BooksInformation booksInformation = booksArrayList.get(i);
                //the above line is used to get the value from the item of position i

                String bookN, An, dscr;
                long TEnd;
                bookN = booksInformation.getBookName();
                An = booksInformation.getauthor();
                dscr = booksInformation.getDescription();
                TEnd = booksInformation.getTimeStampEnd();
                BooksItemDialogue booksItemDialogue = new BooksItemDialogue(bookN, An, dscr, TEnd);
                booksItemDialogue.show(getSupportFragmentManager(), "books info dialogue");
                //to set the current object to the class object to pass it in history branch
                mBookInformation = booksInformation;
                index = i;


            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.books_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.history:
                Intent intent = new Intent(BooksActivity.this, HistoryActivity.class);
                startActivity(intent);

        }
        return true;
    }

    public void openBookAdder() {
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
        long startTime = System.currentTimeMillis() / 1000L;
        long endtime = startTime + 1296000;

        BooksInformation bi = new BooksInformation(bname, aname, desc, startTime, endtime);

        String uid = mFirebaseUser.getUid();

        //to add the book to books branch
        mDatabaseReference.child(uid).child("Books").push().setValue(bi);
    }

    public void historyDatabase(BooksInformation databaseObj, int SFlag) {
        if (SFlag == 1) {
            // also set a submit flag in database .setSubmitFlag while saving into history branch
            long CT = System.currentTimeMillis() / 1000L;
            String pkey = parentKey.get(index);
            databaseObj.setTimeStampEnd(CT);
            databaseObj.setSubmitFlag(1);
            mDatabaseReference.child(mFirebaseUser.getUid()).child("Books").child(pkey).setValue(null);
            mDatabaseReference.child(mFirebaseUser.getUid()).child("History").push().setValue(databaseObj);
            booksArrayList.remove(databaseObj);
            parentKey.remove(index);
            bookAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getInfoFromDialogue(int SubmitFlag) {
        //use this method to get the flag to submit the book to history
        /*
        if(SubmitFlag is yes){
               enter the books to firebase layout
               mDatabaseReference.child(uid).child("History").push().setValue(bi);
               and if possible try to delete the entry for books branch in the database
        }
        */

        if (SubmitFlag == 1) {
            historyDatabase(mBookInformation, SubmitFlag);
        } else {

        }
    }
}
