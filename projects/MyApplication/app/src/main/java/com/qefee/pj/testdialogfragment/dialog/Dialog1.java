package com.qefee.pj.testdialogfragment.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Dialog1.
 * <ul>
 * <li>date: 2016/12/1</li>
 * </ul>
 *
 * @author tongjin
 */

public class Dialog1 extends DialogFragment {

    public static Dialog1 newInstance() {
        return new Dialog1();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("dialog 1");
        builder.setMessage("message to show");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(), "OK clicked", Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                dialogInterface.cancel();
            }
        });

        return builder.create();
    }
}
