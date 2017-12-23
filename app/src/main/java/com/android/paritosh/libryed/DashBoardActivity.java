package com.android.paritosh.libryed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.paritosh.libryed.BooksActivity;
import com.android.paritosh.libryed.LoginActivity;
import com.android.paritosh.libryed.R;
import com.android.paritosh.libryed.UserInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.R.attr.data;
import static android.R.attr.value;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;


public class DashBoardActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView valueFromUser;
    private TextView email;
    private Button saveInfo;
    private EditText name, halltckt;


    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase database;
    private String displayName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        setContentView(R.layout.activity_dash_board);

        String displayName = user.getDisplayName();

        for (UserInfo userInfo : user.getProviderData()) {
            if (displayName == null && userInfo.getDisplayName() != null) {
                displayName = userInfo.getDisplayName();
                email.setText("welcome " + displayName);
            }
        }



        email = (TextView) findViewById(R.id.UserEmail);
        //name = (EditText) findViewById(R.id.nameUsr);
        //halltckt = (EditText) findViewById(R.id.hallUsr);
        //saveInfo = (Button) findViewById(R.id.saveUsr);


        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(DashBoardActivity.this, LoginActivity.class));
        }
        if (displayName == null) {
            displayName = user.getDisplayName();
        }

        email.setText("welcome " + displayName);
    }


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.log_out,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        switch (id)
        {
            case R.id.logout_menu:


                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(this, LoginActivity.class));

        }
        return true;
    }

    @Override
    public void onClick(View view) {



    }

    private void saveUserInformation() {
        String usrName = name.getText().toString().trim();
        String usrHallticket = halltckt.getText().toString().trim();

        UserInformation userInformation = new UserInformation(usrName, usrHallticket);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).setValue(userInformation);

        Toast.makeText(this, "Information Saved", Toast.LENGTH_LONG).show();
    }

    public void books(View view) {
        Intent intent = new Intent(DashBoardActivity.this, BooksActivity.class);
        startActivity(intent);
    }

    public void history(View view) {
        Intent intent = new Intent(DashBoardActivity.this, HistoryActivity.class);
        startActivity(intent);
    }

    public void search(View view) {
        Intent intent = new Intent(DashBoardActivity.this, SearchActivity.class);
        startActivity(intent);
    }


}
