package edu.training.services;

import edu.training.model.entities.Library;
import edu.training.model.entities.Publication;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class LibraryService {
    List<Publication> getPublicationsFromReferences(List<String> references, Library library) {
        Map<String, Publication> publicationsDOI =
                library
                .getPublications().stream()
                .collect(Collectors.toMap(Publication::getDOI, e -> e));

        return references.stream()
                .map(publicationsDOI::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
