package com.vvz.dev.broker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BrokerServer implements Runnable{
    //消息中心服务所占用端口号
    public static int SERVICE_PORT = 1024;

    private Socket socket;

    public BrokerServer(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            while(true){
                String str = in.readLine();
                if(str != null){
                    System.out.println("接收到原始数据：" + str);
                    if("CONSUME".equals(str)){
                        String msg = Broker.consume();
                        out.println(msg);
                        out.flush();
                    }else{
                        Broker.produce(str);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        ServerSocket server = new ServerSocket(SERVICE_PORT);
        while(true){
            BrokerServer brokerServer = new BrokerServer(server.accept());
            new Thread(brokerServer).start();
        }
    }
}
