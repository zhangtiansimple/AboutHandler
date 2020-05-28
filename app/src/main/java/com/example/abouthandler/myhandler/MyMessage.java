package com.example.abouthandler.myhandler;

import androidx.annotation.NonNull;

public class MyMessage {

    public int what;
    public Object object;
    public MyHandler target;

    private static final Object sPoolSync = new Object();
    private static MyMessage sPool;
    private MyMessage next;

    public static MyMessage obtain() {
        synchronized (sPoolSync) {
            if (sPool != null) {
                MyMessage myMessage = sPool;
                sPool = myMessage.next;
                myMessage.next = null;
                return myMessage;
            }
        }
        return new MyMessage();
    }

    @NonNull
    @Override
    public String toString() {
        return object.toString();
    }
}
