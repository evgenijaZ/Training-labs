package edu.training.model.dao;

import edu.training.model.entities.ElectronicPublication;
import edu.training.model.entities.Publication;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ElectronicPublicationDao implements IDao<ElectronicPublication, Long> {

    private Session session;

    public ElectronicPublicationDao(DataSource dataSource) {
        session = new Session(dataSource);
    }

    @Override
    public List<ElectronicPublication> getAll() {
        String selectAllQuery = "SELECT library.publication.id AS id, library.publication.doi AS doi, library.publication.name AS name, library.publication.author AS author, library.publication.key_words AS key_words, library.electronic_publication.link AS link FROM library.publication INNER JOIN library.electronic_publication ON publication.id = electronic_publication.parent_id;";
        String selectReferencesQuery = "SELECT library.publication.doi FROM library.references INNER JOIN library.publication ON library.references.reference_id = library.publication.id WHERE library.publication_id = ?;";

        List<ElectronicPublication> publications = new ArrayList<>();
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
    public boolean update(ElectronicPublication publication) {

        String updatePublicationQuery = "UPDATE library.publication SET doi = ?, name = ?, author = ?, key_words = ? WHERE id = ?;";
        String updateEPublicationQuery = "UPDATE library.electronic_publication SET link = ? WHERE parent_id = ?;";
        String selectIdQuery = "SELECT id FROM library.publication where publication.doi = ?;";
        String updateReferenceQuery = "UPDATE library.references SET reference_id = ? WHERE publication_id = ?;";

        String DOI = publication.getDOI();
        String name = publication.getName();
        String author = publication.getAuthor();
        List<String> references = publication.getReferences();
        String keyWords = publication.getKeyWords();
        String link = publication.getLink();
        Long id = publication.getId();

        PreparedStatement updatePublication = null;
        PreparedStatement updateEPublication = null;
        PreparedStatement selectId = null;
        PreparedStatement updateReference = null;

        try {
            session.setAutoCommit(false);

            updatePublication = session.getPrepareStatement(updatePublicationQuery);
            updateEPublication = session.getPrepareStatement(updateEPublicationQuery);
            selectId = session.getPrepareStatement(selectIdQuery);
            updateReference = session.getPrepareStatement(updateReferenceQuery);

            updatePublication.setString(0, DOI);
            updatePublication.setString(2, name);
            updatePublication.setString(3, author);
            updatePublication.setString(4, keyWords);
            updatePublication.setLong(5, id);
            updatePublication.execute();


            updateEPublication.setString(0, link);
            updateEPublication.setLong(1, id);

            updateEPublication.execute();
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
            session.closeStatement(updateEPublication);
            session.closeStatement(selectId);
            session.closeStatement(updateReference);
            session.setAutoCommit(true);
        }
        return true;
    }

    @Override
    public ElectronicPublication getByKey(Long key) {
        String selectAllQuery = "SELECT library.publication.id AS id, library.publication.doi AS doi, library.publication.name AS name, library.publication.author AS author, library.publication.key_words AS key_words, library.electronic_publication.link AS link FROM library.publication INNER JOIN library.electronic_publication ON publication.id = electronic_publication.parent_id WHERE library.publication.id = ? LIMIT 1;";
        String selectReferencesQuery = "SELECT library.publication.doi FROM library.references INNER JOIN library.publication ON library.references.reference_id = library.publication.id WHERE library.publication_id = ?;";
        ElectronicPublication publication = new ElectronicPublication();
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
        String deleteEPublicationQuery = "DELETE from library.electronic_publication where publication.parent_id = ?";
        String deleteReferencesQuery = "DELETE from library.references where references.publication_id = ?";

        PreparedStatement deletePublication = session.getPrepareStatement(deletePublicationQuery);
        PreparedStatement deleteEPublication = session.getPrepareStatement(deleteEPublicationQuery);
        PreparedStatement deleteReferences = session.getPrepareStatement(deleteReferencesQuery);
        try {
            session.setAutoCommit(false);

            deletePublication.setInt(0, Math.toIntExact(key));
            deletePublication.execute();

            deleteEPublication.setInt(0, Math.toIntExact(key));
            deleteEPublication.execute();

            deleteReferences.setInt(0, Math.toIntExact(key));
            deleteEPublication.execute();

            session.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.print("Transaction is being rolled back");
            session.rollback();
            return false;
        } finally {
            session.closeStatement(deletePublication);
            session.closeStatement(deleteEPublication);
            session.closeStatement(deleteReferences);
            session.setAutoCommit(true);
        }
        return true;
    }

    @Override
    public boolean create(ElectronicPublication publication) {

        String insertPublicationQuery = "INSERT INTO `library`.`publication` (`doi`, `name`, `author`, `key_words`) VALUES (?, ?, ?, ?);";
        String query = "INSERT INTO library.electronic_publication (parent_id, link) VALUES (?, ?);";
        String selectIdQuery = "SELECT id FROM library.publication where publication.doi = ?;";
        String insertReferenceQuery = "INSERT INTO library.references (publication_id, reference_id) VALUES (?, ?);";

        String DOI = publication.getDOI();
        String name = publication.getName();
        String author = publication.getAuthor();
        List<String> references = publication.getReferences();
        String keyWords = publication.getKeyWords();
        String link = publication.getLink();
        String parentId;
        String id;

        PreparedStatement insertPublication = null;
        PreparedStatement insertEPublication = null;
        PreparedStatement selectId = null;
        PreparedStatement insertReference = null;

        try {
            session.setAutoCommit(false);

            insertPublication = session.getPrepareStatement(insertPublicationQuery);
            insertEPublication = session.getPrepareStatement(query);
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

            insertEPublication.setString(0, parentId);
            insertEPublication.setString(1, link);
            insertEPublication.execute();

            generatedKeys = insertEPublication.getGeneratedKeys();
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
            session.closeStatement(insertEPublication);
            session.closeStatement(selectId);
            session.closeStatement(insertReference);
            session.setAutoCommit(true);
        }
        return true;
    }

    private List<ElectronicPublication> parseResultSet(ResultSet resultSet) throws SQLException {
        List<ElectronicPublication> publications = new ArrayList<>();
        while (resultSet.next()) {
            ElectronicPublication publication = new ElectronicPublication();
            publication.setId(resultSet.getLong("id"));
            publication.setDOI(resultSet.getString("doi"));
            publication.setName(resultSet.getString("name"));
            publication.setAuthor(resultSet.getString("author"));
            publication.setKeyWords(resultSet.getString("key_words"));
            publication.setLink(resultSet.getString("link"));
            publications.add(publication);
        }
        return publications;
    }
}
