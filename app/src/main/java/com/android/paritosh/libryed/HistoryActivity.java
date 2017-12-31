package com.android.paritosh.libryed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity implements HistoryDialogue.HistoryInfoListener {

    private ListView bookList;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mFirebaseUser;
    private BooksInformation mBookInformation;

    private ArrayList<BooksInformation> booksArrayList;
    private HistoryAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        bookList = findViewById(R.id.books_history_list);

        booksArrayList = new ArrayList<>();
        bookAdapter = new HistoryAdapter(this, booksArrayList);
        bookList.setAdapter(bookAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        //add the ref here...
        mDatabaseReference = mFirebaseDatabase.getReference("userData");
        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        mDatabaseReference.child(mFirebaseUser.getUid()).child("History")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        BooksInformation booksInformation = dataSnapshot.getValue(BooksInformation.class);
                        booksArrayList.add(booksInformation);
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

        bookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BooksInformation booksInformation = booksArrayList.get(i);
                //the above line is used to get the value from the item of position i

                String bookN, An, dscr;
                long TEnd,TStart;
                bookN = booksInformation.getBookName();
                An = booksInformation.getauthor();
                dscr = booksInformation.getDescription();
                TStart = booksInformation.getTimeStampStart();
                TEnd = booksInformation.getTimeStampEnd();
                HistoryDialogue historyDialogue = new HistoryDialogue(bookN, An, dscr, TStart, TEnd);
                historyDialogue.show(getSupportFragmentManager(), "history dialogue");
                //to set the current object to the class object to pass it in history branch
                mBookInformation = booksInformation;

            }
        });

    }

    @Override
    public void getInfoFromDialogue(int Flag) {

    }
}
