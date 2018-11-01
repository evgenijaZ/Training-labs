package edu.tutorial.servlet;

import edu.tutorial.model.User;
import edu.tutorial.repository.UserRepository;
import edu.tutorial.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(
        name = "UserServlet",
        urlPatterns = "/users")
public class UserServlet extends HttpServlet {
    private final static String USER_PATH = "/WEB-INF/view/user.jsp";
    private final static String USERS_PATH = "/WEB-INF/view/users.jsp";

    private UserService studentService = new UserService(new UserRepository());

    private void processRequest(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String studentID = request.getParameter("id");
        RequestDispatcher dispatcher;

        if (studentID != null) {
            int id = Integer.parseInt(studentID);
            studentService.findById((long) id)
                    .ifPresent(s -> request.setAttribute("user", s));
            dispatcher = request.getRequestDispatcher(USER_PATH);
        } else {
            List<User> users = studentService.getAll();
            if (users != null)
                request.setAttribute("users", users);
            dispatcher = request.getRequestDispatcher(USERS_PATH);
        }

        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }
}