package services;

import model.EmailNotificationsRule;

import java.util.List;

public interface EmailNotificationsRulesService {
    List<EmailNotificationsRule> getAll();

    void create(EmailNotificationsRule emailNotificationsRule);

    EmailNotificationsRule getById(Long emailNotificationsRuleId);

    void update(EmailNotificationsRule emailNotificationsRule);

    void delete(Long emailNotificationsRuleId);
}
