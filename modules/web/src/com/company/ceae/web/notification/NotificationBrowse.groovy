package com.company.ceae.web.notification

import com.company.ceae.entity.Notification
import com.company.ceae.service.NotificationService
import com.haulmont.cuba.core.entity.Entity
import com.haulmont.cuba.gui.WindowManager
import com.haulmont.cuba.gui.app.LinkColumnHelper
import com.haulmont.cuba.gui.components.AbstractLookup
import com.haulmont.cuba.gui.components.Action
import com.haulmont.cuba.gui.components.Component
import com.haulmont.cuba.gui.components.GroupTable
import com.haulmont.cuba.gui.components.LinkButton
import com.haulmont.cuba.gui.components.Table
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory

import javax.inject.Inject

class NotificationBrowse extends AbstractLookup {

    @Inject
    GroupTable<Notification> notificationsTable

    @Inject
    ComponentsFactory componentsFactory

    @Inject
    NotificationService notificationService

    @Override
    void init(Map<String, Object> params) {
        initEntityLinkColumn()
    }

    private initEntityLinkColumn() {
        LinkColumnHelper.initColumn(notificationsTable, 'entityCaption', new LinkColumnHelper.Handler() {
            @Override
            void onClick(Entity entity) {
                def notificationEntityInstance = notificationService.getEntityInstanceFromNotification(entity as Notification)
                openEditor(notificationEntityInstance, WindowManager.OpenType.NEW_TAB)
            }
        })
    }

    void markAsRead() {
        notificationsTable.selected.each {
            it.read = true
        }
        dsContext.commit()
    }
}