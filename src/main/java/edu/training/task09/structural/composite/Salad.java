package edu.training.task09.structural.composite;

import java.util.ArrayList;
import java.util.List;

public class Salad implements Component {
    List<Component> components = new ArrayList<>();

    void add(Component component) {
        components.add(component);
    }

    @Override
    public String getTaste() {
        var taste = new StringBuilder();
        for (var component : components) {
            taste.append(component.getTaste()).append(" ");
        }
        return taste.append(" taste").toString();
    }
}
