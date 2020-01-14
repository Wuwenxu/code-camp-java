package com.data.spark;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

/**
 * <pre>
 * 计算学生的平均成绩
 * 
 * 参考例子：http://blog.csdn.net/T1DMzks/article/category/7062215
 * 
 * @author onlyone
 * </pre>
 */
public class StudentAvgScore {

    private static String master = "local[*]";

    public static void main(String[] args) {

        // 源数据
        ArrayList<ScoreDetail> scoreDetails = new ArrayList<>();
        scoreDetails.add(new ScoreDetail("xiaoming", "Math", 98));
        scoreDetails.add(new ScoreDetail("xiaoming", "English", 88));
        scoreDetails.add(new ScoreDetail("wangwu", "Math", 75));
        scoreDetails.add(new ScoreDetail("wangwu", "Englist", 78));
        scoreDetails.add(new ScoreDetail("lihua", "Math", 90));
        scoreDetails.add(new ScoreDetail("lihua", "English", 80));
        scoreDetails.add(new ScoreDetail("zhangsan", "Math", 91));
        scoreDetails.add(new ScoreDetail("zhangsan", "English", 80));

        SparkConf conf = new SparkConf();
        conf.setAppName(StudentAvgScore.class.getName()).setMaster(master);
        JavaSparkContext javaSparkContext = new JavaSparkContext(conf);
        javaSparkContext.setLogLevel("ERROR");

        // 加载源数据，并指定三个分区
        JavaRDD<ScoreDetail> scoreDetailsRDD = javaSparkContext.parallelize(scoreDetails, 3);

        // 数据格式转换，name---->ScoreDetail
        JavaPairRDD<String, ScoreDetail> pairRDD = scoreDetailsRDD.mapToPair(new PairFunction<ScoreDetail, String, ScoreDetail>() {

            @Override
            public Tuple2<String, ScoreDetail> call(ScoreDetail scoreDetail) throws Exception {

                return new Tuple2<>(scoreDetail.studentName, scoreDetail);
            }
        });

        // createCombiner: combineByKey() 会遍历分区中的所有元素，因此每个元素的键要么还没有遇到过，要么就
        // 和之前的某个元素的键相同。如果这是一个新的元素， combineByKey() 会使用一个叫作 createCombiner() 的函数来创建那个键对应的累加器的初始值
        // 返回 Tuple2<Float, Integer>
        Function<ScoreDetail, Tuple2<Float, Integer>> createCombine = new Function<ScoreDetail, Tuple2<Float, Integer>>() {

            @Override
            public Tuple2<Float, Integer> call(ScoreDetail scoreDetail) throws Exception {
                return new Tuple2<>(scoreDetail.score, 1);
            }
        };

        // mergeValue: 如果这是一个在处理当前分区之前已经遇到的键， 它会使用 mergeValue() 方法将该键的累加器对应的当前值与这个新的值进行合并
        // Function2传入两个值，返回 Tuple2<Float, Integer>
        Function2<Tuple2<Float, Integer>, ScoreDetail, Tuple2<Float, Integer>> mergeValue = new Function2<Tuple2<Float, Integer>, ScoreDetail, Tuple2<Float, Integer>>() {

            @Override
            public Tuple2<Float, Integer> call(Tuple2<Float, Integer> tp, ScoreDetail scoreDetail) throws Exception {
                return new Tuple2<>(tp._1 + scoreDetail.score, tp._2 + 1);
            }
        };

        // mergeCombiners: 由于每个分区都是独立处理的， 因此对于同一个键可以有多个累加器。如果有两个或者更
        // 多的分区都有对应同一个键的累加器，就需要使用用户提供的 mergeCombiners() 方法将各个分区的结果进行合并。
        // 将两两分区的 Tuple2<Float, Integer>合并，并返回新的 Tuple2<Float, Integer>
        Function2<Tuple2<Float, Integer>, Tuple2<Float, Integer>, Tuple2<Float, Integer>> mergeCombiners = new Function2<Tuple2<Float, Integer>, Tuple2<Float, Integer>, Tuple2<Float, Integer>>() {

            @Override
            public Tuple2<Float, Integer> call(Tuple2<Float, Integer> tp1, Tuple2<Float, Integer> tp2) throws Exception {
                return new Tuple2<>(tp1._1 + tp2._1, tp1._2 + tp2._2);
            }
        };

        // 按key（name）开始合并
        // 并转换为新的数据格式转换，name---->Tuple2<Float, Integer>
        JavaPairRDD<String, Tuple2<Float, Integer>> combineByRDD = pairRDD.combineByKey(createCombine, mergeValue,
                                                                                        mergeCombiners);

        // 打印结果
        Map<String, Tuple2<Float, Integer>> stringTuple2Map = combineByRDD.collectAsMap();
        for (String name : stringTuple2Map.keySet()) {
            System.out.println(name + "的平均分：" + stringTuple2Map.get(name)._1 / stringTuple2Map.get(name)._2);
        }

    }

    static class ScoreDetail implements Serializable {

        private static final long serialVersionUID = -4011189054215921430L;
        // case class ScoreDetail(studentName: String, subject: String, score: Float)
        public String             studentName;
        public String             subject;
        public Float              score;

        public ScoreDetail(String studentName, String subject, float score){
            this.studentName = studentName;
            this.subject = subject;
            this.score = score;
        }
    }

}
