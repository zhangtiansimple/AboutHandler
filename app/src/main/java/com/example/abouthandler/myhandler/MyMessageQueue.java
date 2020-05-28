package com.example.abouthandler.myhandler;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyMessageQueue {

    MyMessage[] messages;
    int addIndex;
    private int messageSum;
    private int getIndex;

    Lock lock;
    Condition getCondition;
    Condition addCondition;

    public MyMessageQueue() {
        messages = new MyMessage[10];
        lock = new ReentrantLock();
        getCondition = lock.newCondition();
        addCondition = lock.newCondition();
    }

    MyMessage next(){
        MyMessage msg = null;
        try {
            lock.lock();
            while (messageSum <= 0){
                System.out.println("MessageQueue is empty, await!");
                getCondition.await();
            }
            msg = messages[getIndex];
            messages[getIndex] = null;
            getIndex = (++getIndex >= messages.length)?0: getIndex;
            messageSum--;

            addCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return msg;

    }

    public void enqueueMessage(MyMessage message){

        try {
            lock.lock();
            while (messageSum >= messages.length){
                System.out.println("MessageQueue is empty, await!");
                addCondition.await();
            }
            messages[addIndex] = message;
            addIndex = (++addIndex >= messages.length)?0: addIndex;
            messageSum++;
            getCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}