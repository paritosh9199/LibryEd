package com.android.paritosh.libryed;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by PARITOSH on 12/26/2017.
 */

//this Dialogue opens when an item is clicked on in the list View
//in the @BooksActivity
public class BooksItemDialogue extends AppCompatDialogFragment {
    private TextView bookName,author,descrip,timeLeft;
    private String bn,an,dsc;
    private long timeStamp;
    private BooksInfoListener listener;
    private int flag;
    private static int YES = 1;
    private static int NO = 0;

    public BooksItemDialogue() {
    }

    @SuppressLint("ValidFragment")
    public BooksItemDialogue(String bn, String an, String dsc, long timeStamp) {
        this.bn = bn;
        this.an = an;
        this.dsc = dsc;
        this.timeStamp = timeStamp;
    }

    /*
    public static BooksItemDialogue newInstance(String bn, String an, String dsc, long timeStamp) {
        Bundle bundle = new Bundle();
        bundle.putString("name", bn);
        bundle.putString("author", an);
        bundle.putString("desc", dsc);
        bundle.putLong("timeStamp", timeStamp);
        BooksItemDialogue fragment = new BooksItemDialogue(bn,an,dsc,timeStamp);
        fragment.setArguments(bundle);

        return fragment;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            bn = bundle.getString("name");
            an = bundle.getString("author");
            dsc = bundle.getString("desc");
            timeStamp = bundle.getLong("timeStamp");

        }
    }
    */

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.info_books_dialogue, null);

        builder.setView(view).setTitle("Book description").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        })
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        flag = YES;
                        listener.getInfoFromDialogue(flag);


                    }
                });

        bookName  = view.findViewById(R.id.book_info_name);
        author = view.findViewById(R.id.author_info_name);
        descrip = view.findViewById(R.id.desc_info_display);
        timeLeft = view.findViewById(R.id.time_info_display);

        bookName.setText(bn);
        author.setText(an);
        descrip.setText(dsc);

        long CurrentTime = System.currentTimeMillis() / 1000L;
        long timeL = timeStamp - CurrentTime;
        int daysLeft = (int) timeL/86400;

        Date end = new Date(timeStamp*1000L);
        //'('EEE')'
        SimpleDateFormat sdf = new SimpleDateFormat("dd'('EEE')' MMM YY");
        String formattedDate = sdf.format(end);
        timeLeft.setText("Due Date by"+ formattedDate+"\n"+daysLeft+" Days Left before submittion");


        return builder.create();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (BooksItemDialogue.BooksInfoListener) context;
        }catch (Exception e){
            throw new ClassCastException(context.toString() + "must implement the dialogue listener");
        }
    }



    public interface BooksInfoListener{
        void getInfoFromDialogue(int Flag);
        //implement the fla in the positive button of the dialogue..!
    }


}
