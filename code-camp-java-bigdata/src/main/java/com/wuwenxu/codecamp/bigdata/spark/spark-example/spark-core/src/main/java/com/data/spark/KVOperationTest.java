package com.data.spark;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.VoidFunction;

import scala.Tuple2;

/**
 * 键值对聚合操作reduceByKey，foldByKey，排序操作sortByKey
 * 
 * @author onlyone
 */
public class KVOperationTest {

    private static String master = "local[*]";

    public static void main(String[] args) {
        JavaPairRDD<Integer, Integer> rdd1 = initData();

        // reduceByKey，按key进行reduce操作
        // reduceByKey(rdd1);

        // sortByKey，按key进行排序
        // sortByKey(rdd1);

        // foldByKey，根据K将V做折叠、合并处理
        foldByKey(rdd1);

    }

    // reduceByKey，按key进行reduce操作
    public static void reduceByKey(JavaPairRDD<Integer, Integer> rdd1) {
        JavaPairRDD<Integer, Integer> rdd2 = rdd1.reduceByKey(new Function2<Integer, Integer, Integer>() {

            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });

        rdd2.foreach(new VoidFunction<Tuple2<Integer, Integer>>() {

            @Override
            public void call(Tuple2<Integer, Integer> t) throws Exception {
                System.out.println(t);

            }
        });

    }

    // foldByKey，根据K将V做折叠、合并处理
    public static void foldByKey(JavaPairRDD<Integer, Integer> rdd1) {
        JavaPairRDD<Integer, Integer> rdd2 = rdd1.foldByKey(1, new Function2<Integer, Integer, Integer>() {

            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }

        });

        List<Tuple2<Integer, Integer>> result = rdd2.collect();
        for (Tuple2<Integer, Integer> t1 : result) {
            System.out.println(t1);
        }
    }

    // sortByKey，按key进行排序
    public static void sortByKey(JavaPairRDD<Integer, Integer> rdd1) {
        // true 升序；false 降序
        JavaPairRDD<Integer, Integer> rdd2 = rdd1.sortByKey(false);

        List<Tuple2<Integer, Integer>> result = rdd2.collect();
        for (Tuple2<Integer, Integer> t : result) {
            System.out.println(t);
        }

    }

    public static JavaPairRDD<Integer, Integer> initData() {
        List<Tuple2<Integer, Integer>> list = new ArrayList<Tuple2<Integer, Integer>>();
        list.add(new Tuple2<Integer, Integer>(3, 5));
        list.add(new Tuple2<Integer, Integer>(3, 6));
        list.add(new Tuple2<Integer, Integer>(1, 2));
        list.add(new Tuple2<Integer, Integer>(4, 8));
        list.add(new Tuple2<Integer, Integer>(1, 5));

        SparkConf conf = new SparkConf();
        conf.setAppName(StudentAvgScore.class.getName()).setMaster(master);
        JavaSparkContext javaSparkContext = new JavaSparkContext(conf);
        // 设置日志等级
        javaSparkContext.setLogLevel("ERROR");

        // 加载源数据，并指定三个分片
        JavaPairRDD<Integer, Integer> pairRDD = javaSparkContext.parallelizePairs(list, 3);

        return pairRDD;
    }
}
