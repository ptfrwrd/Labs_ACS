package model;

import javax.persistence.*;

@Entity(name = "email_notifications_rules")
public class EmailNotificationsRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "update_type")
    private String updateType;

    public EmailNotificationsRule() {
    }

    public EmailNotificationsRule(String email, String table_name, String update_type) {
        this.email = email;
        this.tableName = table_name;
        this.updateType = update_type;
    }

    public EmailNotificationsRule(Long id, String email, String table_name, String update_type) {
        this.id = id;
        this.email = email;
        this.tableName = table_name;
        this.updateType = update_type;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getTableName() {
        return tableName;
    }

    public String getUpdateType() {
        return updateType;
    }

    @Override
    public String toString() {
        return "EmailNotificationsRule{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", tableName='" + tableName + '\'' +
                ", updateType='" + updateType + '\'' +
                '}';
    }
}
