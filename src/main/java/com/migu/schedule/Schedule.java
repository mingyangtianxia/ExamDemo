package com.migu.schedule;


import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.TaskInfo;
import com.migu.schedule.util.Consumer;
import com.migu.schedule.util.Node;
import com.migu.schedule.util.NodeServer;
import com.migu.schedule.util.Observer;
import com.migu.schedule.util.Producer;
import com.migu.schedule.util.Storage;
import com.migu.schedule.util.TaskP;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
*类名和方法不能修改
 */
public class Schedule {

	private  Storage storage;
	private  NodeServer server;
	private  Producer producer;
	private  Consumer consumer;
    public int init() {
        // TODO 方法未实现
    	storage= new Storage();
    	server = new NodeServer();
    	producer=new Producer(storage);
    	consumer=new Consumer(storage);
    	server.removeAllObserver();
        return ReturnCodeKeys.E001;
    }


    public int registerNode(int nodeId) {
    	if(nodeId > 0){
    		Observer node = new Node(nodeId);
        	boolean flag=true;
        	for (Observer observer : server.getList()) {
        		if(observer.getNodeSeq()==nodeId){
        			flag=false;
        			break;
        		}
    		}
        	if(flag){
        		server.registerObserver(node);
                server.setInfomation("registerNode");
                return ReturnCodeKeys.E003;
        	}else{
        		return ReturnCodeKeys.E005;
        	}
    	}else {
			return ReturnCodeKeys.E004;
		}
    }

    public int unregisterNode(int nodeId) {
    	if (nodeId > 0) {
    		boolean flag=false;
        	for (Observer observer : server.getList()) {
        		if(observer.getNodeSeq()==nodeId){
        			flag=true;
        			break;
        		}
    		}
        	if(flag){
        		Observer node = new Node(nodeId);
    	    	server.removeObserver(node);
    	        server.setInfomation("unregisterNode");
    	        return ReturnCodeKeys.E006;
        	}else{
        		return ReturnCodeKeys.E007;
        	}
    	} else {
			return ReturnCodeKeys.E004;
		}
    }


    public int addTask(int taskId, int consumption) {
    	if (taskId > 0 && consumption > 0) {
    		List<TaskP> storageList=storage.getList();
    		boolean contansFlag=true;
    		for (TaskP taskP : storageList) {
				if(taskP.getProducer()==taskId){
					contansFlag=false;
					break;
				}
			}
    		if(contansFlag){
    			producer.produce(taskId,consumption);
        	    return ReturnCodeKeys.E008;
    		}else{
    			return ReturnCodeKeys.E010;
    		}
    	} else {
			return ReturnCodeKeys.E009;
		}
    }


    public int deleteTask(int taskId) {
    	if (taskId > 0) {
	    	List<TaskP> storageList=storage.getList();
	    	for (TaskP taskP : storageList) {
	    		if(taskP.getProducer()==taskId){
	    			storageList.remove(taskP);
	    			return ReturnCodeKeys.E011;
	    		}
			}
	        return ReturnCodeKeys.E012;
    	} else {
			return ReturnCodeKeys.E009;
		}
    }


    public int scheduleTask(int threshold) {
    	if(threshold>0){
    		List<TaskP> storageList=storage.getList();
        	Collections.sort(storageList);  
        	int storageListCount=storageList.size();
        	List<Observer> nodeList=server.getList();
        	int nodeListCount=nodeList.size();
        	server.notifyObserver();
        	boolean equalFlag=false;
        	for (int i=0;i < storageListCount-1;i++) {  	
        		if(storageList.get(i).getConsumption()!=storageList.get(i+1).getConsumption()){
        			equalFlag=true;
            		break;
        		}
        	}
        	if(equalFlag){
        		for (int i=0;i < storageListCount;) { 
            		for (Observer observer : nodeList) {
            			observer.addConsumerList(storageList.get(i));
            			i++;
        			}
        		}
        	}else{
        		for (int z = 0;z<nodeListCount;z++) {
        			for (int i=0;i < storageListCount/nodeListCount;i++) { 
        				nodeList.get(z).addConsumerList(storageList.get(i+z*(storageListCount/nodeListCount)));
        			}
        		}
        	}
        	
        	for (Observer observer : nodeList) {
    			observer.update(consumer);
    		}
            
        	//consumer.consumeAll(server,threshold);
        	
            return ReturnCodeKeys.E013;
    	}else {
			return ReturnCodeKeys.E002;
		}
    	
    }


    public int queryTaskStatus(List<TaskInfo> tasks) {
    	if (null != tasks) {
    		tasks.clear();
    		List<Observer> nodeList=server.getList();
        	for (Observer observer : nodeList) {
        		List<TaskP> ConsumerList = observer.getConsumerList();
        		for (TaskP item : ConsumerList) {
    				TaskInfo task = new TaskInfo();
    				task.setNodeId(observer.getNodeSeq());
    				task.setTaskId(item.getProducer());
    				tasks.add(task);
    			}
    		}
    		return ReturnCodeKeys.E015;
    	}else {
			return ReturnCodeKeys.E016;
		}
        
    }

}
