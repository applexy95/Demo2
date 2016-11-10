package test

import dao.DBConnection
import service.ProjectService

/**
  * Created by ReggieYang on 2016/11/6.
  */
object Test {

  def main(args: Array[String]) = {
    val conn = DBConnection.getConnection

    val ps = new ProjectService(conn)
    ps.addProject(Array("", "goc2", "ggg"))

    conn.close()
  }

}
