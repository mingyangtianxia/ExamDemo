package com.migu.schedule.util;

public class TaskP implements Comparable<TaskP>{
    private int producer;
    private int consumption;
    public TaskP(int producer,int consumption )
    {
        this.producer = producer;
        this.consumption = consumption;
    }
	public int getProducer() {
		return producer;
	}
	public void setProducer(int producer) {
		this.producer = producer;
	}
	public int getConsumption() {
		return consumption;
	}
	public void setConsumption(int consumption) {
		this.consumption = consumption;
	}
	public int compareTo(TaskP taskp) {  
        int i = this.getProducer() - taskp.getProducer();
        return i;  
    }  
}
