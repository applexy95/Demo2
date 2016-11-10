package dao

import java.sql.Connection

import model.Project

/**
  * Created by ReggieYang on 2016/11/6.
  */
class ProjectDao(conn: Connection) {
  //  val projectTable = "project"
  //  val projectColumns = Array("project_id", "project_name", "creator_name")
  //  val projectTypes = Array("int", "string", "string")

  val projectTable = ModelFactory.getTableName(classOf[Project])
  val projectColumns = ModelFactory.getFieldNames(classOf[Project])
  val projectTypes = ModelFactory.getFieldTypes(classOf[Project])
  val pk = projectColumns.take(1)
  val pkTypes = projectTypes.take(1)

  val pdTable = "project_developer"
  val pdColumns = Array("project_id", "developer_name")
  val pdTypes = Array("int", "string")

  def getDeveloper(projectId: String): Array[String] = {
    val dev = DaoFactory.select(conn, pdTable, pdColumns.drop(1), pdColumns.take(1), Array(projectId), pdTypes.take(1))
    if (dev.isEmpty) Array() else dev.map(x => x(0))
  }

  def getProject(userName: String): Array[Project] = {
    //    DaoFactory.select(conn, projectTable, projectColumns, projectColumns.takeRight(1), Array(userName), projectTypes.takeRight(1)).map(x => ModelFactory.createProject(x))
    val pro = DaoFactory.select(conn, pdTable, pdColumns.take(1), pdColumns.drop(1), Array(userName), pdTypes.drop(1))
    if (pro.isEmpty) Array()
    else {
      pro.map(x => x(0)).map(p => {
        DaoFactory.select(conn, projectTable, projectColumns, projectColumns.take(1), Array(p), projectTypes.take(1)).map(x => ModelFactory.createProject(x))
      }).map(x => x(0))
    }
  }


}
