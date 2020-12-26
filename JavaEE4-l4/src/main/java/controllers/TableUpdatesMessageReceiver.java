package controllers;

import model.TableUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import services.TableUpdatesService;

@Component
public class TableUpdatesMessageReceiver {
    private TableUpdatesService tableUpdatesService;

    @Autowired
    @Qualifier(value = "tableUpdatesService")
    public void setTableUpdatesService(TableUpdatesService service) {
        this.tableUpdatesService = service;
    }

    @JmsListener(destination = "allUpdates", containerFactory = "jmsContainerFactory")
    public void receiveMessage(TableUpdate tableUpdate) {
        tableUpdatesService.insertUpdate(tableUpdate);
    }
}