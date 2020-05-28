package com.example.abouthandler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.abouthandler.myhandler.MyHandlerActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(HandlerForMainThreadActivity.class);
            }
        });
        findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SubThreadUpdateUIActivity.class);
            }
        });
        findViewById(R.id.btn_sub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(HandlerForSubThreadActivity.class);
            }
        });
        findViewById(R.id.btn_handler_thread).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(HandlerThreadActivity.class);
            }
        });

        findViewById(R.id.btn_normal_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(HandlerNormalQuestionActivity.class);
            }
        });
        findViewById(R.id.btn_my_handler).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MyHandlerActivity.class);
            }
        });
    }

    private void startActivity(Class cls) {
        startActivity(new Intent(this, cls));
    }
}
