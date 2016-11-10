package dao

import java.sql.{Connection, DriverManager}

import com.mchange.v2.c3p0.ComboPooledDataSource

/**
  * Created by ReggieYang on 2016/10/22.
  */
object DBConnection {

  val dataSource: ComboPooledDataSource = new ComboPooledDataSource()

  def getConnection = dataSource.getConnection

//  var conn: Connection = _
//
//  def getConnection: Connection = {
//    //调用Class.forName()方法加载驱动程序
//    Class.forName("com.mysql.jdbc.Driver")
//    val url = "jdbc:mysql://192.168.47.29:3306/risk_management"
//    conn = DriverManager.getConnection(url, "docker", "root")
//    conn
//  }
//
//  def closeConnection() = {
//    conn.close()
//  }



}
