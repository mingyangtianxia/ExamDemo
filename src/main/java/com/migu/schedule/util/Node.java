package com.migu.schedule.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者
 * 实现了update方法
 * @author zhaojian
 *
 */
public class Node implements Observer{

    private int nodeSeq;
    private String message;
    private int tasknum;
    private List<TaskP> consumerList=new ArrayList<TaskP>();
    
    public Node(int nodeSeq) {
        this.nodeSeq = nodeSeq;
    }
    
    public void update(String message) {
        this.message = message;
        read();
    }
    
    public void update(Consumer consumer) {
        for (TaskP taskP : consumerList) {
        	consumer.consume(taskP.getProducer());
        	consume(taskP);
		}
    }
    public void consume(TaskP taskP ) {
        System.out.println(" 处理任务 " + taskP.getProducer());
    }

    public void read() {
        System.out.println(nodeSeq + " 收到推送消息： " + message);
    }

	public List<TaskP> getConsumerList() {
		return consumerList;
	}

	public void setConsumerList(List<TaskP> consumerList) {
		this.consumerList = consumerList;
	}
	
	public void addConsumerList(TaskP taskP) {
		this.consumerList.add(taskP);
	}
	
	public int getConsumerListConsumptionCount(TaskP taskP) {
		int count=0;
		for (TaskP taskP2 : consumerList) {
			count=count+taskP2.getConsumption();
		}
		return count;
	}

	public int getNodeSeq() {
		return nodeSeq;
	}

	public void setNodeSeq(int nodeSeq) {
		this.nodeSeq = nodeSeq;
	}

}