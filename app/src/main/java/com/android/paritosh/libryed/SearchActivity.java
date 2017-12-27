package com.android.paritosh.libryed;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.SearchView;

        import java.lang.reflect.Array;
        import java.util.ArrayList;
        import java.util.Arrays;

public class SearchActivity extends AppCompatActivity {

    ListView search_book;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
