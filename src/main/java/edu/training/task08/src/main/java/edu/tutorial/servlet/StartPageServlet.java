package edu.tutorial.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StartPageServlet extends HttpServlet {
    private final static String INDEX_PATH = "/WEB-INF/view/index.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        dispatcher = request.getRequestDispatcher(INDEX_PATH);
        dispatcher.forward(request, response);
    }
}
