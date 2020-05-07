package com.example.abouthandler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HandlerForSubThreadActivity extends AppCompatActivity {

    private static final String TAG = "HandlerForSubThread";

    private Handler mSubThreadHandler;

    private Button mSendBtn, mQuitBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_for_sub_thread);
        initThreadWithLooper();
        initView();
    }

    private void sendMessage() {
        if (mSubThreadHandler == null) {
            return;
        }
        mSubThreadHandler.sendEmptyMessage(10086);
    }

    private void initThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mSubThreadHandler = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        Log.e(TAG, "received empty message!");
                    }
                };
            }
        }).start();
    }

    private void initThreadWithLooper() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();

                mSubThreadHandler = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        Log.e(TAG, "received empty message!");
                    }
                };

                Log.e(TAG, "sub thread handler is running");
                Looper.loop();
                Log.e(TAG, "sub thread handler is quit");
            }
        }).start();
    }

    private void quit() {
        if (mSubThreadHandler == null) {
            return;
        }
        mSubThreadHandler.getLooper().quit();
    }

    private void initView() {
        mSendBtn = findViewById(R.id.btn_send);
        mSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        mQuitBtn = findViewById(R.id.btn_quit);
        mQuitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quit();
            }
        });
    }
}
