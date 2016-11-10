package dao

import java.sql.Connection

import model.Risk

/**
  * Created by ReggieYang on 2016/11/6.
  */
class RiskDao(conn: Connection) {

//  val riskTable = "risk"
//  val riskColumns = Array("risk_id", "possibility", "impact", "threshold", "creator_name",
//    "follower_name", "description", "risk_type", "project_id")
//  val riskTypes = Array("int", "string", "string", "string", "string", "string"
//    , "string", "string", "int")
  val riskTable = ModelFactory.getTableName(classOf[Risk])
  val riskColumns = ModelFactory.getFieldNames(classOf[Risk])
  val riskTypes = ModelFactory.getFieldTypes(classOf[Risk])

  def getRisk(projectId: String): Array[Risk] = {
   val risks = DaoFactory.select(conn, riskTable, riskColumns,
     Array("project_id"), Array(projectId), Array("int"))
    risks.map(x => ModelFactory.createRisk(x))
  }

}
