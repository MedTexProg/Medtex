package Entity;

public class UserLog {

  private String id;
  private String loginTime;
  private String logoutTime;
  private String clientIp;
  private String userId;
  private String clientOs;
  private String clientBrowser;

  public UserLog(){}

  public UserLog(String userlogID, String loginTime, String clientIP, String UserID, String clientOS, String clientBrowser) {
    this.id = userlogID;
    this.loginTime = loginTime;
    this.userId = UserID;
    this.clientBrowser = clientBrowser;
    this.clientOs = clientOS;
    this.clientIp = clientIP;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getLoginTime() {
    return loginTime;
  }

  public void setLoginTime(String loginTime) {
    this.loginTime = loginTime;
  }


  public String getLogoutTime() {
    return logoutTime;
  }

  public void setLogoutTime(String logoutTime) {
    this.logoutTime = logoutTime;
  }


  public String getClientIp() {
    return clientIp;
  }

  public void setClientIp(String clientIp) {
    this.clientIp = clientIp;
  }


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public String getClientOs() {
    return clientOs;
  }

  public void setClientOs(String clientOs) {
    this.clientOs = clientOs;
  }


  public String getClientBrowser() {
    return clientBrowser;
  }

  public void setClientBrowser(String clientBrowser) {
    this.clientBrowser = clientBrowser;
  }

}
