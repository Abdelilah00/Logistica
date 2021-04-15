////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2020                                                          /
// developed by Abdelilah Dehaoui GitHub : Abdelilah00                         /
////////////////////////////////////////////////////////////////////////////////

package com.configuration;

import com.configuration.security.MyUserAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

public class TenantContext {

    private static final Logger logger = LoggerFactory.getLogger(TenantContext.class.getName());

    public static long getCurrentTenant() {
        try {
            var userId = ((MyUserAuth) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
/*
            logger.info("UserId ############### =>" + userId);
*/
            return userId;
        } catch (Exception ex) {
/*
            logger.info("UserId ############### => Catch");
*/
            return 1L;
        }

    }
}

