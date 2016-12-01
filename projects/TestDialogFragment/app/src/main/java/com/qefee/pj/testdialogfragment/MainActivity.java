package com.qefee.pj.testdialogfragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.qefee.pj.testdialogfragment.dialog.Dialog1;
import com.qefee.pj.testdialogfragment.dialog.Dialog2;

public class MainActivity extends AppCompatActivity {

    public static final String DIALOG_TAG_1 = "dialog1";
    public static final String DIALOG_TAG_2 = "dialog2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog1.newInstance().show(getFragmentManager(), DIALOG_TAG_1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog2.newInstance().show(getFragmentManager(), DIALOG_TAG_2);
            }
        });
    }
}
