package com.zhao.demo.common.exercise.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket server
 *
 * @author wangz
 * @create 2019/4/8
 */
public class SocketServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            Socket accept = serverSocket.accept();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
            String s = bufferedReader.readLine();
            System.out.println("accept info:" + s);
            PrintWriter printWriter = new PrintWriter(accept.getOutputStream());
            printWriter.println("received data:" + s);
            printWriter.close();
            bufferedReader.close();
            accept.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
