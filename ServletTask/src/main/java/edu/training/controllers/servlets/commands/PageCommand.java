package edu.training.controllers.servlets.commands;

import edu.training.model.entities.Publication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PageCommand extends Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long id = Long.valueOf(req.getParameter("id"));
        Publication publication = service.findById(id);
        session.setAttribute("publication", publication);
        req.getRequestDispatcher("/WEB-INF/view/publication.jsp").forward(req, resp);
    }
}
