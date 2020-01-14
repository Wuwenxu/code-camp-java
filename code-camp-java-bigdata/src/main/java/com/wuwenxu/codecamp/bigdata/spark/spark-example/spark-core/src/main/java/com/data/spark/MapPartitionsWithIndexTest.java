package com.data.spark;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.VoidFunction;

import scala.Tuple2;

/**
 * <pre>
 * 源数据分区
 * 
 * 参考例子：http://blog.csdn.net/t1dmzks/article/details/71374418
 * 
 * @author onlyone
 * 
 * </pre>
 */
public class MapPartitionsWithIndexTest {

    private static String master = "local[*]";

    // java对象的hashCode来决定是哪个分区，对于pairRDD, 分区就是key.hashCode() % numPartitions, 3%3=0，所以 (3,6) 这个元素在0 分区， 4%3=1，所以元素(4,8)
    // 在1 分区
    public static void main(String[] args) {
        // 源数据
        List<Tuple2<Integer, Integer>> list = new ArrayList<Tuple2<Integer, Integer>>();
        list.add(new Tuple2<Integer, Integer>(3, 5));
        list.add(new Tuple2<Integer, Integer>(3, 6));
        list.add(new Tuple2<Integer, Integer>(1, 2));
        list.add(new Tuple2<Integer, Integer>(4, 8));
        list.add(new Tuple2<Integer, Integer>(4, 7));
        list.add(new Tuple2<Integer, Integer>(1, 1));
        list.add(new Tuple2<Integer, Integer>(5, 10));
        list.add(new Tuple2<Integer, Integer>(2, 4));
        list.add(new Tuple2<Integer, Integer>(2, 3));
        list.add(new Tuple2<Integer, Integer>(5, 9));

        SparkConf conf = new SparkConf();
        conf.setAppName(StudentAvgScore.class.getName()).setMaster(master);
        JavaSparkContext javaSparkContext = new JavaSparkContext(conf);
        // 设置日志等级
        javaSparkContext.setLogLevel("ERROR");

        // 加载源数据，并指定三个分片
        JavaPairRDD<Integer, Integer> pairRDD = javaSparkContext.parallelizePairs(list, 3);

        printPartRDD(pairRDD);
    }

    private static void printPartRDD(JavaPairRDD<Integer, Integer> pairRDD) {
        JavaRDD<Tuple2<Integer, Tuple2<Integer, Integer>>> mapPartitionIndexRDD = pairRDD.mapPartitionsWithIndex(new Function2<Integer, Iterator<Tuple2<Integer, Integer>>, Iterator<Tuple2<Integer, Tuple2<Integer, Integer>>>>() {

                                                                                                                     @Override
                                                                                                                     public Iterator<Tuple2<Integer, Tuple2<Integer, Integer>>> call(Integer partIndex,
                                                                                                                                                                                     Iterator<Tuple2<Integer, Integer>> tuple2Iterator) {
                                                                                                                         ArrayList<Tuple2<Integer, Tuple2<Integer, Integer>>> tuple2s = new ArrayList<>();

                                                                                                                         while (tuple2Iterator.hasNext()) {
                                                                                                                             Tuple2<Integer, Integer> next = tuple2Iterator.next();
                                                                                                                             tuple2s.add(new Tuple2<Integer, Tuple2<Integer, Integer>>(
                                                                                                                                                                                       partIndex,
                                                                                                                                                                                       next));
                                                                                                                         }
                                                                                                                         return tuple2s.iterator();
                                                                                                                     }
                                                                                                                 },
                                                                                                                 false);

        mapPartitionIndexRDD.foreach(new VoidFunction<Tuple2<Integer, Tuple2<Integer, Integer>>>() {

            @Override
            public void call(Tuple2<Integer, Tuple2<Integer, Integer>> integerTuple2Tuple2) throws Exception {
                System.out.println(integerTuple2Tuple2);
            }
        });
    }

}
