package com.vvz.dev.client;

public class CosumerClient {
    public static void main(String[] args) throws Exception{
        MqClient client = new MqClient();
        while(true){
            String msg = client.consume();
            System.out.println("收到消息：" + msg);
        }
    }
}
