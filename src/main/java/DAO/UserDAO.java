package DAO;

import Entity.User;
import Entity.UserLog;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;
public class UserDAO {

    private static final Logger logger = Logger.getLogger(Logger.class);
    public boolean checkLogin;

    public List<User> queryUser() throws Exception {
        String sql = "select * from jdbc_test.jdbc_test_user;";
        List<User> userList = new ArrayList<>();
        try {
            ResultSet rs = JDBCUtil.queryUser(sql);
            while (rs.next()){
                User user = new User();
                user.setId(rs.getString("id"));
                user.setUsername(rs.getString("username"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setGender(rs.getString("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setAge(rs.getLong("age"));
                user.setPassword(rs.getString("password"));
                user.setAddress(rs.getString("address"));
                user.setMobile(rs.getString("mobile"));
                user.setEmail(rs.getString("email"));
                userList.add(user);
            }
            return userList;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<User> listUsersByPage(int startIndex, int pageSize){
        String sql = "select * from jdbc_test.jdbc_test_user limit ?,?";
        List<User> userList = new ArrayList<>();
        try {
            ResultSet rs = JDBCUtil.queryUser(sql, startIndex, pageSize);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setUsername(rs.getString("username"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setGender(rs.getString("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setAge(rs.getLong("age"));
                user.setPassword(rs.getString("password"));
                user.setAddress(rs.getString("address"));
                user.setMobile(rs.getString("mobile"));
                user.setEmail(rs.getString("email"));
                userList.add(user);
            }
            return userList;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet queryUserLoginTimes(){
        ResultSet rs = null;
        String sql = "select username, count(1)\n" +
                "from jdbc_test_user_log inner join jdbc_test_user jtu on jdbc_test_user_log.userId = jtu.id group by username;";
        try {
            rs = JDBCUtil.queryUser(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public boolean checkLogin(String username, String password, HttpServletRequest request){ // 10 nms
        boolean userlog_flag = false;
        String sql = "select * from jdbc_test_user where username=? and password=?"; //placeholders
        try{
            ResultSet rs = JDBCUtil.queryUser(sql, username, password);
            if (rs.next()){ //If it is true, it means there is a record whose username and password match the ones provided the user.
                userlog_flag = UserLoginLog(rs.getString(1), request); //
                if (userlog_flag) {
                    System.out.println("A user login operation is successfully logged!"); // Probing.-- Logger //  1 nm // logging.
                } else {
                    System.out.println("Failed to log a user login operation!");  // Probing. -- Logger.
                }
                return true;
            }
            JDBCUtil.close(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ResultSet queryUserByUsername(String username) {
        ResultSet rs = null;
        try{
            String sql = "select * from jdbc_test.jdbc_test_user where username='" + username + "';";
            rs = JDBCUtil.queryUser(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public boolean insert(User user){
        boolean result = false;
        String userID = UUID.randomUUID().toString();
        try{
            String sql = "insert into jdbc_test.jdbc_test_user(id, username, password, email) values (?,?,?,?)";
            result = JDBCUtil.insert(sql, userID, user.getUsername(), user.getPassword(), user.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean insertUser(User user){
        boolean result = false;
        String userId = UUID.randomUUID().toString();
        try{
            String sql = "insert into jdbc_test.jdbc_test_user(" +
                    "id, username, email, first_name, last_name, gender, birthday, age, address, mobile, password)" +
                    "values(?,?,?,?,?,?,?,?,?,?,?)";
            result = JDBCUtil.insert(sql, userId, user.getUsername(), user.getEmail(), user.getFirstName(), user.getLastName(),user.getGender(),user.getBirthday(), user.getAge(), user.getAddress(), user.getMobile(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public ResultSet searchUser(String searchStr) {
        ResultSet rs = null;
        String sql = "select * from jdbc_test.jdbc_test_user where username like '%" + searchStr + "%';";
        try {
            rs = JDBCUtil.queryUser(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet queryUserGenders(){
        ResultSet rs = null;
        String sql = "\n" +
                "select count(*) GenderNum, \n" +
                "       case gender when 'Male' then 'Male' \n" +
                "           When 'Female' then 'Female' end as Gender\n" +
                "from jdbc_test_user group by gender;";
        try {
            rs = JDBCUtil.queryUser(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet searchUserCount(String searchStr) {
        ResultSet rs = null;
        String sql = "select count(*) from jdbc_test.jdbc_test_user where username like '%" + searchStr + "%';";
        try {
            rs = JDBCUtil.queryUser(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public boolean updateUser(User user) {
        boolean result = false;
        try {
            String sql = "update jdbc_test.jdbc_test_user set username=?, " +
                    "email=?," +
                    "first_name=?, " +
                    "last_name=?, " +
                    "gender=?, " +
                    "birthday=?," +
                    "age=?," +
                    "address=?," +
                    "mobile=?, " +
                    "password=? where id=?;";
            result = JDBCUtil.updateUser(sql, user.getUsername(),
                    user.getEmail(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getGender(),
                    user.getBirthday(),
                    user.getAge(),
                    user.getAddress(),
                    user.getMobile(),
                    user.getPassword(),
                    user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean delete(String username) {
        boolean result = false;
        try {
            String sql = "delete from jdbc_test.jdbc_test_user where username='" + username + "';";
            result = JDBCUtil.delete(sql);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // Monitor the userLog: when a user logs in, we record his/her device info. and then, record the login time. Put all this info into the database.
    public boolean UserLoginLog(String UserID, HttpServletRequest request){

        boolean result = false;
        String userlogID = UUID.randomUUID().toString(); // ID
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        String currentTime = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        String loginTime = currentTime;
        String clientInfo = request.getHeader("user-agent");
        StringTokenizer st = new StringTokenizer(clientInfo, ";");
        String clientBrowser = st.nextToken(); // "A long String " ; "Another long string."
        String clientOS = st.nextToken();
        String clientIP = getClientIP(request);
        // Put this info. into a UserLog.
        UserLog userLog = new UserLog(userlogID, loginTime, clientIP, UserID, clientOS, clientBrowser);  // Instantiate the UserLog model.
        try {
            String sql = "insert into jdbc_test.jdbc_test_user_log(id, login_time, clientIP, userId, clientOS, clientBrowser)values(?,?,?,?,?,?)";
            result = JDBCUtil.insert(sql, userLog.getId(), userLog.getLoginTime(), userLog.getClientIp(), userLog.getUserId(), userLog.getClientOs(), userLog.getClientBrowser());
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean setLogoutTime(User user, UserLog userLog) {
        boolean result = false;
        try {
            //String sql = "update jdbc_test_user_log set logout_time = ? where userId= (select id from jdbc_test.jdbc_test_user where username = ?) order by login_time desc limit 1";
            String sql = "update jdbc_test_user_log set logout_time = ? " +
                    "where userId= (select id from jdbc_test.jdbc_test_user where username = ?) " +
                    "order by STR_TO_DATE(concat(substring_index(login_time, 'at', 1),' ', left(right(login_time, 13), 9)), '%Y-%m-%d %H:%i:%s') desc " +
                    "limit 1";
            result = JDBCUtil.updateUserLog(sql, userLog.getLogoutTime(), user.getUsername());
            return result;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static String getClientIP(HttpServletRequest request) {
        //Filter the proxies.
        String fromSource = "X-Real-IP";
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
            fromSource = "X-Forwarded-For";
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            fromSource = "Proxy-Client-IP";
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            fromSource = "WL-Proxy-Client-IP";
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            fromSource = "request.getRemoteAddr";
        }
        logger.info("App Client IP: "+ip+", fromSource: "+fromSource);
        return ip;
    }
}
