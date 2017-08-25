/**
 * Web commons : user service.
 * Copyright (c) 2010 EBM Websourcing, http://www.ebmwebsourcing.com/
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * -------------------------------------------------------------------------
 * LogPublicMethodCall.java
 * -------------------------------------------------------------------------
 */

package org.moonila.common.user.aop.impl;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Aspect
public class LogPublicMethodOfServicesCall {

    private final Logger logger = LoggerFactory
			.getLogger(LogPublicMethodOfServicesCall.class);

    // Pointcut definitions on a method signature basis
    // All public methods
    @Pointcut("within(com.ebmwebsourcing.webcommons.user.service..*) && execution(public * *(..))")
    private void allPublicMethodOfServices() {
    }

    // The code advices

    @Around("allPublicMethodOfServices()")
    public Object beforePublicAllArgsNotNull(final ProceedingJoinPoint pjp) throws Throwable {
        // Check that all arguments are not null
        Object[] args = pjp.getArgs();
        String methodName = pjp.getSignature().getName();
        if (logger.isTraceEnabled()) {
            StringBuffer methodCallLog = new StringBuffer("Call method '").append(methodName)
                    .append("' with params : ");
            for (int i = 0; i < args.length; i++) {
                methodCallLog.append(args[i]).append(" ");
            }
            logger.trace(methodCallLog.toString());
        } else if (logger.isDebugEnabled()) {
            StringBuffer methodCallLog = new StringBuffer("Call method '").append(methodName);
            logger.debug(methodCallLog.toString());
        }
        return pjp.proceed();
    }
}
