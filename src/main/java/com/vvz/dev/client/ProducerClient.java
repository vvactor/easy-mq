package com.vvz.dev.client;

import java.util.Scanner;

public class ProducerClient {
    public static void main(String[] args) throws Exception{
        MqClient client = new MqClient();
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            String msg = scanner.nextLine();
            System.out.println("producer投递消息：" + msg);
            client.produce(msg);
        }
    }
}
