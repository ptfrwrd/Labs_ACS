package controllers;

import model.EmailNotificationsRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import services.EmailNotificationsRulesService;

@Controller
public class EmailNotificationsRulesController {
    private EmailNotificationsRulesService emailNotificationsRulesService;

    @Autowired
    @Qualifier(value = "emailNotificationsRulesService")
    public void setEmailNotificationsRulesService(EmailNotificationsRulesService service) {
        this.emailNotificationsRulesService = service;
    }

    @RequestMapping("/email_notifications_rules")
    public String showEmailNotificationsRules(Model m) {
        m.addAttribute("email_notifications_rules", emailNotificationsRulesService.getAll());
        return "email_notifications_rules";
    }

    @RequestMapping(value = "/email_notifications_rule_add", method = RequestMethod.POST)
    public String addEmailNotificationsRule(
            @ModelAttribute("email") String email,
            @ModelAttribute("table_name") String tableName,
            @ModelAttribute("update_type") String updateType
    ) {
        if (email != null) {
            EmailNotificationsRule emailNotificationsRule = new EmailNotificationsRule(email, tableName, updateType);
            emailNotificationsRulesService.create(emailNotificationsRule);
        }
        return "redirect:/email_notifications_rules";
    }

    @RequestMapping("/email_notifications_rule_delete")
    public String deleteEmailNotificationsRule(@ModelAttribute("email_notifications_rule_id") Long emailNotificationsRuleId) {
        emailNotificationsRulesService.delete(emailNotificationsRuleId);
        return "redirect:/email_notifications_rules";
    }
}
