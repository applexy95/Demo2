package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.DBConnection;
import service.ProjectService;
import util.Utils;

import java.sql.Connection;

/**
 * Created by gaowei on 16-11-8.
 */

public class TestAction extends ActionSupport {
    /**
     *
     */
    private static final long serialVersionUID = 6896743704148376054L;
    private String projectList;
    private String hello = "hello";

    // 定义处理用户请求的execute方法
    public String execute() throws Exception {
        return "success";
    }

    //获取该用户的全部项目信息
    public String getAllProjectInfo() throws Exception {
        //get database connection
        Connection conn = DBConnection.getConnection();
        //instantiate a service with the connection
        ProjectService ps = new ProjectService(conn);

        setProjectList(Utils.serializeJson(ps.allProject("kaimyang")));

        return "success";
    }

    public String getProjectList() {
        return projectList;
    }

    public void setProjectList(String projectList) {
        this.projectList = projectList;
    }
}


