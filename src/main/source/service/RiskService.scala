package service

import java.sql.Connection

import dao.{DaoFactory, RiskDao}
import model.Risk

/**
  * Created by ReggieYang on 2016/11/6.
  */
class RiskService(conn: Connection) {


  val riskDao = new RiskDao(conn)


  def addRisk(risk: Array[String]) = {
    DaoFactory.save(conn, riskDao.riskTable, risk.drop(1), riskDao.riskColumns.drop(1))
  }

  def deleteRisk(riskId: String) = {
    DaoFactory.delete(conn, riskDao.riskTable, riskDao.riskColumns.take(1), Array(riskId), riskDao.riskTypes.take(1))
  }

  def modifyRisk(risk: Array[String]) = {
    DaoFactory.updateByPK(conn, riskDao.riskTable, riskDao.riskColumns, risk, riskDao.riskTypes)
  }

  def allRisk(projectId: String): Array[Risk] = {
    riskDao.getRisk(projectId)
  }


}
