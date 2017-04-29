import java.sql.Connection

import com.destp.analysis.util.OperatorUtil

/**
 * Created by Administrator on 2016/6/24 0024.
 */
object DBTest {

  def fun(conn:Connection,sql:String)={
    val stat = conn.createStatement()
    stat.execute(sql)
    val result = stat.getResultSet
    while(result.next){
      println(result.getString(1))
    }
    conn.close
  }

  def main(args: Array[String]) {
    fun(OperatorUtil.connectHive,"select * from orderday w limit 10")
    //OperatorUtil.exeSql(fun)
  }

}
