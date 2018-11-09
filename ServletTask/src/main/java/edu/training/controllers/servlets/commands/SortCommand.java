package edu.training.controllers.servlets.commands;

import edu.training.model.entities.Publication;
import edu.training.model.services.FilteringService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SortCommand extends Command {
    private FilteringService filteringService = new FilteringService();

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("publications",
                filteringService.sortByRelevance(
                        (List<Publication>) session.getAttribute("publications")));
        req.getRequestDispatcher("/WEB-INF/view/publications.jsp").forward(req, resp);
    }
}
