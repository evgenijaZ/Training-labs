package edu.training.model.entities;

import java.util.List;

public abstract class Publication {
    private long id;
    private String DOI;
    private String name;
    private String author;
    private List<String> references;
    private String keyWords;

    public Publication() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDOI() {
        return DOI;
    }

    public void setDOI(String DOI) {
        this.DOI = DOI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getReferences() {
        return references;
    }

    public void setReferences(List<String> references) {
        this.references = references;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }
}
