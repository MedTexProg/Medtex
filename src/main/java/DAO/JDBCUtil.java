package DAO;

import java.sql.*;
import org.apache.log4j.Logger;

public class JDBCUtil {

    private static final Logger logger = Logger.getLogger(Logger.class);

    public JDBCUtil() {
        logger.info("JDBCUtil class is instantiated!");
    }

    static Connection conn = null;
    static PreparedStatement pStmt = null;

    static {
        logger.info("Start loading MySQL driver!");
        try{
            String driverName = "com.mysql.jdbc.Driver"; //MySQL 5.x
            Class.forName(driverName);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Fail to load MySQL driver!");
        }
        logger.debug("MySQL driver is loaded!");
    }

    public static Connection getConnection(){
        logger.info("Start connecting to MySQL!");
        try {
            String server_url = "jdbc:mysql://127.0.0.1/jdbc_test?useUnicode=true&characterEncoding=utf8";
            String user_name = "root";
            String password = "123456";
            conn = DriverManager.getConnection(server_url, user_name, password);
        } catch (Exception e){
            e.printStackTrace();
            logger.error("MySQL connection failed!");
        }
        return conn;
    }

    // login case: sql, username, password --> args[2]
    public static ResultSet queryUser(String sql, Object... args) throws Exception {
        conn = getConnection();
        pStmt = conn.prepareStatement(sql);
        for (int i = 0; i < args.length; i++){
            pStmt.setObject(i + 1, args[i]);
        }
        return pStmt.executeQuery(); // ResultSet object
    }

    public static boolean insert(String sql, Object... args) throws Exception {
        conn = getConnection();
        PreparedStatement pStmt = conn.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            pStmt.setObject(i + 1, args[i]);
        }
        return  pStmt.executeUpdate() >=1 ? true:false; //At least one record is updated successfully.
    }


    public static boolean updateUser(String sql, Object... args) throws Exception {
        conn = getConnection();
        PreparedStatement pStmt = conn.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            pStmt.setObject(i + 1, args[i]);
        }
        return  pStmt.executeUpdate() >=1 ? true:false; //At least one record is updated successfully.
    }

    public static boolean delete(String sql, Object... args) throws Exception {
        conn = getConnection();
        PreparedStatement pStmt = conn.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            pStmt.setObject(i + 1, args[i]);
        }
        return  pStmt.executeUpdate() >=1 ? true:false; //At least one record is updated successfully.
    }

    public static boolean updateUserLog(String sql, Object... args) throws Exception {
        Connection con = null;
        PreparedStatement pStmt = null;
        con = getConnection();
        pStmt = con.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            pStmt.setObject(i + 1, args[i]);
        }
        return pStmt.executeUpdate() >= 1 ? true : false;
    }

    public static void close(ResultSet rs){
        try{
            if(rs!=null){
                rs.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            if(pStmt!=null){
                pStmt.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            if(conn!=null){
                conn.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
