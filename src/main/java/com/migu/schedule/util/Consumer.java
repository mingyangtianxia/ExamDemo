package com.migu.schedule.util;

public class Consumer extends Thread
{
    private int consumer;
    private Storage storage;

    public Consumer(Storage storage)
    {
        this.storage = storage;
    }

    @Override
    public void run()
    {
        consume(consumer);
    }

    public void consume(int consumer)
    {
        storage.consume(consumer);
    }

    public void consumeAll(NodeServer server,int threshold)
    {
        storage.consumeAll( server, threshold);
    }
    public Storage getStorage()
    {
        return storage;
    }

    public void setStorage(Storage storage)
    {
        this.storage = storage;
    }

    public int getConsumer() {
        return consumer;
    }

    public void setConsumer(int consumer) {
        this.consumer = consumer;
    }
}