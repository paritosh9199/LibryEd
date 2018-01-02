package com.android.paritosh.libryed;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends AppCompatActivity {
    MaterialSearchView searchView;
    ListView lstView;

    String[] lstSource = {

            "Intro to C++",
            "A Book on C",
            "Introduction on C",
            "Dld",
            "Mfcs",
            "java",
            "mechanics",
            "m4",
            "cp",
            "Improve your English",
            "Applied Physics",
            "chemistry",
            "it_ws",
            "Introduction on C++",
            "Introduction on java",
            "Oracle",
            "Elementary theory of numbers",
            "Let us C",
            "Computers and Information Technology",
            "Engineering Mathematics",
            "Cambridge International of idioms",
            "Barrons How to prepare for the TOEFEL",
            "Fundamentals of Data Structures",
            "English For Proffessional Students",
            "Net Works and Systems",
            "Engineering Mathematics-1",
            "Engineering Mathematics-2",
            "Engineering Mathematics-3",
            " Engineering Mathematics-4",
            "Networks Lines And Fields",
            "Data Structures Using C And C++",
            "Engineering Drawing and Graphics",
            "Oxford Dictionary",
            "Republic",
            "A Practical English Grammer Exersises-I",
            "Electronic Devices and Circuit Theory",
            "Network Analysis",
            "Toefl Practice Tests",
            "Engineering Drawing",
            " Data Ware Housing in the real World",
            "Introduction to the Intel Family of Microprocessors",
            "System Software",
            "Power System Analysis",
            "Object Oriented analysis and design",
            "Unified Modeling language user guide",
            "Prolog",
            "Introduction to Artificial Intelligence",
            "Computer Network and Internets",
            "Digital And Analog Communications systems",
            "Introduction to Database Systems",
            "C How to Program",
            "C++ How to Programme",
            "Modern Control Systems",
            "Essl Guide to  Wireless Communication Application",
            "I.P. Telephony",
            "Thinking in C++",
            "Thinking in Java",
            "Electronic Devices",
            "UML Distilled",
            "Neural Networks",
            "Computer system design and architecture",
            "Modern systems analysis and design",
            "Managing the software process",
            "Introduction to expert systems",
            "Doftware Rcuse",
            "CMM In Practice",
            "Art of Computer Programming",
            "Applying UML and Patterns",
            "C++ Premier",
            "Engineering Management",
            "Modern Database Management",
            "Effective C++",
            "More Effective C++",
            "Application Specific Integrated Circuits",
            "Operating Systems Modern Perspective",
            "Discrete Time Signal Processing",
            "Distributed Database Systems",
            "Java Server Pages",
            "Radio Frequency and Microwoven Electronics illustrated",
            "Algorhithms in C",
            "Programming Languages",
            "Advanced Computer Architectures A Design Space Approach",
            "Wireless Aplplication Protocol",
            "Digital Communications  Fund Applications",
            "Software Engineering",


};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_navigation_arrow_back_inverted);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Books Library");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        lstView = findViewById(R.id.lstView);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,lstSource);
        lstView.setAdapter(adapter);


        searchView = (MaterialSearchView)findViewById(R.id.search_view);

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

                //If closed Search View , lstView will return default
                lstView = findViewById(R.id.lstView);
                ArrayAdapter adapter = new ArrayAdapter(SearchActivity.this,android.R.layout.simple_list_item_1,lstSource);
                lstView.setAdapter(adapter);

            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText != null && !newText.isEmpty()){
                    List<String> lstFound = new ArrayList<String>();
                    for(String item:lstSource){
                        String s1= item.toLowerCase();
                        String s2 = newText.toLowerCase();
                        if(s1.contains(s2))
                            lstFound.add(item);
                    }

                    ArrayAdapter adapter = new ArrayAdapter(SearchActivity.this,android.R.layout.simple_list_item_1,lstFound);
                    lstView.setAdapter(adapter);
                }
                else{
                    //if search text is null
                    //return default
                    ArrayAdapter adapter = new ArrayAdapter(SearchActivity.this,android.R.layout.simple_list_item_1,lstSource);
                    lstView.setAdapter(adapter);
                }
                return true;
            }

        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }
}

