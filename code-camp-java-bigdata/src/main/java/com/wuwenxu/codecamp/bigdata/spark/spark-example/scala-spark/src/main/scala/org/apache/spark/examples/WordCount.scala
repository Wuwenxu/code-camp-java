
package org.apache.spark.examples

import org.apache.spark.{SparkConf, SparkContext}


object WordCount {

  def main(args: Array[String]): Unit = {

    val scores = List(
      ScoreDetail("xiaoming", "Math", 98),
      ScoreDetail("xiaoming", "English", 88),
      ScoreDetail("wangwu", "Math", 75),
      ScoreDetail("wangwu", "English", 78),
      ScoreDetail("lihua", "Math", 90),
      ScoreDetail("lihua", "English", 80),
      ScoreDetail("zhangsan", "Math", 91),
      ScoreDetail("zhangsan", "English", 80))
      ScoreDetail("add", "ma", 22)


    val scoresWithKey = for {i <- scores} yield (i.studentName, i)

    val conf = new SparkConf().setAppName("aaa").setMaster("local")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    val scoresWithKeyRDD = sc.parallelize(scoresWithKey)

    val avgScoresRDD = scoresWithKeyRDD.combineByKey(
      x => (x.score, 1) /*createCombiner*/ ,
      (acc: (Float, Int), x: ScoreDetail) => (acc._1 + x.score, acc._2 + 1) /*mergeValue*/ ,
      (acc1: (Float, Int), acc2: (Float, Int)) => (acc1._1 + acc2._1, acc1._2 + acc2._2) /*mergeCombiners*/
      // calculate the average
    ).map({ case (key, value) => (key, value._1 / value._2) })

    avgScoresRDD.collect.foreach(println)


  }

  case class ScoreDetail(studentName: String, subject: String, score: Float)

}
