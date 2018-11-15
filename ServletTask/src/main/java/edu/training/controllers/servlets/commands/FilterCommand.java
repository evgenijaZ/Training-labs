
package edu.training.controllers.servlets.commands;

import edu.training.model.entities.Publication;
import edu.training.model.services.FilteringService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class FilterCommand extends Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String doi = req.getParameter("doi");
        String keyWords = req.getParameter("key_words");
        if (doi != null && keyWords != null) {
            FilteringService filteringService = new FilteringService();
            session.setAttribute("publications",
                    filteringService.filter(
                            (List<Publication>) session.getAttribute("publications"), doi, keyWords));
        }
        req.getRequestDispatcher("/WEB-INF/view/publications.jsp").forward(req, resp);
    }
}
