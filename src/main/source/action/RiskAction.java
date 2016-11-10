package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.DBConnection;
import model.Risk;
import org.apache.struts2.ServletActionContext;
import service.RiskService;
import util.ServletUtils;
import util.Utils;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

/**
 * Created by soleil on 16/11/9.
 */
public class RiskAction extends ActionSupport {
    private static final String SUCCESS = "success";
    private String riskList;
    private String projectId;
    private String risk;
    private String riskId;

    //get database connection
    Connection conn = DBConnection.getConnection();
    //instantiate a service with the connection
    RiskService rs = new RiskService(conn);


    //显示所有风险
    public void allRisk() throws Exception {
        ActionContext context = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
        setProjectId(request.getParameter("projectId"));
        setRiskList(Utils.serializeJson(rs.allRisk(getProjectId())));
        ServletUtils.sendResponse(riskList);
//        setRiskList(Utils.serializeJson(rs.allRisk("123")));
//        ProjectAction test = new ProjectAction();
//        test.print(riskList);
    }

    //增加风险
    public void addRisk() {
        rs.addRisk(Utils.deserializeJson2Array(getRisk(), Risk.class));
    }

    //删除风险
    public void delRisk() {
        ActionContext context = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
        setRiskId(request.getParameter("riskId"));
        rs.deleteRisk(getRiskId());
    }

    //修改风险
    public void modifyRisk() {
        rs.modifyRisk(Utils.deserializeJson2Array(getRisk(), Risk.class));
    }


    public String getRiskList() {
        return riskList;
    }

    public void setRiskList(String riskList) {
        this.riskList = riskList;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public String getRiskId() {
        return riskId;
    }

    public void setRiskId(String riskId) {
        this.riskId = riskId;
    }
}
