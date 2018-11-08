package edu.training.controllers.servlets;

import edu.training.model.entities.Publication;
import edu.training.model.services.FilteringService;
import edu.training.model.services.PublicationService;
import edu.training.model.services.ReferencesService;

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
                dispatcher = getReferencesDispatcher(request, service, publication);
            } else {
                dispatcher = getPublicationDispatcher(request, publication);
            }
        } else {
            dispatcher = getAllPublicationsDispatcher(request, service);
        }
        dispatcher.forward(request, response);
    }

    private RequestDispatcher getPublicationDispatcher(HttpServletRequest request, Publication publication) {
        RequestDispatcher dispatcher;
        request.setAttribute("publication", publication);
        dispatcher = request.getRequestDispatcher(SHOW_ONE_PATH);
        return dispatcher;
    }

    private RequestDispatcher getReferencesDispatcher(HttpServletRequest request, PublicationService service, Publication publication) {
        RequestDispatcher dispatcher;
        List<Publication> publications = service.getAll();
        ReferencesService referencesService = new ReferencesService();
        request.setAttribute("publications",
                Objects.requireNonNull(
                        referencesService
                                .getPublicationsFromReferences(
                                        publication.getReferences(),
                                        publications)));
        dispatcher = request.getRequestDispatcher(SHOW_ALL_PATH);
        return dispatcher;
    }

    private RequestDispatcher getAllPublicationsDispatcher(HttpServletRequest request, PublicationService service) {
        RequestDispatcher dispatcher;
        FilteringService filteringService = new FilteringService();
        List<Publication> publications = service.getAll();
        String filtered = request.getParameter("filtered");
        if (filtered != null && filtered.equals("true")) {
            String doi = request.getParameter("doi");
            String keyWords = request.getParameter("key_words");
            if (doi != null && keyWords != null) {
                publications = filteringService.filter(publications, doi, keyWords);
            }
        }
        String sorted = request.getParameter("sorted");
        if (sorted != null && sorted.equals("true")) {
            publications = filteringService.sortByRelevance(publications);
        }
        request.setAttribute("publications", publications);
        dispatcher = request.getRequestDispatcher(SHOW_ALL_PATH);
        return dispatcher;
    }

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        processRequest(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }
}