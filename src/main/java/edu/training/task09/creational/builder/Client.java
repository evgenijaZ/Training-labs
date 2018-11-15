package edu.training.task09.creational.builder;

public class Client {
    public static void main(String[] args) {
        Tour premiumTour = new PremiumTourBuilder().build();
        Tour economyTour = new EconomyTourBuilder().build();
    }
}
