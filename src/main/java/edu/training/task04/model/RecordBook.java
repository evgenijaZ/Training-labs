package edu.training.task04.model;

import edu.training.task04.model.exceptions.NotUniqueNicknameException;

import java.util.ArrayList;
import java.util.List;

public class RecordBook {
    private Record record;

    private List<Record> records;

    public RecordBook() {
        this.records = new ArrayList<>();
    }

    public List<Record> getRecords() {
        return records;
    }

    public void save() throws NotUniqueNicknameException {
        checkNickname(record);
        records.add(record);
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public Record getRecord() {
        return record;
    }

    private void checkNickname(Record record) throws NotUniqueNicknameException {
        for (var item : records) {
            if (item.getNickname().equals(record.getNickname()))
                throw new NotUniqueNicknameException("Such nickname already exists.");
        }
    }
}
