package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.DBConnection;
import model.Project;
import model.User;
import org.apache.struts2.ServletActionContext;
import service.ProjectService;
import util.ServletUtils;
import util.Utils;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.sql.Connection;

/**
 * Created by soleil on 16/11/9.
 */
public class ProjectAction extends ActionSupport {
    private String projectId;
    private String projectList;
    private String developerList;
    private String developerName;
    private String project;//json格式项目
    private String userJson;
    private String UserName;

    //get database connection
    Connection conn = DBConnection.getConnection();
    //instantiate a service with the connection
    ProjectService ps = new ProjectService(conn);


    //获取该用户的全部项目信息
    public void getProjectByDeveloper() throws Exception {
        ActionContext context = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
        setUserName(request.getParameter("userName"));
        setProjectList(Utils.serializeJson(ps.allProject(getUserName())));
        ServletUtils.sendResponse(projectList);
    }

    //获取一个项目的全部开发者
    public void getAllDeveloper() throws Exception {
        ActionContext context = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
        setProjectId(request.getParameter("projectId"));
        setDeveloperList(Utils.serializeJson(ps.allProjectDeveloper(getProjectId())));
//        setDeveloperList(Utils.serializeJson(ps.allProjectDeveloper("123")));
//        print("全部开发者信息"+developerList);
        ServletUtils.sendResponse(developerList);
    }

    //测试方法
    public void print(String test) throws Exception {
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        PrintWriter out = ServletActionContext.getResponse().getWriter();
        out.print(test);
        out.flush();
        out.close();
    }

    //添加开发者
    public void addDeveloper() {
        ActionContext context = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
        setDeveloperName(request.getParameter("developerName"));
        setProjectId(request.getParameter("projectId"));
        ps.addDeveloper(developerName, projectId);
    }

    //删除开发者
    public void delDeveloper() {
        ActionContext context = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
        setDeveloperName(request.getParameter("username"));
        setProjectId(request.getParameter("projectId"));
        ps.deleteDeveloper(developerName, projectId);
    }

    //增加项目
    public void addProject() {
        ps.addProject(Utils.deserializeJson2Array(getProject(), Project.class));
    }

    //删除项目
    public void delProject() {
        ActionContext context = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
        setProjectId(request.getParameter("projectId"));
        ps.deleteProject(getProjectId());
    }

    //修改项目
    public void modifyProject() {
        ps.modifyProject(Utils.deserializeJson2Array(getProject(), Project.class));
    }


    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectList() {
        return projectList;
    }

    public void setProjectList(String jsonResult) {
        this.projectList = jsonResult;
    }

    public String getDeveloperList() {
        return developerList;
    }

    public void setDeveloperList(String developerList) {
        this.developerList = developerList;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getUserJson() {
        return userJson;
    }

    public void setUserJson(String userJson) {
        this.userJson = userJson;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}
