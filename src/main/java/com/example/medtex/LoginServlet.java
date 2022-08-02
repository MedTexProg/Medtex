package com.example.medtex;

import DAO.UserDAO;
import Entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String info = "Username or password is incorrect! Please try again!";
        // User model to encapsulate the user data.
        User user = new User(username, password);
        UserDAO userDAO = new UserDAO();
        boolean result = userDAO.checkLogin(user.getUsername(), user.getPassword(), request);
        if (result){
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            if ((username != null) && (username.equals("admin"))) {
                System.out.println("The Admin logs in!");
                response.sendRedirect("Admin/Admin.jsp");
            } else if (username != null){
                System.out.println("client is in!");
                response.sendRedirect("one-music/index.jsp");
            }
            } else {
            request.setAttribute("info", info);
            response.sendRedirect("Login.jsp");
            }
        }
    }

