package com.company.ceae.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.security.entity.User;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.haulmont.cuba.core.entity.StandardEntity;

@Table(name = "CEAE_NOTIFICATION_SUBSCRIPTION")
@Entity(name = "ceae$NotificationSubscription")
public class NotificationSubscription extends StandardEntity {
    private static final long serialVersionUID = 990561275074259875L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    protected User user;

    @Column(name = "ENTITY_CLASS", nullable = false)
    protected String entityClass;

    @Column(name = "CONDITION_", nullable = false, length = 4000)
    protected String condition;

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

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }


}