package com.example.abouthandler.myhandler;

import android.os.Message;

public class MyHandler {

    private MyLooper myLooper;
    private MyMessageQueue myMessageQueue;

    public MyHandler() {
        myLooper = MyLooper.getMyLooper();
        myMessageQueue = myLooper.myMessageQueue;
    }

    public void sendMessage(MyMessage myMessage) {
        myMessage.target = this;
        myMessageQueue.enqueueMessage(myMessage);
    }

    public void handleMessage(MyMessage myMessage) {

    }

    public void dispatchMessage(MyMessage myMessage) {
        handleMessage(myMessage);
    }
}
