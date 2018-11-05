package edu.training.model.entities;

public enum PublishingHouse {
    BULLETIN_OF_THE_KPI("Bulletin of NTUU KPI", "Kyiv"),
    LVIV_POLYTECHNIC("Lviv Polytechnic Publishing House", "Lviv"),
    SCOPUS("Scopus", "Ohio"),
    NO_NAME("no name", "no name");

    public final String name;
    public final String city;

    PublishingHouse(String name, String city) {
        this.name = name;
        this.city = city;
    }
}
