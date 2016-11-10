package service

import java.sql.Connection

import dao.{DaoFactory, FollowupDao}
import model.Followup

/**
  * Created by ReggieYang on 2016/11/6.
  */
class FollowupService(conn: Connection) {

  val followupDao = new FollowupDao(conn)

  def addFollowup(followup: Array[String]) = {
    DaoFactory.save(conn, followupDao.followupTable, followup.drop(1), followupDao.followupColumns.drop(1))
  }

  def deleteFollowup(followupId: String) = {
    DaoFactory.delete(conn, followupDao.followupTable, followupDao.followupColumns.take(1), Array(followupId), followupDao.followupTypes.take(1))
  }

  def modifyFollowup(followup: Array[String]) = {
    DaoFactory.updateByPK(conn, followupDao.followupTable, followupDao.followupColumns, followup, followupDao.followupTypes)
  }

  def allFollowup(riskId: String): Array[Followup] = followupDao.getFollowup(riskId)

}
