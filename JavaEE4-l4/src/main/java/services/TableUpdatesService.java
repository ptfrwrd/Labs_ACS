package services;

import model.TableUpdate;

import java.util.List;

public interface TableUpdatesService {
    List<TableUpdate> getAllUpdates();

    void insertUpdate(TableUpdate update);
}
