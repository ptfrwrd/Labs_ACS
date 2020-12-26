package dao;

import model.TableUpdate;

import java.util.List;

public interface TableUpdatesDao {
    List<TableUpdate> getAllUpdates();

    void insertUpdate(TableUpdate update);
}
