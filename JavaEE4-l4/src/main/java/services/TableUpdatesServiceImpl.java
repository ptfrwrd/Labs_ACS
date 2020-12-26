package services;

import dao.TableUpdatesDao;
import model.TableUpdate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TableUpdatesServiceImpl implements TableUpdatesService {

    private TableUpdatesDao tableUpdatesDao;

    public void setTableUpdatesDao(TableUpdatesDao tableUpdatesDao) {
        this.tableUpdatesDao = tableUpdatesDao;
    }

    @Override
    @Transactional
    public List<TableUpdate> getAllUpdates() {
        return tableUpdatesDao.getAllUpdates();
    }

    @Override
    @Transactional
    public void insertUpdate(TableUpdate update) {
        tableUpdatesDao.insertUpdate(update);
    }
}
