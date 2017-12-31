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
 * Created by PARITOSH on 12/31/2017.
 */

public class HistoryDialogue extends AppCompatDialogFragment {
    private TextView bookName, author, descrip, timeLeft;
    private String bn, an, dsc;
    private long timeStampStart, timeStampEnd;
    private HistoryInfoListener listener;
    private int flag;
    private static int YES = 1;
    private static int NO = 0;

    public HistoryDialogue() {
    }

    @SuppressLint("ValidFragment")
    public HistoryDialogue(String bn, String an, String dsc, long timeStampStart, long timeStampEnd) {
        this.bn = bn;
        this.an = an;
        this.dsc = dsc;
        this.timeStampStart = timeStampStart;
        this.timeStampEnd = timeStampEnd;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.info_books_dialogue, null);

        builder.setView(view).setTitle("Book description")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        flag = YES;
                        listener.getInfoFromDialogue(flag);
                    // add negative button if needed in future

                    }
                });

        bookName = view.findViewById(R.id.book_info_name);
        author = view.findViewById(R.id.author_info_name);
        descrip = view.findViewById(R.id.desc_info_display);
        timeLeft = view.findViewById(R.id.time_info_display);

        bookName.setText(bn);
        author.setText(an);
        descrip.setText(dsc);

        //long CurrentTime = System.currentTimeMillis() / 1000L;
        //long timeL = timeStamp - CurrentTime;
        //int daysLeft = (int) timeL/86400;

        Date end = new Date(timeStampEnd * 1000L);
        Date start = new Date(timeStampStart * 1000L);
        //'('EEE')'
        SimpleDateFormat sdf = new SimpleDateFormat("dd'('EEE')' MMM YY");
        String startDate = sdf.format(start);
        String EndDate = sdf.format(end);
        timeLeft.setText("Issued on " + startDate + "\n" + "Submited on " + EndDate);


        return builder.create();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (HistoryDialogue.HistoryInfoListener) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString() + "must implement the history dialogue listener");
        }
    }

    public interface HistoryInfoListener {
        void getInfoFromDialogue(int Flag);
        //implement the flag to delete field or to do something...
    }


}

