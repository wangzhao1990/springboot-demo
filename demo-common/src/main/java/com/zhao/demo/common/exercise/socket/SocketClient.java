package com.zhao.demo.common.exercise.socket;

import java.io.*;
import java.net.Socket;

/**
 * socket客户端
 *
 * @author wangz
 * @create 2019/4/8
 */
public class SocketClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",8080);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter.println("hello");
            printWriter.flush();
            String s = bufferedReader.readLine();
            System.out.println("server return:" + s);

            bufferedReader.close();
            printWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
