package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.DBConnection;
import model.User;
import org.apache.struts2.ServletActionContext;
import service.UserService;
import util.ServletUtils;
import util.Utils;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

/**
 * Created by soleil on 16/11/9.
 */
public class UserAction extends ActionSupport{
    private String userName;
    private String company;
    private String user;
    private String password;
    private String result;
    private String colleagueList;

    //get database connection
    Connection conn = DBConnection.getConnection();
    //instantiate a service with the connection
    UserService us = new UserService(conn);

    //添加用户
    public void addUser() {
        us.addUser(Utils.deserializeJson2Array(getUser(), User.class));
    }
    //验证用户
    public void verifyUser(){
        ActionContext context = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
        setUserName(request.getParameter("userName"));
        setPassword(request.getParameter("password"));
        setResult(us.verifyUser(getUserName(),getPassword()));
        ServletUtils.sendResponse(result);
    }

    //获取同事列表
    public void getColleague() throws Exception{
        ActionContext context = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
        setCompany(request.getParameter("company"));
        setColleagueList(Utils.serializeJson(us.getColleague(getCompany())));
        ServletUtils.sendResponse(colleagueList);
//        setColleagueList(Utils.serializeJson(us.getColleague("eBay")));
//        ProjectAction test = new ProjectAction();
//        test.print(colleagueList);
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String isResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }



    public String getColleagueList() {
        return colleagueList;
    }

    public void setColleagueList(String colleagueList) {
        this.colleagueList = colleagueList;
    }
}
