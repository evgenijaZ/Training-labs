package edu.training.model.dao;

import edu.training.model.entities.PaperPublication;
import edu.training.model.entities.Publication;
import edu.training.model.entities.PublishingHouse;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class PaperPublicationDao implements IDao<PaperPublication, Long> {

    private Session session;
    private ResourceBundle bundle = ResourceBundle.getBundle("queries");

    public PaperPublicationDao(DataSource dataSource) {
        session = new Session(dataSource);
    }

    @Override
    public List<PaperPublication> getAll() {
        String selectAllQuery = bundle.getString("pap-publication.select-all");
        String selectReferencesQuery = bundle.getString("references.select-all");

        List<PaperPublication> publications = new ArrayList<>();
        PreparedStatement selectAll = session.getPrepareStatement(selectAllQuery);
        try {
            ResultSet resultSet = selectAll.executeQuery();
            publications = parseResultSet(resultSet);
            PreparedStatement selectReferences = session.getPrepareStatement(selectReferencesQuery);
            for (Publication publication : publications) {
                List<String> references = new ArrayList<>();
                selectReferences.setLong(1, publication.getId());
                ResultSet referencesResultSet = selectReferences.executeQuery();
                while (referencesResultSet.next()) {
                    references.add(referencesResultSet.getString("doi"));
                }
                publication.setReferences(references);
            }
        } catch (SQLException e) {
            System.err.println("Cannot execute 'select all' query: " + e.getMessage());
        } finally {
            session.closeStatement(selectAll);
        }
        return publications;
    }

    @Override
    public boolean update(PaperPublication publication) {

        String updatePublicationQuery = bundle.getString("publication.update");
        String updatePaperPublicationQuery = bundle.getString("pap-publication.update");
        String selectIdQuery = bundle.getString("publication.select-id");
        String updateReferenceQuery = bundle.getString("references.update");

        String DOI = publication.getDOI();
        String name = publication.getName();
        String author = publication.getAuthor();
        List<String> references = publication.getReferences();
        String keyWords = publication.getKeyWords();
        int circulation = publication.getCirculation();
        String publishingHouseName = publication.getPublishingHouse().name;
        String publishingHouseCity = publication.getPublishingHouse().city;
        Long id = publication.getId();

        PreparedStatement updatePublication = null;
        PreparedStatement updatePaperPublication = null;
        PreparedStatement selectId = null;
        PreparedStatement updateReference = null;

        try {
            session.setAutoCommit(false);

            updatePublication = session.getPrepareStatement(updatePublicationQuery);
            updatePaperPublication = session.getPrepareStatement(updatePaperPublicationQuery);
            selectId = session.getPrepareStatement(selectIdQuery);
            updateReference = session.getPrepareStatement(updateReferenceQuery);

            updatePublication.setString(1, DOI);
            updatePublication.setString(2, name);
            updatePublication.setString(3, author);
            updatePublication.setString(4, keyWords);
            updatePublication.setLong(5, id);
            updatePublication.execute();


            updatePaperPublication.setInt(1, circulation);
            updatePaperPublication.setString(2, publishingHouseName);
            updatePaperPublication.setString(3, publishingHouseCity);
            updatePaperPublication.setLong(4, id);

            updatePaperPublication.execute();
            session.commit();

            for (String referenceDOI : references) {
                selectId.setString(1, referenceDOI);
                ResultSet idSet = selectId.executeQuery();
                String referenceId;
                if (idSet.next()) {
                    referenceId = idSet.getString(1);
                } else {
                    throw new SQLException("Creating reference failed, no publication found with DOI " + referenceDOI);
                }

                updateReference.setString(1, referenceId);
                updateReference.setLong(2, id);

                session.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.print("Transaction is being rolled back");
            session.rollback();
            return false;
        } finally {
            session.closeStatement(updatePublication);
            session.closeStatement(updatePaperPublication);
            session.closeStatement(selectId);
            session.closeStatement(updateReference);
            session.setAutoCommit(true);
        }
        return true;
    }

    @Override
    public PaperPublication getByKey(Long key) {
        String selectAllQuery = bundle.getString("pap-publication.select-by-id");
        String selectReferencesQuery = bundle.getString("references.select-all");

        PaperPublication publication = new PaperPublication();
        PreparedStatement statement = session.getPrepareStatement(selectAllQuery);
        try {
            statement.setInt(1, Math.toIntExact(key));
            ResultSet resultSet = statement.executeQuery();
            List<PaperPublication> publications = parseResultSet(resultSet);
            if (publications.size() >= 1)
                publication = publications.get(0);
            else
                return null;
            List<String> references = new ArrayList<>();
            PreparedStatement selectReferences = session.getPrepareStatement(selectReferencesQuery);
            selectReferences.setLong(1, publication.getId());
            ResultSet referencesResultSet = selectReferences.executeQuery();
            while (referencesResultSet.next()) {
                references.add(referencesResultSet.getString("doi"));
            }
            publication.setReferences(references);
            session.closeStatement(statement);
        } catch (SQLException e) {
            System.err.println("Cannot execute 'select by key' query: " + e.getMessage());
        }
        return publication;
    }

    @Override
    public boolean deleteByKey(Long key) {
        String deletePublicationQuery = bundle.getString("publication.delete");
        String deletePaperPublicationQuery = bundle.getString("pap-publication.delete");
        String deleteReferencesQuery = bundle.getString("references.delete");

        PreparedStatement deletePublication = session.getPrepareStatement(deletePublicationQuery);
        PreparedStatement deletePaperPublication = session.getPrepareStatement(deletePaperPublicationQuery);
        PreparedStatement deleteReferences = session.getPrepareStatement(deleteReferencesQuery);
        try {
            session.setAutoCommit(false);

            deletePublication.setInt(1, Math.toIntExact(key));
            deletePublication.execute();

            deletePaperPublication.setInt(1, Math.toIntExact(key));
            deletePaperPublication.execute();

            deleteReferences.setInt(1, Math.toIntExact(key));
            deletePaperPublication.execute();

            session.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.print("Transaction is being rolled back");
            session.rollback();
            return false;
        } finally {
            session.closeStatement(deletePublication);
            session.closeStatement(deletePaperPublication);
            session.closeStatement(deleteReferences);
            session.setAutoCommit(true);
        }
        return true;
    }

    @Override
    public boolean create(PaperPublication publication) {
        String insertPublicationQuery = bundle.getString("publication.insert");
        String insertPaperPublicationQuery = bundle.getString("pap-publication.insert");
        String selectIdQuery = bundle.getString("publication.select-id");
        String insertReferenceQuery = bundle.getString("references.insert");

        String DOI = publication.getDOI();
        String name = publication.getName();
        String author = publication.getAuthor();
        List<String> references = publication.getReferences();
        String keyWords = publication.getKeyWords();
        int circulation = publication.getCirculation();
        String publishingHouseName = publication.getPublishingHouse().name;
        String publishingHouseCity = publication.getPublishingHouse().city;
        String parentId;
        String id;

        PreparedStatement insertPublication = null;
        PreparedStatement insertPaperPublication = null;
        PreparedStatement selectId = null;
        PreparedStatement insertReference = null;

        try {
            session.setAutoCommit(false);

            insertPublication = session.getPrepareStatement(insertPublicationQuery);
            insertPaperPublication = session.getPrepareStatement(insertPaperPublicationQuery);
            selectId = session.getPrepareStatement(selectIdQuery);
            insertReference = session.getPrepareStatement(insertReferenceQuery);

            insertPublication.setString(1, DOI);
            insertPublication.setString(2, name);
            insertPublication.setString(3, author);
            insertPublication.setString(4, keyWords);
            insertPublication.execute();

            ResultSet generatedKeys = insertPublication.getGeneratedKeys();
            if (generatedKeys.next()) {
                parentId = generatedKeys.getString(1);
            } else {
                throw new SQLException("Creating publication failed, no ID obtained.");
            }

            insertPaperPublication.setString(1, parentId);
            insertPaperPublication.setInt(2, circulation);
            insertPaperPublication.setString(3, publishingHouseName);
            insertPaperPublication.setString(4, publishingHouseCity);
            insertPaperPublication.execute();

            generatedKeys = insertPaperPublication.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getString(1);
            } else {
                throw new SQLException("Creating electronic failed, no ID obtained.");
            }
            session.commit();

            for (String referenceDOI : references) {
                selectId.setString(1, referenceDOI);
                ResultSet idSet = selectId.executeQuery();
                String referenceId;
                if (idSet.next()) {
                    referenceId = idSet.getString(1);
                } else {
                    throw new SQLException("Creating reference failed, no publication found with DOI " + referenceDOI);
                }

                insertReference.setString(1, id);
                insertReference.setString(2, referenceId);
                session.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.print("Transaction is being rolled back");
            session.rollback();
            return false;
        } finally {
            session.closeStatement(insertPublication);
            session.closeStatement(insertPaperPublication);
            session.closeStatement(selectId);
            session.closeStatement(insertReference);
            session.setAutoCommit(true);
        }
        return true;
    }

    private List<PaperPublication> parseResultSet(ResultSet resultSet) throws SQLException {
        List<PaperPublication> publications = new ArrayList<>();
        while (resultSet.next()) {
            PaperPublication publication = new PaperPublication();
            publication.setId(resultSet.getLong("id"));
            publication.setDOI(resultSet.getString("doi"));
            publication.setName(resultSet.getString("name"));
            publication.setAuthor(resultSet.getString("author"));
            publication.setKeyWords(resultSet.getString("key_words"));
            publication.setCirculation(resultSet.getInt("circulation"));
            publication.setPublishingHouse(
                    getPublishingHouse(
                            resultSet.getString("publishing_house_name"),
                            resultSet.getString("publishing_house_city")));
            publications.add(publication);
        }
        return publications;
    }

    private PublishingHouse getPublishingHouse(String houseName, String houseCity) {
        return Arrays
                .stream(PublishingHouse.values())
                .filter(house -> clear(house.name).equals(clear(houseName))
                        && clear(house.city).equals(clear(houseCity)))
                .findFirst().orElse(PublishingHouse.NO_NAME);
    }

    private String clear(String text) {
        return text.toUpperCase().trim().replaceAll("[\\s,.!-]", " ");
    }
}
