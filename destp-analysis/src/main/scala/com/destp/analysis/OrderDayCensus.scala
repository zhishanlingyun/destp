package com.destp.analysis

import com.destp.analysis.util.OperatorUtil
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkContext, SparkConf}

/**
 * Created by Administrator on 2016/6/24 0024.
 */
object OrderDayCensus {

  def main(args: Array[String]) {
    val path = "/opt/data/order_offline.log.2016-06-01"
    val path2 = "";
    val conf = new SparkConf().setAppName("OrderDayCensus").setMaster("local[1]")
    val sc = new SparkContext(conf)
    //val sqlContext = new SQLContext(sc)
    //import sqlContext.implicits._
    /*val data = sc.textFile(path).map(OperatorUtil.createOrderItem).toDF()
    data.registerTempTable("orderday")*/
    //sqlContext.sql("select SkuId,count(1) as num from orderday group by SkuId order by num desc").show(10)
    val sqlContext = new HiveContext(sc)
    //sqlContext.sql("load data inpath 'hdfs://ubuntu:9000/user/hive/warehouse/orderday'")
    sqlContext.setConf("","")
    sqlContext.sql("select * from orderday where dt='2016-06-01' limit 10").show()

  }

}
