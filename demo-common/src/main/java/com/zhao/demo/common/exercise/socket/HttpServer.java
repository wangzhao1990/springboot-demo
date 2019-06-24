package com.zhao.demo.common.exercise.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * HttpServer
 *
 * @author wangz
 * @create 2019/4/8
 */
public class HttpServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(8080));
        // 设置非阻塞模式
        ssc.configureBlocking(false);

        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            if (selector.select(3000) == 0) {
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                new Thread(new HttpHandler(selectionKey)).run();
                iterator.remove();
            }
        }
    }

    private static class HttpHandler implements Runnable {
        private int bufferSize = 1024;
        private String localCharset = "UTF-8";
        private SelectionKey key;

        public HttpHandler(){};
        public HttpHandler(SelectionKey key){
            this.key = key;
        };
        public HttpHandler(int bufferSize) {
            this.bufferSize = bufferSize;
        }
        public HttpHandler(String localCharset) {
            this(-1,localCharset);
        }
        public HttpHandler(int bufferSize, String localCharset) {
            if (bufferSize > 0) {
                this.bufferSize = bufferSize;
            }
            if (localCharset != null) {
                this.localCharset = localCharset;
            }
        }

        public void handleAccept(SelectionKey key) throws IOException {
            // accept前
            System.out.println("accept start");
            SocketChannel accept = ((ServerSocketChannel) key.channel()).accept();
            // accept后
            System.out.println("accept end");
            accept.configureBlocking(false);
            accept.register(key.selector(),SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
        }

        public void handleRead(SelectionKey key) throws IOException {
            SocketChannel channel = (SocketChannel) key.channel();
            ByteBuffer buffer = (ByteBuffer)key.attachment();
            buffer.clear();
            if (channel.read(buffer) == -1) {
                channel.close();
            } else {
                buffer.flip();
                String receivedString = Charset.forName(localCharset).newDecoder().decode(buffer).toString();
                // 控制台打印请求报文头
                String[] requestMessage = receivedString.split("\r\n");
                for (String s: requestMessage) {
                    System.out.println(s);
                    if(s.isEmpty()) {
                        break;
                    }
                }
                // 控制台打印首行信息
                String[] firstLine = requestMessage[0].split(" ");
                System.out.println();
                System.out.println("Method:" + firstLine[0]);
                System.out.println("url:" + firstLine[1]);
                System.out.println("HTTP Version:" + firstLine[2]);
                System.out.println();

                // 返回客户端
                StringBuilder sendString = new StringBuilder();
                sendString.append("HTTP/1.1 200 OK\r\n");
                sendString.append("Content-Type:text/html;charset=" + localCharset + "\r\n");
                sendString.append("\r\n");

                sendString.append("<html><head><title>显示报文</title></head><body>");
                sendString.append("接收到的请求报文是：<br/>");
                for (String s : requestMessage) {
                    sendString.append(s + "<br/>");
                }
                sendString.append("</body></html>");
                System.out.println(sendString);
                ByteBuffer wrap = ByteBuffer.wrap(sendString.toString().getBytes(localCharset));
                channel.write(wrap);
                channel.close();
            }

        }

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            try {
                // 接收到连接请求
                if (key.isAcceptable()) {
                    handleAccept(key);
                }
                // 读数据
                if (key.isReadable()) {
                    handleRead(key);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
