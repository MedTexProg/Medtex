package com.example.medtex;

import DAO.UserDAO;
import Entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "UpdateCompleteServlet", value = "/UpdateCompleteServlet")
public class UpdateCompleteServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(Logger.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        request.setCharacterEncoding("utf8");
        user.setId(request.getParameter("Id"));
        user.setUsername(request.getParameter("username"));
        user.setFirstName(request.getParameter("firstname"));
        user.setLastName(request.getParameter("lastname"));
        user.setEmail(request.getParameter("email"));
        user.setGender(request.getParameter("gender"));
        SimpleDateFormat birthdayFormat = new SimpleDateFormat("yyyy-MM-dd"); //java.util.Date
        try {
            // birthdayFormat format this birthday string to java.util.Date.
            java.util.Date utilDate = birthdayFormat.parse(request.getParameter("birthday"));
            Date sqlDate = new Date(utilDate.getTime());
            user.setBirthday(sqlDate); // requires java.sql.Date.
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setAge(Long.parseLong(request.getParameter("age")));
        user.setMobile(request.getParameter("mobile"));
        user.setPassword(request.getParameter("password"));
        user.setAddress(request.getParameter("address"));
        UserDAO userDAO = new UserDAO();
        boolean result = userDAO.updateUser(user);
        if (result) {
            System.out.println(user.getUsername() + " is updated successfully!");
            logger.info(user.getUsername() + "is updated successfully!");
            request.setAttribute("username", user.getUsername());
            request.getRequestDispatcher("/userDetailServlet").forward(request, response);
        } else {
            System.out.println("Failed to update " + user.getUsername());
            logger.error("Failed to update " + user.getUsername());
            request.setAttribute("info", "Failed to update " + user.getUsername());
            request.setAttribute("username", user.getUsername());
            request.getRequestDispatcher("/userDetailServlet").forward(request, response);
        }
    }
}
