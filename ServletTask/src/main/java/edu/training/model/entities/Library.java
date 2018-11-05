package edu.training.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Publication> publications;

    public Library() {
        publications = new ArrayList<>();
    }

    void add(Publication publication){
        publications.add(publication);
    }

    public List<Publication> getPublications() {
        return publications;
    }
}
