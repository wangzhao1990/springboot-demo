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
 * NioSocketServer
 *
 * @author wangz
 * @create 2019/4/8
 */
public class NioSocketServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(8080));
        // 设置非阻塞模式
        ssc.configureBlocking(false);

        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        Handler handler = new Handler(1024);
        while (true) {
            if (selector.select(3000) == 0) {
                System.out.println("等待超时-----------");
                continue;
            }
            System.out.println("处理请求-----------");
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                try {
                    if (selectionKey.isAcceptable()) {
                        handler.handleAccept(selectionKey);
                    }
                    if (selectionKey.isReadable()) {
                        handler.handleRead(selectionKey);
                    }
                } catch (IOException e) {
                    iterator.remove();
                    continue;
                }
                iterator.remove();
            }
        }
    }

    private static class Handler {
        private int bufferSize = 1024;
        private String localCharset = "UTF-8";

        public Handler(){};
        public Handler(int bufferSize) {
            this.bufferSize = bufferSize;
        }
        public Handler(String localCharset) {
            this(-1,localCharset);
        }
        public Handler(int bufferSize,String localCharset) {
            if (bufferSize > 0) {
                this.bufferSize = bufferSize;
            }
            if (localCharset != null) {
                this.localCharset = localCharset;
            }
        }

        public void handleAccept(SelectionKey key) throws IOException {
            SocketChannel accept = ((ServerSocketChannel) key.channel()).accept();
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
                System.out.println("received form client :" + receivedString);

                String sendString = "received data:" + receivedString;
                ByteBuffer wrap = ByteBuffer.wrap(sendString.getBytes(localCharset));
                channel.write(wrap);
                channel.close();
            }

        }

    }


}
