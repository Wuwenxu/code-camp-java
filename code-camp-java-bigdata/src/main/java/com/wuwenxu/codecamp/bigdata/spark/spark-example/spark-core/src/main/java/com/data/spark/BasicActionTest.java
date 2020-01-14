package com.data.spark;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

/**
 * 基本的Action操作 first, take, collect, count, countByValue, reduce, aggregate, fold,top
 * 
 * @author onlyone
 */

public class BasicActionTest {

    private static String master = "local[*]";

    public static void main(String[] args) {
        JavaRDD<Integer> rdd = initData();

        // 返回第一个元素
        System.out.println("first：" + rdd.first());
        // 返回前2个元素
        System.out.println("task:" + rdd.take(2));
        // 返回所有的元素
        System.out.println("collect：" + rdd.collect());
        // 返回元素个数
        System.out.println("count：" + rdd.count());
        // 各元素在 RDD 中出现的次数 返回{(key1,次数),(key2,次数),…(keyn,次数)}
        System.out.println("countByValue：" + rdd.countByValue());
        // 按照降序或者指定的排序规则，返回前2个元素
        System.out.println("top：" + rdd.top(2));
        // 对RDD元素进行升序排序,取出前n个元素并返回
        System.out.println("takeOrdered：" + rdd.takeOrdered(2));
    }

    public static JavaRDD<Integer> initData() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        SparkConf conf = new SparkConf();
        conf.setAppName(StudentAvgScore.class.getName()).setMaster(master);
        JavaSparkContext javaSparkContext = new JavaSparkContext(conf);
        // 设置日志等级
        javaSparkContext.setLogLevel("ERROR");

        // 加载源数据，并指定三个分片
        JavaRDD<Integer> rdd = javaSparkContext.parallelize(list);

        return rdd;
    }

}
