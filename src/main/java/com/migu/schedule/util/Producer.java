package com.migu.schedule.util;

public class Producer extends Thread
{
    private int producer;
    private Storage storage;
    private int consumption;
    public Producer(Storage storage)
    {
        this.storage = storage;
    }
    @Override
    public void run()
    {
        produce(producer,consumption);
    }

    public void produce(int producer,int consumption)
    {
        storage.produce(producer,consumption);
    }

    public int getProducer()
    {
        return producer;
    }

    public void setProducer(int producer)
    {
        this.producer = producer;
    }

    public Storage getStorage()
    {
        return storage;
    }

    public void setStorage(Storage storage)
    {
        this.storage = storage;
    }
}