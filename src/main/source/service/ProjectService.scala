package service

import java.sql.Connection

import dao.{DaoFactory, ProjectDao}
import model.{Project, User}

/**
  * Created by ReggieYang on 2016/11/6.
  */
class ProjectService(conn: Connection) {

  val projectDao = new ProjectDao(conn)

  def addDeveloper(developerName: String, projectId: String) = {
    DaoFactory.save(conn, projectDao.pdTable, Array(projectId, developerName), projectDao.projectColumns)
  }

  def deleteDeveloper(developerName: String, projectId: String) = {
    DaoFactory.delete(conn, projectDao.pdTable, projectDao.pdColumns, Array(projectId, developerName), projectDao.pdTypes)
  }

  def allProjectDeveloper(projectId: String): Array[String] = {
    projectDao.getDeveloper(projectId)
  }

  def addProject(project: Array[String]) = {
    DaoFactory.save(conn, projectDao.projectTable, project.drop(1), projectDao.projectColumns.drop(1))
    //the logic of adding the creator into developers is handled by a trigger in mysql!
  }

  def deleteProject(projectId: String) = {
    DaoFactory.delete(conn, projectDao.projectTable, Array("project_id"), Array(projectId), Array("int"))
    //the logic of deleting developersis handled by a trigger in mysql!
  }

  def modifyProject(project: Array[String]) = {
    DaoFactory.update(conn, projectDao.projectTable, projectDao.projectColumns.drop(1), project.drop(1), projectDao.projectTypes.drop(1),
      projectDao.projectColumns.take(1), project.take(1), projectDao.projectTypes.take(1))
  }

  def allProject(userName: String): Array[Project] = {
    projectDao.getProject(userName)
  }


}
