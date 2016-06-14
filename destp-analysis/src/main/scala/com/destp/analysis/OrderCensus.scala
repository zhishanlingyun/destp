package com.destp.analysis

import com.destp.analysis.util.OperatorUtil
import org.apache.spark.{SparkContext, SparkConf}

/**
 * Created by Administrator on 2016/6/11 0011.
 */
object OrderCensus {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("OrderCensus").setMaster("local[4]")
    val sc = new SparkContext(conf)
    val data = sc.textFile("E:\\data\\od.txt")
    val userRdd = data.map(OperatorUtil.createOrderItem(_)).map(item=>(item.UserId,1))
    userRdd.reduceByKey(_+_).sortBy(_._2,false).collect().foreach(println)


  }

}
