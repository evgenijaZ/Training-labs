package edu.training.model.dao;

import edu.training.model.entities.ElectronicPublication;
import edu.training.model.entities.Publication;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ElectronicPublicationDao implements IDao<ElectronicPublication, Long> {

    private Session session;
    private ResourceBundle bundle = ResourceBundle.getBundle("queries");

    public ElectronicPublicationDao(DataSource dataSource) {
        session = new Session(dataSource);
    }

    @Override
    public List<ElectronicPublication> getAll() {
        String selectAllQuery = bundle.getString("el-publication.select-all");
        String selectReferencesQuery = bundle.getString("references.select-all");

        List<ElectronicPublication> publications = new ArrayList<>();
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
    public boolean update(ElectronicPublication publication) {

        String updatePublicationQuery = bundle.getString("publication.update");
        String updateEPublicationQuery = bundle.getString("el-publication.update");
        String selectIdQuery = bundle.getString("publication.select-id");
        String updateReferenceQuery = bundle.getString("references.update");

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

            updatePublication.setString(1, DOI);
            updatePublication.setString(2, name);
            updatePublication.setString(3, author);
            updatePublication.setString(4, keyWords);
            updatePublication.setLong(5, id);
            updatePublication.execute();


            updateEPublication.setString(1, link);
            updateEPublication.setLong(2, id);

            updateEPublication.execute();
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
            session.closeStatement(updateEPublication);
            session.closeStatement(selectId);
            session.closeStatement(updateReference);
            session.setAutoCommit(true);
        }
        return true;
    }

    @Override
    public ElectronicPublication getByKey(Long key) {
        String selectAllQuery = bundle.getString("el-publication.select-by-id");
        String selectReferencesQuery = bundle.getString("references.select-all");

        ElectronicPublication publication = null;
        PreparedStatement statement = session.getPrepareStatement(selectAllQuery);
        try {
            publication = new ElectronicPublication();
            statement.setInt(1, Math.toIntExact(key));
            ResultSet resultSet = statement.executeQuery();
            List<ElectronicPublication> publications = parseResultSet(resultSet);
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
        String deleteEPublicationQuery = bundle.getString("el-publication.delete");
        String deleteReferencesQuery = bundle.getString("references.delete");

        PreparedStatement deletePublication = session.getPrepareStatement(deletePublicationQuery);
        PreparedStatement deleteEPublication = session.getPrepareStatement(deleteEPublicationQuery);
        PreparedStatement deleteReferences = session.getPrepareStatement(deleteReferencesQuery);
        try {
            session.setAutoCommit(false);

            deletePublication.setInt(1, Math.toIntExact(key));
            deletePublication.execute();

            deleteEPublication.setInt(1, Math.toIntExact(key));
            deleteEPublication.execute();

            deleteReferences.setInt(1, Math.toIntExact(key));
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

        String insertPublicationQuery = bundle.getString("publication.insert");
        String insertEPublicationQuery = bundle.getString("el-publication.insert");
        String selectIdQuery = bundle.getString("publication.select-id");
        String insertReferenceQuery = bundle.getString("references.insert");

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
            insertEPublication = session.getPrepareStatement(insertEPublicationQuery);
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

            insertEPublication.setString(1, parentId);
            insertEPublication.setString(2, link);
            insertEPublication.execute();

            generatedKeys = insertEPublication.getGeneratedKeys();
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
