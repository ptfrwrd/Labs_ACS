package controllers;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import model.EmailNotificationsRule;
import model.TableUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import services.EmailNotificationsRulesService;

import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
public class EmailMessageReceiver {
    private EmailNotificationsRulesService emailNotificationsRulesService;

    @Autowired
    @Qualifier(value = "emailNotificationsRulesService")
    public void setEmailNotificationsRulesService(EmailNotificationsRulesService service) {
        this.emailNotificationsRulesService = service;
    }

    @JmsListener(destination = "allUpdates", containerFactory = "jmsContainerFactory")
    public void onUpdate(TableUpdate tableUpdate) {
        List<EmailNotificationsRule> rules = emailNotificationsRulesService.getAll();
        for (EmailNotificationsRule rule : rules) {
            if ((rule.getTableName() == null || rule.getTableName().equalsIgnoreCase("All") || rule.getTableName().equalsIgnoreCase(tableUpdate.getObjectTable()))
                    && (rule.getUpdateType() == null || rule.getUpdateType().equalsIgnoreCase("All") || rule.getUpdateType().equalsIgnoreCase(tableUpdate.getUpdateType()))
            ) {
                sendEmail(rule.getEmail(), tableUpdate);
            }
        }
    }

    public void sendEmail(String toEmail, TableUpdate tableUpdate) {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api", "d381b976a3ee4199b8c9a7190f8c39f5-e5da0167-f187c23e"));
        WebResource webResource = client.resource("http://api.mailgun.net/v3/sandbox936b56de063e4fbe834d1c293cb29e72.mailgun.org/messages");
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", "Mailgun Sandbox <postmaster@sandbox936b56de063e4fbe834d1c293cb29e72.mailgun.org>");
        formData.add("to", toEmail);
        formData.add("subject", "'" + tableUpdate.getObjectTable() + "' table is updated");
        formData.add("text", tableUpdate.toString());
        webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData);
    }
}
