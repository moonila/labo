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
 * CheckParameterNullity.java
 * -------------------------------------------------------------------------
 */

package org.moonila.common.user.aop.impl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.moonila.common.user.aop.annotation.NotNullParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @author ofabre
 * 
 */
@Aspect
public class CheckParameterNullityForServices {

	private final static Logger logger = LoggerFactory
			.getLogger(CheckParameterNullityForServices.class);

    // Pointcut definitions on a method signature basis
    // All public methods must have non null arguments
    @Pointcut("within(com.ebmwebsourcing.webcommons.user..*) && execution(public * *(..)) && @annotation(com.ebmwebsourcing.webcommons.aop.annotation.CheckAllArgumentsNotNull)")
    private void publicMethodAllArgsNotNull() {
    }

    // Pointcut definitions on a method signature basis
    // All public methods must some non null arguments
    @Pointcut("within(com.ebmwebsourcing.webcommons.user..*) && execution(public * *(..)) && @annotation(com.ebmwebsourcing.webcommons.aop.annotation.CheckArgumentsNotNull)")
    private void publicMethodSomeArgsNotNull() {
    }

    // The code advices

    @Around("publicMethodAllArgsNotNull()")
    public Object beforePublicAllArgsNotNull(final ProceedingJoinPoint pjp) throws Throwable {
        // Check that all arguments are not null
        Object[] args = pjp.getArgs();
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                if (args[i] == null) {
                    throw new NullPointerException("Arg " + i + " of method '"
                            + pjp.getSignature().getName() + "' must not be null");
                }
            }
        }
        return pjp.proceed();
    }

    @Around("publicMethodSomeArgsNotNull()")
    public Object beforePublicSomeArgsNotNull(final ProceedingJoinPoint pjp) throws Throwable {
        // Check that annotated arguments are not null
        Object[] args = pjp.getArgs();
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Object[][] argAnnots = methodSignature.getMethod().getParameterAnnotations();
        if (args != null) {
            // Iterate over method arguments
            for (int i = 0; i < args.length; i++) {
                Object[] annots = argAnnots[i];
                if (annots != null) {
                    boolean annotFound = false;
                    // Iterate over argument annotations
                    for (int j = 0; j < annots.length && !annotFound; j++) {
                        // If a NotNullParam annot is found on an argument, it
                        // must be non null
                        if (annots[j] instanceof NotNullParam) {
                            logger.debug("##### NotNullParam annotation found on arg " + i);
                            annotFound = true;
                            if (args[i] == null) {
                                throw new NullPointerException("Arg " + i + " of method '"
                                        + pjp.getSignature() + "' must not be null");
                            }
                        }
                    }

                }
            }
        }
        return pjp.proceed();
    }
}
