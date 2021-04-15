////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2020                                                          /
// developed by Abdelilah Dehaoui GitHub : Abdelilah00                         /
////////////////////////////////////////////////////////////////////////////////

package com.configuration;

import com.alexy.models.BaseEntity;
import org.hibernate.EmptyInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Configuration
public class MyInterceptor extends EmptyInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, org.hibernate.type.Type[] types) {
        if (entity instanceof BaseEntity) {
            //logger.info("onDelete ### => " + TenantContext.getCurrentTenant());
            auditDelete(state, propertyNames);
        }
    }

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, org.hibernate.type.Type[] types) {
        if (entity instanceof BaseEntity) {
            //logger.info("onSave ### => " + TenantContext.getCurrentTenant());
            return auditSave(state, propertyNames);
        }
        return false;
    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, org.hibernate.type.Type[] types) {
        if (entity instanceof BaseEntity) {
            //logger.info("onFlushDirty ### => " + TenantContext.getCurrentTenant());
            return auditFlushDirty(currentState, propertyNames);
        }
        return false;
    }

    private boolean auditSave(Object[] currentState, String[] propertyNames) {
        boolean changed = false;
        for (int i = 0; i < propertyNames.length; i++) {
            if ("createdBy".equals(propertyNames[i])) {
                Object currentCreatedBy = currentState[i];
                if ((Long) currentCreatedBy == 0) {
                    currentState[i] = TenantContext.getCurrentTenant();
                    changed = true;
                }
            }
            //TODO : move on from her
            if ("tenantId".equals(propertyNames[i])) {
                Object currentCreatedBy = currentState[i];
                if ((Long) currentCreatedBy == 0) {
                    currentState[i] = TenantContext.getCurrentTenant();
                    changed = true;
                }
            }
        }
        return changed;
    }

    private boolean auditFlushDirty(Object[] currentState, String[] propertyNames) {
        boolean changed = false;
        for (int i = 0; i < propertyNames.length; i++) {
            if ("updatedBy".equals(propertyNames[i])) {
                Object currentUpdatedBy = currentState[i];
                if ((Long) currentUpdatedBy == 0) {
                    currentState[i] = TenantContext.getCurrentTenant();
                    changed = true;
                }
            }
            //TODO : move on from her
            if ("tenantId".equals(propertyNames[i])) {
                Object currentCreatedBy = currentState[i];
                if ((Long) currentCreatedBy == 0) {
                    currentState[i] = TenantContext.getCurrentTenant();
                    changed = true;
                }
            }
        }
        return changed;
    }

    private void auditDelete(Object[] currentState, String[] propertyNames) {
        for (int i = 0; i < propertyNames.length; i++) {
            if ("deletedBy".equals(propertyNames[i])) {
                Object currentDeletedBy = currentState[i];
                if ((Long) currentDeletedBy == 0) {
                    currentState[i] = TenantContext.getCurrentTenant();
                }
            }
        }
    }
}
