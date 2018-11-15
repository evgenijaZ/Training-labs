package edu.training.controllers.servlets.commands;

import edu.training.model.entities.Publication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UnknownCommand extends Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message", "error.unknown_command");
        req.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(req, resp);
    }
}