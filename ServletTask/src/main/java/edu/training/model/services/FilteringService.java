package edu.training.model.services;

import edu.training.model.entities.Publication;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FilteringService {
    public List<Publication> filter(List<Publication> publications, String reference, String keyWord) {
        return publications.stream()
                .filter(e -> e.getReferences().contains(reference) && e.getKeyWords().contains(keyWord))
                .collect(Collectors.toList());
    }

    public List<Publication> sortByRelevance(List<Publication> publications) {
        publications.sort(Comparator.comparingInt(p -> p.getReferences().size()));
        return publications;
    }
}
