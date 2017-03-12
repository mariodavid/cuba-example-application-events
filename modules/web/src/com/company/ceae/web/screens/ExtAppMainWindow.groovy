package com.company.ceae.web.screens;

import com.company.ceae.service.NotificationService;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractMainWindow;
import com.haulmont.cuba.gui.components.Embedded;
import com.haulmont.cuba.gui.components.Timer;
import com.haulmont.cuba.gui.components.mainwindow.FtsField;
import com.haulmont.cuba.gui.components.mainwindow.SideMenu;

import javax.inject.Inject;
import java.util.Map;
import java.util.function.Consumer;

public class ExtAppMainWindow extends AbstractMainWindow {
    @Inject
    private FtsField ftsField;

    @Inject
    private Embedded logoImage;

    @Inject
    private SideMenu sideMenu;

    @Inject
    NotificationService notificationService;



    int timerCounter = 0;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        initLayoutAnalyzerContextMenu(logoImage);
        initLogoImage(logoImage);
        initFtsField(ftsField);
        SideMenu.MenuItem item = sideMenu.createMenuItem("special");
        item.setCaption("Notifications");
        item.setIcon("font-icon:ENVELOPE");
        sideMenu.selectOnClick = true

        item.setCommand(new Consumer<SideMenu.MenuItem>() {
            @Override
            public void accept(SideMenu.MenuItem menuItem) {
                openWindow('ceae$Notification.browse', WindowManager.OpenType.NEW_TAB);
            }
        });
        sideMenu.addMenuItem(item,0);
    }

    @Override
    void ready() {
        updateNotificationCounter()
    }

    public void updateCounters(Timer source) {
        updateNotificationCounter()
        timerCounter++;
    }

    private void updateNotificationCounter() {
        int count = notificationService.countUnreadNotificationsForCurrentUser();
        sideMenu.getMenuItemNN("special")
                .setBadgeText(count + " new");
    }
}