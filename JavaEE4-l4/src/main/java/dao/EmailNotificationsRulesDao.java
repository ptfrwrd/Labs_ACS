package dao;

import model.EmailNotificationsRule;

import java.util.List;

public interface EmailNotificationsRulesDao {
    List<EmailNotificationsRule> getAll();

    void create(EmailNotificationsRule emailNotificationsRule);

    EmailNotificationsRule getById(Long emailNotificationsRuleId);

    void update(EmailNotificationsRule emailNotificationsRule);

    void delete(EmailNotificationsRule emailNotificationsRule);
}
