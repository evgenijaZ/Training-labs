package edu.training.task03.model.entities;

import java.util.ArrayList;
import java.util.List;

public class RecordBook {
    private List <Record> records;

    public RecordBook() {
        this.records = new ArrayList<>();
    }

    public void addRecord(Record record){
        records.add(record);
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
}
