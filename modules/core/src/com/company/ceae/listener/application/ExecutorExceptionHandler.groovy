/*
 * TODO Copyright
 */

package com.company.ceae.listener.application

import com.company.ceae.entity.Customer
import com.company.ceae.event.CustomerCreatedEvent
import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.LoadContext
import com.haulmont.cuba.core.global.Metadata
import com.haulmont.cuba.security.entity.Group
import com.haulmont.cuba.security.entity.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

import javax.inject.Inject
import java.lang.reflect.Method

/**
 * Acts as EventListener
 *
 * Responsible for catching CustomerCreatedEvents and
 * creating a User for this newly created Customer
 */
@Component
class ExecutorExceptionHandler  implements  AsyncUncaughtExceptionHandler{
    @Override
    void handleUncaughtException(Throwable ex, Method method, Object... params) {
        println "halllo"
    }
}