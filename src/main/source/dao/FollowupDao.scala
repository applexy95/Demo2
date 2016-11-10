package dao

import java.sql.Connection

import model.Followup

/**
  * Created by ReggieYang on 2016/11/6.
  */
class FollowupDao(conn:Connection) {

//  val followupTable = "followup"
//  val followupColumns = Array("followup_id", "status", "followup_date", "risk_id")
//  val followupTypes = Array("int", "string", "string", "string", "int")
  val followupTable = ModelFactory.getTableName(classOf[Followup])
  val followupColumns = ModelFactory.getFieldNames(classOf[Followup])
  val followupTypes = ModelFactory.getFieldTypes(classOf[Followup])

  def getFollowup(riskId: String): Array[Followup] = {
    val risks = DaoFactory.select(conn, followupTable, followupColumns,
      Array("risk_id"), Array(riskId), Array("int"))
    risks.map(x => ModelFactory.createFollowup(x))
  }

}
