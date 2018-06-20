package com.migu.schedule.util;

import java.util.List;

/***
 * 抽象观察者
 * 定义了一个update()方法，当被观察者调用notifyObservers()方法时，观察者的update()方法会被回调。
 * @author zhaojian
 *
 */
public interface Observer {
    public void update(String message);
	public List<TaskP> getConsumerList() ;
	public void setConsumerList(List<TaskP> consumerList) ;
	public void addConsumerList(TaskP taskP);
	public void update(Consumer consumer);
	public int getNodeSeq();
	public void setNodeSeq(int nodeSeq);
	public int getConsumerListConsumptionCount(TaskP taskP);
}