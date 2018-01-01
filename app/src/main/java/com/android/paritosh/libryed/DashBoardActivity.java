package com.android.paritosh.libryed;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.R.attr.data;
import static android.R.attr.value;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;


public class DashBoardActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView valueFromUser;
    private TextView email;
    private Button saveInfo;
    private EditText name, halltckt;
    private TextView date,day;


    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase database;
    private String displayName;
    private Menu m1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        setContentView(R.layout.activity_dash_board);

        Toolbar toolbar = findViewById(R.id.infobar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(
                new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        // Handle menu item click event

                        int id=item.getItemId();

                        switch (id)
                        {
                            case R.id.logout_menu:
                                firebaseAuth.signOut();
                                startActivity(new Intent(DashBoardActivity.this, LoginActivity.class));
                                finish();
                            case R.id.exit:
                                if (Build.VERSION.SDK_INT >= 21)
                                    finishAndRemoveTask();
                                else
                                    finish();
                                System.exit(0);


                        }
                        return true;
                    }
                });
        //().setHomeAsUpIndicator(R.drawable.ic_action_navigation_arrow_back_inverted);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("Lib");

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/calibril.ttf");
        email = findViewById(R.id.UserEmail);
        day = findViewById(R.id.day);
        date = findViewById(R.id.date);

        email.setTypeface(custom_font);
        day.setTypeface(custom_font);
        date.setTypeface(custom_font);


        String displayName = user.getDisplayName();
        /*
        for (UserInfo userInfo : user.getProviderData()) {
            if (displayName == null && userInfo.getDisplayName() != null) {
                displayName = userInfo.getDisplayName();
                email.setText("Hello \n" + displayName);
            }
        }*/

        long CurrentTime = System.currentTimeMillis() / 1000L;


        Date d = new Date(CurrentTime*1000L);
        //'('EEE')'
        SimpleDateFormat sd1 = new SimpleDateFormat("EEEE");
        SimpleDateFormat sd2 = new SimpleDateFormat("dd MMMM ");
        String dayF = sd1.format(d);
        String dateF = sd2.format(d);
        day.setTypeface(day.getTypeface(), Typeface.BOLD);
        day.setText(""+dayF);
        date.setText(" "+dateF);



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

        email.setText("Hello \n" + displayName);
    }


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.log_out,menu);
        m1= menu;

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {




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
