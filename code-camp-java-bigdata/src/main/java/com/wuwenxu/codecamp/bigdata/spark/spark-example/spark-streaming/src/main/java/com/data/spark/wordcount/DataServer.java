package com.data.spark.wordcount;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端，监听9082端口，优先启动
 * 
 * @author onlyone
 */
public class DataServer {

    private static ServerSocket soc;

    public static void main(String[] args) throws IOException {

        soc = new ServerSocket(9082);

        Socket client = soc.accept();
        System.out.println("建立连接");
        OutputStream outputStream = client.getOutputStream();

        while (true) {

            PrintWriter pw = new PrintWriter(outputStream);

            // 控制台输入数据
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine();
            System.out.println("输入值：" + input);

            pw.println(input);
            pw.flush();

        }

    }

}
