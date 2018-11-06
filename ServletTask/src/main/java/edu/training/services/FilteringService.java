package edu.training.services;

import edu.training.model.entities.Publication;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FilteringService {
    List<Publication> filter(List<Publication> publications, String reference, String keyWord) {
        return publications.stream()
                .filter(e -> e.getReferences().contains(reference) && e.getKeyWords().contains(keyWord))
                .collect(Collectors.toList());
    }

    List<Publication> sortByRelevance(List<Publication> publications) {
        publications.sort(Comparator.comparingInt(p -> p.getReferences().size()));
        return publications;
    }
}
