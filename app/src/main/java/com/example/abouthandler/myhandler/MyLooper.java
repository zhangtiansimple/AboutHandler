package com.example.abouthandler.myhandler;

public class MyLooper {
    MyMessageQueue myMessageQueue;

    private static ThreadLocal<MyLooper> threadLocal = new ThreadLocal<>();

    public MyLooper() {
        myMessageQueue = new MyMessageQueue();
    }

    public static void prepare() {
        if (threadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        threadLocal.set(new MyLooper());
        System.out.println("My looper is prepare!");
    }

    public static MyLooper getMyLooper() {
        return threadLocal.get();
    }

    public static void loop() {
        MyLooper myLooper = getMyLooper();
        MyMessage myMessage;
        for(;;) {
            myMessage = myLooper.myMessageQueue.next();
            if (myMessage == null || myMessage.target == null) {
                System.out.println("empty message");
                continue;
            }
            System.out.println("looper new message, send");
            myMessage.target.dispatchMessage(myMessage);
        }
    }
}
