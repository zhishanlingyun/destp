package com.destp.analysis.util

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



}
