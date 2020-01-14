package com.data.spark;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.util.LongAccumulator;
import org.spark_project.guava.collect.Lists;

import scala.Tuple2;

/**
 * @author onlyone
 */
public class BasicClusterTask {

    /*
     * This is the address of the Spark cluster. We will call the task from WordCountTest and we use a local standalone
     * cluster. [*] means use all the cores available. See {@see
     * http://spark.apache.org/docs/latest/submitting-applications.html#master-urls}.
     */
    private static String master = "local[*]";

    /**
     * 统计单词计数
     */
    public static void splitWordCount(String inputFile) {

        SparkConf conf = new SparkConf();
        conf.setAppName(BasicClusterTask.class.getName()).setMaster(master);

        JavaSparkContext javaSparkContext = new JavaSparkContext(conf);
        javaSparkContext.textFile(inputFile)
        .flatMap(text -> Arrays.asList(text.split(" ")).iterator())
        .mapToPair(word -> new Tuple2<>( word.replace(".","").replace(",",""),1))
        .reduceByKey((a,b) -> a+ b)
        .foreach(result -> System.out.println(String.format("[%s] 出现 [%d] 次",result._1(),result._2)));
        
    }

    /**
     * 统计全文单词总数
     */
    public static void countSize(String inputFile) {

        SparkConf conf = new SparkConf();
        conf.setAppName(BasicClusterTask.class.getName()).setMaster(master);
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> lines = sc.textFile(inputFile);
        JavaRDD<Integer> lineLengths = lines.map(s -> s.length());
        System.out.println("lineLengths---begin-------");
        lineLengths.foreach(t -> System.out.println(t.toString()));
        System.out.println("lineLengths---end-------");
        int totalLength = lineLengths.reduce(new Function2<Integer, Integer, Integer>() {

            public Integer call(Integer a, Integer b) {
                return a + b;
            }
        });
        System.out.println("单词总数----" + totalLength);
    }
    
    /**
     * 求和
     */
    public static void sum() {

        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);

        SparkConf conf = new SparkConf();
        conf.setAppName(BasicClusterTask.class.getName()).setMaster(master);

        JavaSparkContext javaSparkContext = new JavaSparkContext(conf);
        LongAccumulator accum = javaSparkContext.sc().longAccumulator();

        //将List集合数据转换为RDD，parallelize可以指定分区数
        JavaRDD<Integer> javaRDD=  javaSparkContext.parallelize(list,8);
        
        //分区数，默认为这个程序所分配到的资源的CPU核的个数
        int partionSize=javaRDD.partitions().size();
        System.out.println("分区数="+partionSize);
        
        javaRDD.foreach(t -> accum.add(t));
        System.out.println("总和= " + accum.sum());
    }
    

}
