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
import java.util.Objects;

public class PaperPublicationDao implements IDao<PaperPublication, Long> {

    private Session session;

    public PaperPublicationDao(DataSource dataSource) {
        session = new Session(dataSource);
    }

    @Override
    public List<PaperPublication> getAll() {
        String selectAllQuery = "SELECT library.publication.id AS id, library.publication.doi AS doi, library.publication.name AS name, library.publication.author AS author, library.publication.key_words AS key_words, library.paper_publication.circulation AS circulation, library.paper_publication.publishing_house_name AS publishing_house_name, library.paper_publication.publishing_house_city AS publishing_house_city FROM library.publication INNER JOIN library.paper_publication ON publication.id = paper_publication.parent_id;";
        String selectReferencesQuery = "SELECT library.publication.doi FROM library.references INNER JOIN library.publication ON library.references.reference_id = library.publication.id WHERE library.publication_id = ?;";

        List<PaperPublication> publications = new ArrayList<>();
        PreparedStatement selectAll = session.getPrepareStatement(selectAllQuery);
        try {
            ResultSet resultSet = selectAll.executeQuery();
            publications = parseResultSet(resultSet);
            PreparedStatement selectReferences = session.getPrepareStatement(selectReferencesQuery);
            for (Publication publication : publications) {
                List<String> references = new ArrayList<>();
                selectReferences.setLong(0, publication.getId());
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

        String updatePublicationQuery = "UPDATE library.publication SET doi = ?, name = ?, author = ?, key_words = ? WHERE id = ?;";
        String updatePaperPublicationQuery = "UPDATE library.paper_publication SET circulation = ?, publishing_house_name = ?, publishing_house_city = ? WHERE parent_id = ?;";
        String selectIdQuery = "SELECT id FROM library.publication where publication.doi = ?;";
        String updateReferenceQuery = "UPDATE library.references SET reference_id = ? WHERE publication_id = ?;";

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

            updatePublication.setString(0, DOI);
            updatePublication.setString(2, name);
            updatePublication.setString(3, author);
            updatePublication.setString(4, keyWords);
            updatePublication.setLong(5, id);
            updatePublication.execute();


            updatePaperPublication.setInt(0, circulation);
            updatePaperPublication.setString(1, publishingHouseName);
            updatePaperPublication.setString(2, publishingHouseCity);
            updatePaperPublication.setLong(3, id);

            updatePaperPublication.execute();
            session.commit();

            for (String referenceDOI : references) {
                selectId.setString(0, referenceDOI);
                ResultSet idSet = selectId.executeQuery();
                String referenceId;
                if (idSet.next()) {
                    referenceId = idSet.getString(1);
                } else {
                    throw new SQLException("Creating reference failed, no publication found with DOI " + referenceDOI);
                }

                updateReference.setString(0, referenceId);
                updateReference.setLong(1, id);

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
        String selectAllQuery =
                "SELECT library.publication.id AS id, library.publication.doi AS doi, library.publication.name AS name, library.publication.author AS author, library.publication.key_words AS key_words, library.paper_publication.circulation AS circulation, library.paper_publication.publishing_house_name AS publishing_house_name, library.paper_publication.publishing_house_city AS publishing_house_city FROM library.publication INNER JOIN library.paper_publication ON publication.id = paper_publication.parent_id WHERE library.publication.id = ? LIMIT 1;";
        String selectReferencesQuery = "SELECT library.publication.doi FROM library.references INNER JOIN library.publication ON library.references.reference_id = library.publication.id WHERE library.publication_id = ?;";
        PaperPublication publication = new PaperPublication();
        PreparedStatement statement = session.getPrepareStatement(selectAllQuery);
        try {
            statement.setInt(0, Math.toIntExact(key));
            ResultSet resultSet = statement.executeQuery();
            publication = Objects.requireNonNull(parseResultSet(resultSet).get(0));
            List<String> references = new ArrayList<>();
            PreparedStatement selectReferences = session.getPrepareStatement(selectReferencesQuery);
            selectReferences.setLong(0, publication.getId());
            ResultSet referencesResultSet = selectReferences.executeQuery();
            while (referencesResultSet.next()) {
                references.add(referencesResultSet.getString("doi"));
            }
            publication.setReferences(references);
            session.closeStatement(statement);
        } catch (SQLException e) {
            System.err.println("Cannot execute 'select all' query: " + e.getMessage());
        }
        return publication;
    }

    @Override
    public boolean deleteByKey(Long key) {
        String deletePublicationQuery = "DELETE from library.publication where publication.id = ?";
        String deletePaperPublicationQuery = "DELETE from library.paper_publication where publication.parent_id = ?";
        String deleteReferencesQuery = "DELETE from library.references where references.publication_id = ?";

        PreparedStatement deletePublication = session.getPrepareStatement(deletePublicationQuery);
        PreparedStatement deletePaperPublication = session.getPrepareStatement(deletePaperPublicationQuery);
        PreparedStatement deleteReferences = session.getPrepareStatement(deleteReferencesQuery);
        try {
            session.setAutoCommit(false);

            deletePublication.setInt(0, Math.toIntExact(key));
            deletePublication.execute();

            deletePaperPublication.setInt(0, Math.toIntExact(key));
            deletePaperPublication.execute();

            deleteReferences.setInt(0, Math.toIntExact(key));
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

        String insertPublicationQuery = "INSERT INTO `library`.`publication` (`doi`, `name`, `author`, `key_words`) VALUES (?, ?, ?, ?);";
        String query = "INSERT INTO library.paper_publication (parent_id, circulation, publishing_house_name, publishing_house_city) VALUES (?, ?, ?, ?) ;";
        String selectIdQuery = "SELECT id FROM library.publication where publication.doi = ?;";
        String insertReferenceQuery = "INSERT INTO library.references (publication_id, reference_id) VALUES (?, ?);";

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
            insertPaperPublication = session.getPrepareStatement(query);
            selectId = session.getPrepareStatement(selectIdQuery);
            insertReference = session.getPrepareStatement(insertReferenceQuery);

            insertPublication.setString(0, DOI);
            insertPublication.setString(1, name);
            insertPublication.setString(2, author);
            insertPublication.setString(3, keyWords);
            insertPublication.execute();

            ResultSet generatedKeys = insertPublication.getGeneratedKeys();
            if (generatedKeys.next()) {
                parentId = generatedKeys.getString(1);
            } else {
                throw new SQLException("Creating publication failed, no ID obtained.");
            }

            insertPaperPublication.setString(0, parentId);
            insertPaperPublication.setInt(1, circulation);
            insertPaperPublication.setString(2, publishingHouseName);
            insertPaperPublication.setString(3, publishingHouseCity);
            insertPaperPublication.execute();

            generatedKeys = insertPaperPublication.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getString(1);
            } else {
                throw new SQLException("Creating electronic failed, no ID obtained.");
            }
            session.commit();

            for (String referenceDOI : references) {
                selectId.setString(0, referenceDOI);
                ResultSet idSet = selectId.executeQuery();
                String referenceId;
                if (idSet.next()) {
                    referenceId = idSet.getString(1);
                } else {
                    throw new SQLException("Creating reference failed, no publication found with DOI " + referenceDOI);
                }

                insertReference.setString(0, id);
                insertReference.setString(1, referenceId);
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
