package edu.training.task09.behavioral.state;

import java.util.EnumMap;

public class Human {
    private EnumMap<Place, State> contextMapper = new EnumMap<Place, State>(Place.class);
    private State state;

    Human() {
        contextMapper.put(Place.FOREST, new HunterState());
        contextMapper.put(Place.GLADE, new MushroomPickerState());
        contextMapper.put(Place.RIVER, new FishermanState());
    }

    public void setState(Place place){
        state = contextMapper.get(place);
    }

    public void feed(){
        state.catchPrey();
    }
}
