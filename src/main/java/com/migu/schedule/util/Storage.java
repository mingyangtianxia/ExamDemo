package com.migu.schedule.util;

import java.util.Collections;
import java.util.LinkedList;

public class Storage
{
    // 仓库最大存储量
    private final int MAX_SIZE = 1000;

    // 仓库存储的载体
    private LinkedList<TaskP> list = new LinkedList<TaskP>();

    // 生产产品
    public void produce(int producer,int consumption)
    {
        synchronized (list)
        {
            // 如果仓库已满
            while (list.size() == MAX_SIZE)
            {
                System.out.println("仓库已满，【"+producer+"】： 暂时不能执行生产任务!");
                try
                {
                    // 由于条件不满足，生产阻塞
                    list.wait();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

            // 生产产品            
            list.add(new TaskP(producer,consumption));            

            System.out.println("【"+producer+"】：生产了一个产品\t【现仓储量为】:" + list.size());

            list.notifyAll();
        }
    }

    // 消费产品
    public void consume(int consumer)
    {
        synchronized (list)
        {
            //如果仓库存储量不足
            while (list.size()==0)
            {
                System.out.println("仓库已空，【"+consumer+"】： 暂时不能执行消费任务!");
                try
                {
                    // 由于条件不满足，消费阻塞
                    list.wait();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            
            list.remove();
            System.out.println("【"+consumer+"】：消费了一个产品\t【现仓储量为】:" + list.size());
            list.notifyAll();
        }
    }
    
    // 消费产品
    public void consumeAll(NodeServer server,int threshold)
    {
        synchronized (list)
        {
            //如果仓库存储量不足
            while (list.size()==0)
            {
                System.out.println("仓库已空，： 暂时不能执行消费任务!");
                try
                {
                    // 由于条件不满足，消费阻塞
                    list.wait();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

            list.remove();
            System.out.println("消费了全部产品\t【现仓储量为】:" + list.size());
            list.notifyAll();
        }
    }

    public LinkedList<TaskP> getList()
    {
        return list;
    }

    public void setList(LinkedList<TaskP> list)
    {
        this.list = list;
    }

    public int getMAX_SIZE()
    {
        return MAX_SIZE;
    }
}