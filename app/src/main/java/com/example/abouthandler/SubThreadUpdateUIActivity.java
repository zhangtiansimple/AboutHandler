package com.example.abouthandler;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SubThreadUpdateUIActivity extends AppCompatActivity {

    private Button mUpdateBtn;
    private TextView mContentTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_thread_update_ui);
        initView();
        firstUpdate();
//        secondUpdate();
    }

    private void initView() {
        mUpdateBtn = findViewById(R.id.btn_update);
        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThirdUpdate();
            }
        });
        mContentTv = findViewById(R.id.content_tv);
    }

    private void firstUpdate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mContentTv.setText("我是来自子线程的更新信息");
            }
        }).start();
    }

    private void secondUpdate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mContentTv.setText("我是来自子线程的更新信息");
            }
        }).start();
    }

    private void ThirdUpdate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mContentTv.setText("我是来自子线程的更新信息");
            }
        }).start();
    }
}
