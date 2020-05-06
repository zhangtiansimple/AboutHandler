package com.example.abouthandler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HandlerForMainThreadActivity extends AppCompatActivity {

    private static final int MAIN_THREAD_UPDATE_UI = 0x11;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == MAIN_THREAD_UPDATE_UI) {
                mTv.setText((String)msg.obj);
            }
        }
    };

    private TextView mTv;
    private Button mBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_for_main_thread);
        initView();
    }

    private void initView() {
        mTv = findViewById(R.id.tv_mian_thread);
        mTv.setText("我是在主线程设置的消息");

        mBtn = findViewById(R.id.btn_mian_thread);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUI();
            }
        });
    }

    private void updateUI() {
        Message message = Message.obtain();
        message.what = MAIN_THREAD_UPDATE_UI;
        message.obj = "我是子线程更新的内容";
        handler.sendMessage(message);
    }
}
