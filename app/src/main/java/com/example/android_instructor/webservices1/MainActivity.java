package com.example.android_instructor.webservices1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private Button btnGetPosts;
    private Button btnGetPost;
    private Button btnPost;

    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        btnGetPosts = (Button)findViewById(R.id.btnGetPosts);
        btnGetPost = (Button)findViewById(R.id.btnGetPost);
        btnPost = (Button)findViewById(R.id.btnPost);

        btnGetPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnGetPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
