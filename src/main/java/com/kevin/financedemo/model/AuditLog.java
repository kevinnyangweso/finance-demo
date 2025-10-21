package com.kevin.financedemo.model;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue
    @Column(name = "audit_id")
    private UUID auditId;

    @JoinColumn(name = "user_id")
    private UUID userId;

    @Column(name = "action", nullable = false, length = 100)
    private String action;

    @Column(name = "entity_name", length = 50, nullable = false)
    private String entityName;

    @Column(name = "entity_id")
    private UUID entityId;

    @Column(name = "old_value")
    private String oldValue;

    @Column(name = "new_value")
    private String newValue;

    @Column(name = "createdAt", updatable = false, nullable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @PrePersist
    public void onCreate(){
        this.createdAt = OffsetDateTime.now();
    }

    public AuditLog (){}

    public AuditLog(UUID userId, String action, String entityName,
                    UUID entityId, String oldValue, String newValue,
                    OffsetDateTime createdAt){
        this.userId = userId;
        this.action = action;
        this.entityName = entityName;
        this.entityId = entityId;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.createdAt = createdAt;
    }

    public UUID getAuditId(){return this.auditId;}
    public void setAuditId(UUID auditId){this.auditId = auditId;}

    public UUID getUserId(){return this.userId;}
    public void setUserId(UUID userId){this.userId = userId;}

    public String getAction(){return this.action;}
    public void setAction(String action){this.action = action;}

    public String getEntityName(){return this.entityName;}
    public void setEntityName(String entityName){this.entityName = entityName;}

    public UUID getEntityId(){return this.entityId;}
    public void setEntityId(UUID entityId){this.entityId = entityId;}

    public String getOldValue(){return this.oldValue;}
    public void setOldValue(String oldValue){this.oldValue = oldValue;}

    public String getNewValue(){return this.newValue;}
    public void setNewValue(String newValue){this.newValue = newValue;}

    public OffsetDateTime getCreatedAt(){return this.createdAt;}
    public void setCreatedAt(OffsetDateTime createdAt){this.createdAt = createdAt;}

    public String toString(){
        return "AuditLog{" +
                ", AuditId=" + auditId +
                ", userId=" + userId +
                ", action=" + action +
                ", entityName=" + entityName +
                ", entityId=" + entityId +
                ", oldValue=" + oldValue +
                ", newValue=" + newValue +
                ", createdAt" + createdAt +
                '}'; 
    }
}
