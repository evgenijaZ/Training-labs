package edu.training.model.services;

import edu.training.model.dao.ElectronicPublicationDao;
import edu.training.model.dao.PaperPublicationDao;
import edu.training.model.entities.Publication;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PublicationService {
    private ElectronicPublicationDao elPubDAO;
    private PaperPublicationDao papPubDAO;


    public PublicationService(DataSource dataSource) {
        Objects.requireNonNull(dataSource);
        elPubDAO = new ElectronicPublicationDao(dataSource);
        papPubDAO = new PaperPublicationDao(dataSource);
    }

    public List<Publication> getAll() {
        List<Publication> result = new ArrayList<>();
        result.addAll(elPubDAO.getAll());
        result.addAll(papPubDAO.getAll());
        return result;
    }

    public Publication findById(long id) {
        Publication publication = elPubDAO.getByKey(id);
        if (publication == null) publication = papPubDAO.getByKey(id);
        return publication;
    }
}
