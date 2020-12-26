package model;

import javax.persistence.*;

@Entity(name = "table_updates")
public class TableUpdate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "update_type")
    private String updateType;

    @Column(name = "object_id")
    private Long objectId;

    @Column(name = "object_table")
    private String objectTable;

    @Column(name = "object_text")
    private String objectText;

    public TableUpdate() {
    }

    public TableUpdate(String updateType, Long objectId, String objectTable, String objectText) {
        this.updateType = updateType;
        this.objectId = objectId;
        this.objectTable = objectTable;
        this.objectText = objectText;
    }

    public String getUpdateType() {
        return updateType;
    }

    public Long getObjectId() {
        return objectId;
    }

    public String getObjectTable() {
        return objectTable;
    }

    public String getObjectText() {
        return objectText;
    }

    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public void setObjectTable(String objectTable) {
        this.objectTable = objectTable;
    }

    public void setObjectText(String objectText) {
        this.objectText = objectText;
    }

    @Override
    public String toString() {
        return "TableUpdate{" +
                "updateType='" + updateType + '\'' +
                ", objectId=" + objectId +
                ", objectTable='" + objectTable + '\'' +
                ", objectText='" + objectText + '\'' +
                '}';
    }
}
