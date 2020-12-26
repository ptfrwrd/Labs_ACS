package services;

import dao.EmailNotificationsRulesDao;
import model.EmailNotificationsRule;
import model.TableUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmailNotificationsRulesServiceImpl implements EmailNotificationsRulesService {

    private EmailNotificationsRulesDao emailNotificationsRulesDao;
    private JmsTemplate jmsTemplate;

    public void setEmailNotificationsRulesDao(EmailNotificationsRulesDao emailNotificationsRulesDao) {
        this.emailNotificationsRulesDao = emailNotificationsRulesDao;
    }

    @Autowired
    @Qualifier(value = "jmsTemplate")
    public void setJmsTemplate(JmsTemplate template) {
        this.jmsTemplate = template;
    }

    @Override
    @Transactional
    public List<EmailNotificationsRule> getAll() {
        return emailNotificationsRulesDao.getAll();
    }

    @Override
    @Transactional
    public void create(EmailNotificationsRule emailNotificationsRule) {
        emailNotificationsRulesDao.create(emailNotificationsRule);
        jmsTemplate.convertAndSend(
                "allUpdates",
                new TableUpdate("INSERT", emailNotificationsRule.getId(), "email_notifications_rules", emailNotificationsRule.toString())
        );
    }

    @Override
    @Transactional
    public EmailNotificationsRule getById(Long emailNotificationsRuleId) {
        return emailNotificationsRulesDao.getById(emailNotificationsRuleId);
    }

    @Override
    @Transactional
    public void update(EmailNotificationsRule emailNotificationsRule) {
        emailNotificationsRulesDao.update(emailNotificationsRule);
        jmsTemplate.convertAndSend(
                "allUpdates",
                new TableUpdate("UPDATE", emailNotificationsRule.getId(), "email_notifications_rules", emailNotificationsRule.toString())
        );
    }

    @Override
    @Transactional
    public void delete(Long emailNotificationsRuleId) {
        EmailNotificationsRule emailNotificationsRule = getById(emailNotificationsRuleId);
        emailNotificationsRulesDao.delete(emailNotificationsRule);
        jmsTemplate.convertAndSend(
                "allUpdates",
                new TableUpdate("DELETE", emailNotificationsRule.getId(), "email_notifications_rules", emailNotificationsRule.toString())
        );
    }
}
