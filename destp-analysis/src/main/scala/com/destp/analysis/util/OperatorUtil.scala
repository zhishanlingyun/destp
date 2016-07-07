package com.destp.analysis.util

import java.sql.{DriverManager, Connection}

import com.destp.analysis.dto.OrderItem

/**
 * Created by Administrator on 2016/6/13 0013.
 */
object OperatorUtil {

  def createOrderItem(line:String):OrderItem={
    val data = line.split("\t")
    OrderItem(data(0),data(1),data(2),data(3).toLong,data(4).toLong,data(5),data(6).toInt,
      data(7).toInt,BigDecimal(data(8)),data(9).toLong,data(10).toLong,data(11).toLong,data(12).toLong)
  }

  val connectHive = {
    Class.forName("org.apache.hive.jdbc.HiveDriver")
    val conn = DriverManager.getConnection("jdbc:hive2://192.168.188.128:10000/default","","")
    conn
  }

  def exeSql(fun:(Connection,String)=>Unit)={
    fun(connectHive,"select * from orderday where dt='2016-06-01' limit 10")
  }







}
