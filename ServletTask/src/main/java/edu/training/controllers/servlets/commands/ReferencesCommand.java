package edu.training.controllers.servlets.commands;

import edu.training.model.entities.Publication;
import edu.training.model.services.ReferencesService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ReferencesCommand extends Command {
    private ReferencesService referencesService = new ReferencesService();

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long id = Long.valueOf(req.getParameter("id"));
        List<Publication> references = referencesService
                .getPublicationsFromReferences(
                        service.findById(id).getReferences(),
                        service.getAll());
        session.setAttribute("publications", references);
        req.getRequestDispatcher("/WEB-INF/view/publications.jsp").forward(req, resp);
    }
}
