package edu.training.model.entities;

import java.util.List;

public class PaperPublication extends Publication {
    private PublishingHouse publishingHouse;
    private int circulation;

    public PaperPublication() {
        super();
    }

    public PublishingHouse getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(PublishingHouse publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public int getCirculation() {
        return circulation;
    }

    public void setCirculation(int circulation) {
        this.circulation = circulation;
    }
}
