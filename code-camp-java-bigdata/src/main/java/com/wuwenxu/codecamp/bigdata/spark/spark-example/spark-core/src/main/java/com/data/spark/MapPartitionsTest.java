package com.data.spark;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.VoidFunction;

import scala.Tuple2;

public class MapPartitionsTest {

    private static String master = "local[*]";

    /**
     * <pre>
     * mapPartition可以倒过来理解，先partition，再把每个partition进行map函数
     * 如果在映射的过程中需要频繁创建额外的对象，使用mapPartitions要比map高效的多。
     * 
     * 比如，将RDD中的所有数据通过JDBC连接写入数据库，如果使用map函数，可能要为每一个元素都创建一个connection，这样开销很大，
     * 如果使用mapPartitions，那么只需要针对每一个分区建立一个connection。
     * </pre>
     */
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);

        SparkConf conf = new SparkConf();
        conf.setAppName(StudentAvgScore.class.getName()).setMaster(master);
        JavaSparkContext javaSparkContext = new JavaSparkContext(conf);
        // 设置日志等级
        javaSparkContext.setLogLevel("ERROR");

        // 加载源数据，并指定三个分片
        JavaRDD<Integer> rdd = javaSparkContext.parallelize(list, 3);

        JavaRDD<Tuple2<Integer, Integer>> tuple2JavaRDD = rdd.mapPartitions(new FlatMapFunction<Iterator<Integer>, Tuple2<Integer, Integer>>() {

            @Override
            public Iterator<Tuple2<Integer, Integer>> call(Iterator<Integer> it) throws Exception {
                List<Tuple2<Integer, Integer>> tuple2s = new ArrayList<>();
                while (it.hasNext()) {
                    Integer next = it.next();
                    tuple2s.add(new Tuple2<Integer, Integer>(next, next * next));
                }
                return tuple2s.iterator();
            }
        });

        tuple2JavaRDD.foreach(new VoidFunction<Tuple2<Integer, Integer>>() {

            @Override
            public void call(Tuple2<Integer, Integer> tp2) throws Exception {
                System.out.println(tp2);
            }
        });
    }

}
