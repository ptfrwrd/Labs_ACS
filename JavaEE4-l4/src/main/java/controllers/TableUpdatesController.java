package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import services.TableUpdatesService;

@Controller
public class TableUpdatesController {
    private TableUpdatesService tableUpdatesService;

    @Autowired
    @Qualifier(value = "tableUpdatesService")
    public void setTableUpdatesService(TableUpdatesService service) {
        this.tableUpdatesService = service;
    }

    @RequestMapping("/table_updates")
    public String showUpdates(Model m) {
        m.addAttribute("table_updates", tableUpdatesService.getAllUpdates());
        return "table_updates";
    }
}
