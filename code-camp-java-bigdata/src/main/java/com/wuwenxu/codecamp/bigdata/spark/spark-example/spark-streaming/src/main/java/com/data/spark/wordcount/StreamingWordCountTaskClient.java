package com.data.spark.wordcount;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

import scala.Tuple2;

/**
 * 定期向目标服务器发出请求，并对数据流实时处理
 * 
 * @author onlyone
 */
public class StreamingWordCountTaskClient {

    public static void main(String[] args) throws InterruptedException {

        SparkConf conf = new SparkConf();
        conf.setAppName("StreamingWordCountTaskClient");
        // conf.set("spark.rpc.askTimeout", "600s");
        // conf.set("spark.executor.heartbeatInterval", "600s");
        // conf.setMaster(master);
        // .setMaster("spark://onlyonedeMacBook-Pro.local:7077");
        JavaStreamingContext ssc = new JavaStreamingContext(conf, Durations.seconds(6));
        ssc.sparkContext().setLogLevel("ERROR");

        // 接收TCP套接字作为文本流
        // 定期向特定机器的9087请求数据，一旦收到数据就存入内存
        // 如果内存不足就写入硬盘
        JavaReceiverInputDStream<String> lines = ssc.socketTextStream("localhost", 9082,
                                                                      StorageLevel.MEMORY_AND_DISK_2());

        // 按空格，对数据拆分
        JavaDStream<String> words = lines.flatMap(new FlatMapFunction<String, String>() {

            @Override
            public Iterator<String> call(String arg0) throws Exception {
                return Arrays.asList(arg0.split(" ")).iterator();
            }
        });

        // 数据转换
        JavaPairDStream<String, Integer> paris = words.mapToPair(new PairFunction<String, String, Integer>() {

            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<String, Integer>(s, 1);
            }
        });

        JavaPairDStream<String, Integer> wordCounts = paris.reduceByKey(new Function2<Integer, Integer, Integer>() {

            @Override
            public Integer call(Integer arg0, Integer arg1) throws Exception {
                return arg0 + arg1;
            }
        });

        // 打印前10个单词
        System.out.println("开始打印结果---begin");
        // wordCounts.print(10);
        wordCounts.foreachRDD(new VoidFunction<JavaPairRDD<String, Integer>>() {

            @Override
            public void call(JavaPairRDD<String, Integer> rdd2) throws Exception {
                List<Tuple2<String, Integer>> result = rdd2.collect();
                for (Tuple2<String, Integer> t1 : result) {
                    System.out.println(t1);
                }
            }

        });
        System.out.println("开始打印结果---end");

        // 初始化Streaming上下文
        ssc.start();

        // 等待执行结束
        ssc.awaitTermination();

    }
}
