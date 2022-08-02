package com.example.medtex;

import DAO.UserDAO;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "userDetailServlet", value = "/userDetailServlet")
public class userDetailServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(Logger.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        UserDAO userDAO = new UserDAO();
        ResultSet rs = userDAO.queryUserByUsername(username);
        try {
            if (rs.next()){
                request.setAttribute("userDetail", rs);
                RequestDispatcher rd = request.getRequestDispatcher("Admin/UserDetail.jsp");
                rd.forward(request, response);
            } else {
                logger.error("Failed to obtain the information of " + username + "!" );
                System.out.println("Failed to obtain the information of " + username + "!");
                request.getRequestDispatcher("/UserListServlet").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
