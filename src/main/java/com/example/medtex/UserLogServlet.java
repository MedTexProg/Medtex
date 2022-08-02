package com.example.medtex;

import DAO.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;

@WebServlet(name = "UserLogServlet", value = "/UserLogServlet")
public class UserLogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        ResultSet rs = userDAO.queryUserLoginTimes();
        ResultSet gender_rs = userDAO.queryUserGenders();
        request.setAttribute("userlogs_rs", rs);
        request.setAttribute("userlogs_gender_rs", gender_rs);
        RequestDispatcher rd = request.getRequestDispatcher("Admin/UserLogs.jsp");
        rd.forward(request, response);
    }
}
