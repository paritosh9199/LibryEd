package com.android.paritosh.libryed;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by PARITOSH on 12/30/2017.
 */
public class HistoryAdapter extends ArrayAdapter<BooksInformation>  {
    public HistoryAdapter(@NonNull Context context, @NonNull List<BooksInformation> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View newView = convertView;
        if (newView == null) {
            newView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.history_display_layout, parent, false);
        }

        BooksInformation item = getItem(position);


        TextView bookName = newView.findViewById(R.id.books_history_display_name);
        TextView authorName = newView.findViewById(R.id.author_history_display_name);
        TextView desc = newView.findViewById(R.id.desc_history_display);
        TextView subTime = newView.findViewById(R.id.time_history_display);

        bookName.setText(item.getBookName());
        authorName.setText(item.getauthor());

        String completerDesc = item.getDescription();
        if(completerDesc.length()>=41){
            String subDesc = completerDesc.substring(0,49);
            String setDesc = subDesc+"...";
            desc.setText(setDesc);
        }else{
            desc.setText(item.getDescription());
        }
        //change the time stamp
        long d = item.getTimeStampEnd();
        Date end = new Date(d * 1000L);
        //'('EEE')'
        SimpleDateFormat sdf = new SimpleDateFormat("dd'('EEE')' MMM YY");
        String formattedDate = sdf.format(end);
        subTime.setText("submited on " + formattedDate);
        return newView;


    }

}