package edu.training.model.entities;

import java.util.List;
import java.util.function.Function;

public class ElectronicPublication extends Publication {
    private String link;

    public ElectronicPublication() {
        super();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
