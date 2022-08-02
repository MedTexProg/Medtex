package com.example.medtex;

import DAO.UserDAO;
import Entity.User;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "AddUserServlet", value = "/AddUserServlet")
public class AddUserServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(Logger.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        request.setCharacterEncoding("utf8");
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setFirstName(request.getParameter("firstname"));
        user.setLastName(request.getParameter("lastname"));
        user.setEmail(request.getParameter("email"));
        SimpleDateFormat birthdayFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // To convert java.util.Date object to java.sql.Date object.
            java.util.Date utilDate = birthdayFormat.parse(request.getParameter("birthday"));
            Date sqlDate = new Date(utilDate.getTime());
            user.setBirthday(sqlDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        user.setMobile(request.getParameter("mobile"));
        user.setAge(Long.parseLong(request.getParameter("age")));
        user.setGender(request.getParameter("gender"));
        user.setAddress(request.getParameter("address"));
        UserDAO userDAO = new UserDAO();
        boolean result = false;
        result = userDAO.insertUser(user);

        if (result) {
            request.getRequestDispatcher("/UserListServlet").forward(request, response);
            System.out.println("Add user successfully!");
            logger.info("A user has been successfully added!");
        } else {

            request.setAttribute("info", "Adding user failed! Please try again!");
            logger.info("Failed to add an user!");
            RequestDispatcher rd = request.getRequestDispatcher("/Admin/AddUser.jsp");
            rd.forward(request, response);

        }
    }
}
