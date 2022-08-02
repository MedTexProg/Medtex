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

@WebServlet(name = "SearchServlet", value = "/SearchServlet")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchStr = request.getParameter("searchStr");
        UserDAO userDAO = new UserDAO();
        ResultSet rs = userDAO.searchUser(searchStr);
        ResultSet rsSearchCount = userDAO.searchUserCount(searchStr);
        request.setAttribute("rsSearchCount", rsSearchCount);
        request.setAttribute("searchResult", rs);
        RequestDispatcher rd = request.getRequestDispatcher("Admin/Admin.jsp");
        rd.forward(request, response);

    }
}
