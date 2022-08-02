package com.example.medtex;

import DAO.UserDAO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "userDeleteServlet", value = "/userDeleteServlet")
public class userDeleteServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(Logger.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        UserDAO userDAO = new UserDAO();
        boolean result = userDAO.delete(username);
        if (result) {
            logger.info(username + " has been deleted!");
            System.out.println(username + " has been deleted!");
            request.getRequestDispatcher("/UserListServlet").forward(request, response);
        } else {
            logger.info("Failed to delete " + username + "! Please try again!");
            System.out.println("Failed to delete " + username + "! Please try again!");
            request.getRequestDispatcher("/UserListServlet").forward(request, response);
        }
    }
}
