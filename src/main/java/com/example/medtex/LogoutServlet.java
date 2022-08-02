package com.example.medtex;

import DAO.UserDAO;
import Entity.User;
import Entity.UserLog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "LogoutServlet", value = "/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getSession(false)!=null
            && request.getSession().getAttribute("username")!=null) {


            String username = (String) request.getSession().getAttribute("username");
            User user = new User();
            user.setUsername(username);

            request.getSession().invalidate();

            UserLog userLog = new UserLog();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            String currentTime = formatter.format(new Date(System.currentTimeMillis()));
            String logout_time = currentTime;
            userLog.setLogoutTime(logout_time);
            UserDAO userDAO = new UserDAO();
            boolean result = userDAO.setLogoutTime(user, userLog);
        }

        response.sendRedirect(request.getContextPath()+"/Login.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
