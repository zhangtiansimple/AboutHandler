package com.example.abouthandler.myhandler;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.abouthandler.R;

public class MyHandlerActivity extends AppCompatActivity {

    private MyHandler myHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myhandler);
        test();
    }

    private void test() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MyLooper.prepare();
                myHandler = new MyHandler() {
                    @Override
                    public void handleMessage(MyMessage message) {
                        super.handleMessage(message);
                        System.out.println(Thread.currentThread().getName() + " received ：" + message.object);
                    }
                };
                MyLooper.loop();
            }
        }).start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                MyMessage message = MyMessage.obtain();
                message.object = Thread.currentThread().getName() + " send message";
                myHandler.sendMessage(message);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                MyMessage message = new MyMessage();
                message.object = Thread.currentThread().getName() + " send message";
                myHandler.sendMessage(message);
            }
        }).start();

    }
}
