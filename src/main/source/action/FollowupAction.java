package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.DBConnection;
import model.Followup;
import org.apache.struts2.ServletActionContext;
import service.FollowupService;
import service.UserService;
import util.ServletUtils;
import util.Utils;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.sql.Connection;

/**
 * Created by soleil on 16/11/9.
 */
public class FollowupAction extends ActionSupport {
    private String followupList;
    private String riskId;
    private String followup;
    private String followupId;

    //get database connection
    Connection conn = DBConnection.getConnection();
    //instantiate a service with the connection
    FollowupService fs = new FollowupService(conn);

    //增加跟踪者
    public void addFollowup() {
        fs.addFollowup(Utils.deserializeJson2Array(getFollowup(), Followup.class));
    }

    //删除跟踪者
    public void delFollowup() {
        ActionContext context = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
        setFollowupId(request.getParameter("followupId"));
        fs.deleteFollowup(getFollowupId());
    }

    //修改跟踪记录
    public void modifyFollowup() {
        fs.modifyFollowup(Utils.deserializeJson2Array(getFollowup(), Followup.class));
    }

    //显示全部跟踪用户
    public void allFollowup() throws Exception {
        ActionContext context = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
        setRiskId(request.getParameter("riskId"));
        setFollowupList(Utils.serializeJson(fs.allFollowup(getRiskId())));
        ServletUtils.sendResponse(followupList);
//        setFollowupList(Utils.serializeJson(fs.allFollowup("123")));
//        print(getFollowupList());
    }

    //    //测试方法
//    public void print(String test) throws Exception{
//        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
//        PrintWriter out = ServletActionContext.getResponse().getWriter();
//        out.print(test);
//        out.flush();
//        out.close();
//    }
    public String getFollowupList() {
        return followupList;
    }

    public void setFollowupList(String followupList) {
        this.followupList = followupList;
    }

    public String getRiskId() {
        return riskId;
    }

    public void setRiskId(String riskId) {
        this.riskId = riskId;
    }

    public String getFollowup() {
        return followup;
    }

    public void setFollowup(String followup) {
        this.followup = followup;
    }

    public String getFollowupId() {
        return followupId;
    }

    public void setFollowupId(String followupId) {
        this.followupId = followupId;
    }
}
