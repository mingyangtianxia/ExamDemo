package com.migu.schedule.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者，也就是微信公众号服务
 * 实现了Observerable接口，对Observerable接口的三个方法进行了具体实现
 * @author zhaojian
 *
 */
public class NodeServer implements Observerable {
    
    private List<Observer> list;
    private String message;
    private int consumptionCount=0;
    
    public NodeServer() {
        list = new ArrayList<Observer>();
    }
    
    public void registerObserver(Observer o) {
        list.add(o);
    }
    
    public void removeObserver(Observer o) {
        if(!list.isEmpty())
            list.remove(o);
    }

    public void removeAllObserver() {
            list.clear();
    }
    //遍历
    public void notifyObserver() {
        for(int i = 0; i < list.size(); i++) {
            Observer oserver = list.get(i);
            oserver.update(message);
        }
    }
    
    public void setInfomation(String s) {
        this.message = s;
        System.out.println("服务更新消息： " + s);
        //消息更新，通知所有观察者
        notifyObserver();
    }

	public int getConsumptionCount() {
		return consumptionCount;
	}

	public void setConsumptionCount(int consumptionCount) {
		this.consumptionCount = consumptionCount;
	}

	public List<Observer> getList() {
		return list;
	}

	public void setList(List<Observer> list) {
		this.list = list;
	}

}
