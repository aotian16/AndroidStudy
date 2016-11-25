package com.qefee.pj.testfresco;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

public class CircleViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Uri uri = Uri.parse("https://pic4.zhimg.com/03b2d57be62b30f158f48f388c8f3f33_b.png");
        SimpleDraweeView commonImageView = (SimpleDraweeView) findViewById(R.id.commonImageView);
        commonImageView.setImageURI(uri);

        SimpleDraweeView circleImageView = (SimpleDraweeView) findViewById(R.id.circleImageView);
        circleImageView.setImageURI(uri);

        SimpleDraweeView roundedImageView = (SimpleDraweeView) findViewById(R.id.roundedImageView);
        roundedImageView.setImageURI(uri);
    }

}
