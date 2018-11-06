package edu.training.controllers.servlets;

import edu.training.model.entities.Publication;
import edu.training.services.FilteringService;
import edu.training.services.LibraryService;
import edu.training.services.PublicationService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet("/publications")
public class PublicationServlet extends HttpServlet {
    private final static String SHOW_ALL_PATH = "/WEB-INF/view/publications.jsp";
    private final static String SHOW_ONE_PATH = "/WEB-INF/view/publication.jsp";

    private void processRequest(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PublicationService service = new PublicationService(InitContext.getDataSource());

        RequestDispatcher dispatcher;

        String publicationID = request.getParameter("id");
        if (publicationID != null) {

            int id = Integer.parseInt(publicationID);
            Publication publication = service.findById((long) id);
            String showReferences = request.getParameter("references");
            if (showReferences != null && showReferences.equals("true")) {
                List<Publication> publications = service.getAll();
                LibraryService libraryService = new LibraryService();
                request.setAttribute("publications",
                        Objects.requireNonNull(
                                libraryService
                                        .getPublicationsFromReferences(
                                                publication.getReferences(),
                                                publications)));
                dispatcher = request.getRequestDispatcher(SHOW_ALL_PATH);
            } else {
                request.setAttribute("publication", Objects.requireNonNull(publication));
                dispatcher = request.getRequestDispatcher(SHOW_ONE_PATH);
            }


        } else {
            List<Publication> publications = service.getAll();
            String sorted = request.getParameter("sorted");
            if (sorted != null && sorted.equals("true")) {
                FilteringService filteringService = new FilteringService();
                publications = filteringService.sortByRelevance(publications);
            }
            request.setAttribute("publications", publications);
            dispatcher = request.getRequestDispatcher(SHOW_ALL_PATH);
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