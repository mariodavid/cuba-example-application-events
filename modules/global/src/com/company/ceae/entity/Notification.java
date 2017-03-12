package com.company.ceae.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.security.entity.User;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.haulmont.cuba.core.entity.StandardEntity;

@Table(name = "CEAE_NOTIFICATION")
@Entity(name = "ceae$Notification")
public class Notification extends StandardEntity {
    private static final long serialVersionUID = -780819709678689324L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    protected User user;

    @Column(name = "MESSAGE", length = 4000)
    protected String message;

    @Column(name = "ENTITY_CLASS")
    protected String entityClass;

    @Column(name = "ENTITY_ID")
    protected UUID entityId;

    @Column(name = "ENTITY_CAPTION")
    protected String entityCaption;

    @Column(name = "READ_")
    protected Boolean read;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


    public void setEntityCaption(String entityCaption) {
        this.entityCaption = entityCaption;
    }

    public String getEntityCaption() {
        return entityCaption;
    }


    public void setRead(Boolean read) {
        this.read = read;
    }

    public Boolean getRead() {
        return read;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setEntityClass(String entityClass) {
        this.entityClass = entityClass;
    }

    public String getEntityClass() {
        return entityClass;
    }

    public void setEntityId(UUID entityId) {
        this.entityId = entityId;
    }

    public UUID getEntityId() {
        return entityId;
    }


}