package com.example.abouthandler;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HandlerThreadActivity extends AppCompatActivity {

    private static final String TAG = "HandlerThreadActivity";

    private HandlerThread mHandlerThread;
    private Handler mSubThreadHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initHandlerThread();
        initThread();
        sendEmptyMessage();
    }

    private void initHandlerThread() {
        mHandlerThread = new HandlerThread("any name");
        mHandlerThread.start();
    }

    private void initThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mSubThreadHandler = new Handler(mHandlerThread.getLooper()) {
                    @Override
                    public void handleMessage(Message msg) {
                        Log.e(TAG, "received empty message!");
                    }
                };
            }
        }).start();
    }

    private void sendEmptyMessage() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSubThreadHandler.sendEmptyMessage(10086);
            }
        }, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandlerThread != null) {
            mHandlerThread.quit();
        }
    }
}
