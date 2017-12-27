package com.android.paritosh.libryed;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by PARITOSH on 12/23/2017.
 */

// This dialogue is to add the book and its detail to firebase

public class BooksDialogue extends AppCompatDialogFragment  {

    private EditText bookName,authorName,desc;
    private BooksListener listener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.add_books_dialogue, null);

        builder.setView(view).setTitle("Add Book").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        })
        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String bname = bookName.getText().toString();
                String aname = authorName.getText().toString();
                String descriptionNotes = desc.getText().toString();

                listener.applyText(bname,aname,descriptionNotes);

            }
        });

        bookName  = view.findViewById(R.id.bookName);
        authorName = view.findViewById(R.id.authorName);
        desc = view.findViewById(R.id.descrName);

        return builder.create();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (BooksListener) context;
        }catch (Exception e){
            throw new ClassCastException(context.toString() + "must implement the dialogue listener");
        }
    }



    public interface BooksListener{
        void applyText(String bname,String aname, String desc);
    }
}
