package com.example.medtex;

import DAO.UserDAO;
import Entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserListServlet", value = "/UserListServlet")
public class UserListServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(Logger.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        logger.info("UserListServlet is called!");
        try {
            int pageNum = 1;
            String pageIndex = request.getParameter("pageIndex");
            if (pageIndex != null){
                pageNum = Integer.valueOf(pageIndex);
            }

            List<User> userList = userDAO.queryUser();
            int totalUsers = userList.size();
            int usersPerPage = 6;
            int totalPages = totalUsers % usersPerPage == 0 ? totalUsers / usersPerPage : totalUsers / usersPerPage + 1;
            int beginIndex = (pageNum - 1) * usersPerPage;
            List<User> users = userDAO.listUsersByPage(beginIndex, usersPerPage);
            if ((!userList.isEmpty()) && (totalUsers!=0)){
                System.out.println(userList.size() + "user(s) found in the database.");
                request.setAttribute("users", users);
                request.setAttribute("userList", userList);
                request.setAttribute("pageIndex", pageNum);
                request.setAttribute("totalPages", totalPages);
                RequestDispatcher rd = request.getRequestDispatcher("Admin/UserList.jsp");
                rd.forward(request, response);
            } else {
                System.out.println("No users found in the database.");
                request.setAttribute("userList", userList);
                request.setAttribute("users", users);
                RequestDispatcher rd = request.getRequestDispatcher("Admin/UserList.jsp");
                rd.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
