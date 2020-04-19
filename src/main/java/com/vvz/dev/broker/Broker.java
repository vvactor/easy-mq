package com.vvz.dev.broker;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 消息处理中心类
 */
public class Broker {
    //队列存储消息的最大数量
    private final static int MAX_SIZE = 3;

    //保存消息数据的容器
    private static ArrayBlockingQueue<String> messageQueue = new ArrayBlockingQueue<>(MAX_SIZE);

    //生产者向消息中心投递消息
    public static void produce(String msg){
        if(messageQueue.offer(msg)){
            System.out.println("成功向消息中心投递消息: " + msg + "，当前消息中心容纳消息数量为: " + messageQueue.size());
        }else {
            System.out.println("消息处理中心所容纳消息已达上限，本次消息投递失败！");
        }
        System.out.println("================================================");
    }

    //消费者从消息中心消费消息
    public static String consume(){
        String msg = messageQueue.poll();
        if(null != msg){
            System.out.println("已经消费消息: " + msg + "，当前消息中心所容纳的消息数量为：" + messageQueue.size());
        }else{
            System.out.println("消息中心没有消息可供消费！");
        }
        System.out.println("=========================================");
        return msg;
    }
}
