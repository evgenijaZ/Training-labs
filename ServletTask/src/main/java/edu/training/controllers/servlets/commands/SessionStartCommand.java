package edu.training.controllers.servlets.commands;

import edu.training.model.entities.Publication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SessionStartCommand extends Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Publication> publications = service.getAll();
        session.setAttribute("publications", publications);
        req.getRequestDispatcher("/WEB-INF/view/publications.jsp").forward(req, resp);
    }
}