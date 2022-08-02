package com.example.medtex;

import DAO.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "AccountIsExistenceServlet", value = "/AccountIsExistenceServlet")
public class AccountIsExistenceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Prevent browser cache.
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        // Server-side
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        UserDAO userDAO = new UserDAO();
        ResultSet rs = userDAO.queryUserByUsername(username);
        try {
            if (rs.next()){
                out.print("This username has been used!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
