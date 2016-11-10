package util


import org.apache.struts2.ServletActionContext

/**
  * Created by ReggieYang on 2016/11/10.
  */
object ServletUtils {

  def sendResponse(s: Any) = {
    lazy val RESPONSE_HEADER = "Access-Control-Allow-Origin"
    lazy val RESPONSE_HEADER2 = "Access-Control-Allow-Methods"
    lazy val RESPONSE_HEADER3 = "Access-Control-Allow-Headers"
    lazy val ALL_DOMAIN_NAME = "*"

    ServletActionContext.getResponse.setContentType("text/json;charset=utf-8")
    ServletActionContext.getResponse.addHeader(RESPONSE_HEADER, ALL_DOMAIN_NAME)
    ServletActionContext.getResponse.addHeader(RESPONSE_HEADER2, "GET, POST, OPTIONS")
    ServletActionContext.getResponse.addHeader(RESPONSE_HEADER3, "content-type")
    val out = ServletActionContext.getResponse.getWriter
    out.print(s)
    out.flush()
    out.close()
  }


}
