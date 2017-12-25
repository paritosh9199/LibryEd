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
        setContentView(R.layout.activity_books);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        search_book= (ListView) findViewById(R.id.search_book);

        ArrayList<String> arraybook=new ArrayList<>();
        arraybook.addAll(Arrays.asList(getResources().getStringArray(R.array.my_books)));

        adapter=new ArrayAdapter<String>(
                SearchActivity.this,
                android.R.layout.simple_list_item_1,
                arraybook
        );

        search_book.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);
        MenuItem item=menu.findItem(R.id.search_book);
        SearchView searchView=(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);

    }
}
